package mio.kon.sdk.database;

import android.content.ContentValues;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mio on 15-5-12.
 */
public class DbHelper<T> {
    private static DbHelper instance;

    public static synchronized DbHelper getInstance(){

        if (instance == null)
            instance = new DbHelper ();
        return instance;
    }

    /**
     * 增加一条记录 *
     * @return -1:添加失败 1:成功
     */
    public int create(T t) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao (t.getClass ());
            return dao.create (t);

        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            if(db!=null){
                db.close ();
            }
        }
        return -1;
    }


    /**
     * 增加一条记录,如果id存在就修改
     * Note:在Bean的id字段加上 @DatabaseField(id = true)
     * @param t
     */
    public void  createOrUpdate(T t){
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();

        try {
            Dao dao =db.getDao (t.getClass ());
            dao.createOrUpdate (t);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
    public int createIfNoExists(T t,Map<String,Object> fieldValues){
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(t.getClass());
            if (dao.queryForFieldValues(fieldValues).size() < 1) {
                return dao.create(t);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return -1;
    }


    /**
     * 查询是否存在该条数据
     * @param t 表对象
     * @param fieldValues 查询条件键值对
     * @return true:存在
     */
    public boolean exists(T t, Map<String, Object> fieldValues) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(t.getClass());
            if (dao.queryForFieldValues(fieldValues).size() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return false;
    }

    /**
     * 查询该表是否存在数据
     * @param clazz 无需对象,只需要标明
     * @return true:存在
     */
    public boolean exists(Class<T> clazz) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance();
        try {
            Dao dao = db.getDao(clazz);
            if (dao.queryForAll ().size ()>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return false;
    }

    /**
     * 查询一条记录
     * @param t
     * @param fieldName
     * @param value
     * @return
     */
    public List<T> queryForEq(T t, String fieldName, Object value) {
        SqliteHelperOrm db =SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(t.getClass ());
            return dao.queryForEq (fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return new ArrayList<T> ();
    }



    /**
     * 根据特定条件更新特定字段
     *
     * @param t
     * @param values
     * @param fieldName
     * @param value
     * @return
     */
    public int update(T t, ContentValues values, String fieldName, Object value) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(t.getClass ());
            UpdateBuilder<T, Long> updateBuilder = dao.updateBuilder();
            updateBuilder.where().eq(fieldName, value);
            for (String key : values.keySet()) {
                updateBuilder.updateColumnValue(key, values.get(key));
            }
            return updateBuilder.update ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return -1;
    }

    /** 更新一条记录 */
    public int update(T t) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(t.getClass());
            return dao.update(t);
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return -1;
    }

    /** 查询所有记录 */
    public List<T> queryForAll(Class<T> clazz) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(clazz);
            return dao.queryForAll ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return new ArrayList<T>();
    }
    /** 查询记录的第一条 */
    public T queryFirstForAll(Class<T> clazz) {
        SqliteHelperOrm db = SqliteHelperOrm.getNewInstance ();
        try {
            Dao dao = db.getDao(clazz);
            return (T) dao.queryForAll ().get (0);
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            if (db != null)
                db.close();
        }
        return null;
    }

}
