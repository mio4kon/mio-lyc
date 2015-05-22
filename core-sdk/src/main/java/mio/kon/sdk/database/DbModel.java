package mio.kon.sdk.database;

import java.util.List;

/**
 * Created by mio on 15-5-12.
 * 数据库信息
 */
public class DbModel {
    public String db_name;
    public int version;
    public List<Table> tables;

   public class Table{
        public String tb_name;
    }
}
