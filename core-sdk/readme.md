>开发六叶草APP所封装的SDK.完善中..

#使用Api需要配置:

## 在Application中需要做的
初始化API
ApiManager.initApi(String HOST_URL)

#使用Orm需要配置:

## 在Application中需要做的
初始化SqliteHelperOrm
SqliteHelperOrm.initOrm (Context context);
## 在assets文件中需要做的
在assets文件下建`ormlite.xml`文件
在xml文件中写入参数
        <?xml version="1.0" encoding="utf-8"?>
        <ormlite>
            <!-- 数据库名称-->
            <dbname>lyc</dbname>
            <!-- 数据库版本-->
            <version>1</version>
            <!-- 数据库表合集-->
            <tables>
                <!-- 表对应的bean的全称-->
                <table>mio.kon.lyc.model.UserInfo</table>
            </tables>
        </ormlite>
