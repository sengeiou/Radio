package cn.yuntk.radio.ibook;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

import com.google.android.gms.ads.MobileAds;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
import com.umeng.commonsdk.UMConfigure;

import cn.yuntk.radio.BuildConfig;
import cn.yuntk.radio.R;
import cn.yuntk.radio.ReadActivityLifecycleCallbacks;
import cn.yuntk.radio.ibook.ads.ADConstants;
import cn.yuntk.radio.ibook.base.ForegroundObserver;
import cn.yuntk.radio.ibook.common.Constants;
import cn.yuntk.radio.ibook.component.AppComponent;
import cn.yuntk.radio.ibook.component.DaggerAppComponent;
import cn.yuntk.radio.ibook.dbdao.local.DaoMaster;
import cn.yuntk.radio.ibook.dbdao.local.DaoSession;
import cn.yuntk.radio.ibook.module.AppModule;
import cn.yuntk.radio.ibook.module.BookApiModule;
import cn.yuntk.radio.ibook.util.GlobalApp;
import cn.yuntk.radio.ibook.util.NetworkUtils;
import cn.yuntk.radio.ibook.util.SharedPreferencesUtil;

import java.io.File;

import static cn.yuntk.radio.Constants.BUGLY_KEY;


/**
 * 创建时间:2018/4/3
 * 创建人: lxp
 * 描述:app
 */

public class XApplication extends Application {

    public static String cachePath = "";
    public static boolean isHandle = false;//是否百度处理
    public static XApplication sInstance;
    private static Handler mMainThreadHandler;
    private boolean haveAD = true;

    public static boolean isListeneringPage = false;
    public static boolean isMusicDetailPage = false;
    public boolean isBackGroud = false;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalApp.APP = this;
        sInstance = this;
        initCompoent();
        getWindowWidth();
        setCacheFile();
        initPrefs();
        mMainThreadHandler = new Handler();

        //初始化Google广告
        MobileAds.initialize(this, ADConstants.AD_GOOGLE_APPID);
        //注册生命周期回调，用于后台返回展示广告
        registerActivityLifecycleCallbacks(new ReadActivityLifecycleCallbacks());
        //bugly版本升级配置和初始化
        BuglyStrategy strategy = configUpgradeInfo();
        Bugly.init(this, BUGLY_KEY, BuildConfig.DEBUG, strategy);

    }

    @NonNull
    private BuglyStrategy configUpgradeInfo() {
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.showInterruptedStrategy = true;
        Beta.autoDownloadOnWifi = true;
        Beta.enableNotification = true;
        Beta.upgradeDialogLayoutId = R.layout.autoupdate_dialog_layout;
        BuglyStrategy strategy = new BuglyStrategy();
        strategy.setAppChannel(BuildConfig.FLAVOR);
        return strategy;
    }

    static {
        //static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            //layout.setPrimaryColorsId(R.color.color_activity_bg,R.color.color_activity_bg);//全局设置 背景颜色 字体颜色
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    /**
     * 获取屏幕宽度、高度
     */
    public void getWindowWidth() {
        //获取屏幕信息
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Constants.width = dm.widthPixels;
        Constants.height = dm.heightPixels;
        Constants.screenDensity = dm.density;//屏幕密度
    }

    //    设置应用缓存路径
    private void setCacheFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 设定存放文件夹的路径
            String path = Environment
                    .getExternalStorageDirectory()
                    + File.separator
                    + "ytk_wholesale"
                    //              + File.separator + "cache" + File.separator
                    ;
            // 声明存放文件夹的File对象
            File imageFolder = new File(path);

            // 如果不存在此文件夹,则创建
            if (!imageFolder.exists()) {
                imageFolder.mkdirs();
            }
            cachePath = path;
        }
    }

    /*数据库初始化*/
    public final static String dbName = "book_mp3_db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, dbName, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


    public static XApplication getsInstance() {
        return sInstance;
    }

    public void setHaveAD(boolean haveAD) {
        this.haveAD = haveAD;
    }

    /**
     * 得到主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public boolean isHaveAD() {
        if (!NetworkUtils.isConnected(XApplication.getsInstance())) {
            return false;
        }
        return haveAD;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .bookApiModule(new BookApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_PRIVATE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

}