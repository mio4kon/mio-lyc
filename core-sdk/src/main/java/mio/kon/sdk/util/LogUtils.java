package mio.kon.sdk.util;

import android.util.Log;

/**
 * Created by mio on 15-4-14.
 */
public class LogUtils {
    public static final String DEFAULT_TAG = "mio4kon";

    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_WARNING = 3;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_NO_LOG = 10;

    private static int minLevel = LEVEL_VERBOSE; //默认都打印


    /**
     * 设置log level, 低于这个等级不会打印
     * @param level
     */
    public static  void  setLogLevel(int level){
        minLevel = level;
    }

    public static void v(String tag, String msg) {
        if (minLevel > LEVEL_VERBOSE) {
            return;
        }
        Log.v (tag, msg);
    }


    public static void v(String tag, String msg, Object... args) {
        if (minLevel > LEVEL_VERBOSE) {
            return;
        }
        if (args.length > 0) {
            msg = String.format (msg, args);
        }
        Log.v (tag, msg);
    }


    public static void d(String tag, String msg) {
        if (minLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d (tag, msg);
    }

    public static void _d(String msg) {
        if (minLevel > LEVEL_DEBUG) {
            return;
        }
        Log.d (DEFAULT_TAG, msg);
    }


    public static void d(String tag, String msg, Object... args) {
        if (minLevel > LEVEL_DEBUG) {
            return;
        }
        if (args.length > 0) {
            msg = String.format (msg, args);
        }
        Log.d (tag, msg);
    }


    public static void i(String tag, String msg) {
        if (minLevel > LEVEL_INFO) {
            return;
        }
        Log.i (tag, msg);
    }


    public static void i(String tag, String msg, Object... args) {
        if (minLevel > LEVEL_INFO) {
            return;
        }
        if (args.length > 0) {
            msg = String.format (msg, args);
        }
        Log.i (tag, msg);
    }



    public static void w(String tag, String msg) {
        if (minLevel > LEVEL_WARNING) {
            return;
        }
        Log.w (tag, msg);
    }


    public static void w(String tag, String msg, Object... args) {
        if (minLevel > LEVEL_WARNING) {
            return;
        }
        if (args.length > 0) {
            msg = String.format (msg, args);
        }
        Log.w (tag, msg);
    }



    public static void e(String tag, String msg) {
        if (minLevel > LEVEL_ERROR) {
            return;
        }
        Log.e (tag, msg);
    }


    public static void e(String tag, String msg, Object... args) {
        if (minLevel > LEVEL_ERROR) {
            return;
        }
        if (args.length > 0) {
            msg = String.format (msg, args);
        }
        Log.e (tag, msg);
    }

}
