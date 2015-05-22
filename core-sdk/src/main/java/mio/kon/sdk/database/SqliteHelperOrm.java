package mio.kon.sdk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import mio.kon.sdk.Exception.OrmException;
import mio.kon.sdk.util.LogUtils;

/**
 * Created by mio on 15-5-12.
 */
public class SqliteHelperOrm extends OrmLiteSqliteOpenHelper {
    private final static String TAG = "SqliteHelperOrm";
    private static Context M_CONTEXT = null;
    private static DbModel M_dbModel = null;
    private static SqliteHelperOrm instance;

    public static void initOrm(Context context) {
        M_CONTEXT = context;
        //解析xml
        try {

            InputStream is = M_CONTEXT.getAssets ().open ("ormlite.xml");
            DbXmlParser dbXmlParser = new DbXmlParser ();
            M_dbModel = dbXmlParser.parse (is);

            LogUtils.d (TAG, "解析ormlite.xml结果-->\n" +
                            "db_name:" + M_dbModel.db_name + "\n" +
                            "dv_version:" + M_dbModel.version + "\n"
            );

        } catch (IOException e) {
            throw new OrmException ("打开ormlite.xml文件错误.");
        }
    }


    public static synchronized SqliteHelperOrm getNewInstance() {
        if (M_CONTEXT == null) {
            throw new OrmException ("未初始化SqliteHelperOrm,请在Application中调用initOrm");
        }

        if (M_dbModel == null) {
            throw new OrmException ("请确认在assets目录下创建了xml文件,具体见readme");
        }
        //不用单例,因为每次都会关闭.
        instance = new SqliteHelperOrm ();
        return instance;
    }

    /**
     * 无用*
     */
    private SqliteHelperOrm(Context context) {
        super (context, M_dbModel.db_name, null, M_dbModel.version);
    }

    public SqliteHelperOrm() {
        super (M_CONTEXT, M_dbModel.db_name, null, M_dbModel.version);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable (connectionSource, Class.forName (M_dbModel.tables.get (0).tb_name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,  Class.forName (M_dbModel.tables.get (0).tb_name), true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        }
    }


}
