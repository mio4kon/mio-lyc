package mio.kon.sdk.database;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import mio.kon.sdk.Exception.OrmException;

/**
 * Created by mio on 15-5-12.
 * 解析db xml文件内容
 */
public class DbXmlParser {
    public DbModel parse(InputStream is) {
        DbModel dbModel = null;
        DbModel.Table table = null;
        try {
            XmlPullParser parser = Xml.newPullParser ();
            parser.setInput (is, "utf-8");
            int eventType = parser.getEventType ();
            while (eventType != XmlPullParser.END_DOCUMENT){
               switch (eventType){
                   case XmlPullParser.START_DOCUMENT:
                       dbModel = new DbModel ();

                       break;
                   case XmlPullParser.START_TAG:
                       if(parser.getName ().equals ("dbname")){
                           eventType = parser.next ();
                           dbModel.db_name = parser.getText ();
                       }else if(parser.getName ().equals ("version")){
                           eventType = parser.next ();
                           dbModel.version = Integer.parseInt (parser.getText ());
                       }else if(parser.getName ().equals ("tables")){
                           dbModel.tables = new ArrayList<DbModel.Table> ();
                       }else if (parser.getName ().equals ("table")){
                           table = dbModel.new Table ();
                           eventType = parser.next ();
                           table.tb_name = parser.getText ();
                       }
                       break;
                   case XmlPullParser.END_TAG:
                       if(parser.getName ().equals ("table")){
                           dbModel.tables.add (table);
                           table = null;
                       }
                       break;
               }
                eventType = parser.next ();
            }
            return dbModel;

        } catch (XmlPullParserException e) {
            throw new OrmException ("解析xml文件错误:"+e.getMessage ());

        } catch (IOException e) {
            throw new OrmException ("解析xml文件错误:"+e.getMessage ());
        }
    }
}
