package cn.yuntk.radio.ibook.dbdao.local;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import cn.yuntk.radio.ibook.bean.ListenerMusicInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "dao_listener_chapter".
*/
public class ListenerMusicInfoDao extends AbstractDao<ListenerMusicInfo, String> {

    public static final String TABLENAME = "dao_listener_chapter";

    /**
     * Properties of entity ListenerMusicInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Book_id = new Property(0, String.class, "book_id", false, "BOOK_ID");
        public final static Property Data_id = new Property(1, String.class, "data_id", true, "DATA_ID");
        public final static Property Book_title = new Property(2, String.class, "book_title", false, "BOOK_TITLE");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property MusicPath = new Property(4, String.class, "musicPath", false, "MUSIC_PATH");
        public final static Property PathOnline = new Property(5, String.class, "pathOnline", false, "PATH_ONLINE");
        public final static Property Book_chapter_status = new Property(6, String.class, "book_chapter_status", false, "BOOK_CHAPTER_STATUS");
        public final static Property Duration = new Property(7, int.class, "duration", false, "DURATION");
        public final static Property Progress = new Property(8, int.class, "progress", false, "PROGRESS");
        public final static Property ListenerStatus = new Property(9, int.class, "listenerStatus", false, "LISTENER_STATUS");
        public final static Property Mark1 = new Property(10, String.class, "mark1", false, "MARK1");
    }

    private Query<ListenerMusicInfo> listenerBookInfo_MusicsQuery;

    public ListenerMusicInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ListenerMusicInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"dao_listener_chapter\" (" + //
                "\"BOOK_ID\" TEXT," + // 0: book_id
                "\"DATA_ID\" TEXT PRIMARY KEY NOT NULL ," + // 1: data_id
                "\"BOOK_TITLE\" TEXT," + // 2: book_title
                "\"TITLE\" TEXT," + // 3: title
                "\"MUSIC_PATH\" TEXT," + // 4: musicPath
                "\"PATH_ONLINE\" TEXT," + // 5: pathOnline
                "\"BOOK_CHAPTER_STATUS\" TEXT," + // 6: book_chapter_status
                "\"DURATION\" INTEGER NOT NULL ," + // 7: duration
                "\"PROGRESS\" INTEGER NOT NULL ," + // 8: progress
                "\"LISTENER_STATUS\" INTEGER NOT NULL ," + // 9: listenerStatus
                "\"MARK1\" TEXT);"); // 10: mark1
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"dao_listener_chapter\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ListenerMusicInfo entity) {
        stmt.clearBindings();
 
        String book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindString(1, book_id);
        }
 
        String data_id = entity.getData_id();
        if (data_id != null) {
            stmt.bindString(2, data_id);
        }
 
        String book_title = entity.getBook_title();
        if (book_title != null) {
            stmt.bindString(3, book_title);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String musicPath = entity.getMusicPath();
        if (musicPath != null) {
            stmt.bindString(5, musicPath);
        }
 
        String pathOnline = entity.getPathOnline();
        if (pathOnline != null) {
            stmt.bindString(6, pathOnline);
        }
 
        String book_chapter_status = entity.getBook_chapter_status();
        if (book_chapter_status != null) {
            stmt.bindString(7, book_chapter_status);
        }
        stmt.bindLong(8, entity.getDuration());
        stmt.bindLong(9, entity.getProgress());
        stmt.bindLong(10, entity.getListenerStatus());
 
        String mark1 = entity.getMark1();
        if (mark1 != null) {
            stmt.bindString(11, mark1);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ListenerMusicInfo entity) {
        stmt.clearBindings();
 
        String book_id = entity.getBook_id();
        if (book_id != null) {
            stmt.bindString(1, book_id);
        }
 
        String data_id = entity.getData_id();
        if (data_id != null) {
            stmt.bindString(2, data_id);
        }
 
        String book_title = entity.getBook_title();
        if (book_title != null) {
            stmt.bindString(3, book_title);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }
 
        String musicPath = entity.getMusicPath();
        if (musicPath != null) {
            stmt.bindString(5, musicPath);
        }
 
        String pathOnline = entity.getPathOnline();
        if (pathOnline != null) {
            stmt.bindString(6, pathOnline);
        }
 
        String book_chapter_status = entity.getBook_chapter_status();
        if (book_chapter_status != null) {
            stmt.bindString(7, book_chapter_status);
        }
        stmt.bindLong(8, entity.getDuration());
        stmt.bindLong(9, entity.getProgress());
        stmt.bindLong(10, entity.getListenerStatus());
 
        String mark1 = entity.getMark1();
        if (mark1 != null) {
            stmt.bindString(11, mark1);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1);
    }    

    @Override
    public ListenerMusicInfo readEntity(Cursor cursor, int offset) {
        ListenerMusicInfo entity = new ListenerMusicInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // book_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // data_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // book_title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // musicPath
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // pathOnline
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // book_chapter_status
            cursor.getInt(offset + 7), // duration
            cursor.getInt(offset + 8), // progress
            cursor.getInt(offset + 9), // listenerStatus
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // mark1
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ListenerMusicInfo entity, int offset) {
        entity.setBook_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setData_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBook_title(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMusicPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPathOnline(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBook_chapter_status(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDuration(cursor.getInt(offset + 7));
        entity.setProgress(cursor.getInt(offset + 8));
        entity.setListenerStatus(cursor.getInt(offset + 9));
        entity.setMark1(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ListenerMusicInfo entity, long rowId) {
        return entity.getData_id();
    }
    
    @Override
    public String getKey(ListenerMusicInfo entity) {
        if(entity != null) {
            return entity.getData_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ListenerMusicInfo entity) {
        return entity.getData_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "musics" to-many relationship of ListenerBookInfo. */
    public List<ListenerMusicInfo> _queryListenerBookInfo_Musics(String book_id) {
        synchronized (this) {
            if (listenerBookInfo_MusicsQuery == null) {
                QueryBuilder<ListenerMusicInfo> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Book_id.eq(null));
                listenerBookInfo_MusicsQuery = queryBuilder.build();
            }
        }
        Query<ListenerMusicInfo> query = listenerBookInfo_MusicsQuery.forCurrentThread();
        query.setParameter(0, book_id);
        return query.list();
    }

}
