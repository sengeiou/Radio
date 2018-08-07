package cn.yuntk.radio.utils

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cn.yuntk.radio.Constants
import cn.yuntk.radio.R
import cn.yuntk.radio.bean.messageEvent.ListenEvent
import org.greenrobot.eventbus.EventBus
import java.io.Serializable

/**
 * Author : Gupingping
 * Date : 2018/7/15
 * Mail : gu12pp@163.com
 */
fun AppCompatActivity.switchFragment(current: Fragment?, targetFg: Fragment, tag: String? = null) {
    val ft = supportFragmentManager.beginTransaction()
    current?.run { ft.hide(this) }
    if (!targetFg.isAdded) {
        tag?.run { ft.add(R.id.container, targetFg, tag) } ?: ft.add(R.id.container, targetFg)
        ft.show(targetFg)
        ft.commitAllowingStateLoss()
    }
}

fun Activity.jumpActivity(clazz: Class<*>, serializable: Serializable? = null) {
    val intent = Intent()
    serializable?.let {
        val bundle = Bundle()
        bundle.putSerializable(Constants.KEY_SERIALIZABLE, it)
        intent.putExtras(bundle)
    }
    intent.setClass(this, clazz)
    startActivity(intent)
}

fun Service.jumpActivity(clazz: Class<*>) {
    val intent = Intent()
    intent.setClass(this, clazz)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

fun Activity.registerTimeListener(receiver: BroadcastReceiver) {
    val intentFilter = IntentFilter()
    intentFilter.addAction(Intent.ACTION_TIME_TICK)
    registerReceiver(receiver, intentFilter)
}

fun Activity.unRegisterTimeListener(receiver: BroadcastReceiver) {
    unregisterReceiver(receiver)
}

fun Activity.timeReceiver(method: () -> Unit): BroadcastReceiver {
    return object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == Intent.ACTION_TIME_TICK) {
                Lg.e(LT.RadioNet, "intent=${intent?.action}")
                method()
            }
        }
    }
}

fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.log(msg: String) {
    msg.logE(LT.RadioNet)
}

fun Service.log(msg: String) {
    msg.logE(LT.RadioNet)
}

fun Any.registerEventBus() {
    EventBus.getDefault().register(this)
}


fun Any.unRegisterEventBus() {
    EventBus.getDefault().unregister(this)
}

fun Any.postEvent(any: ListenEvent) {
    EventBus.getDefault().post(any)
}