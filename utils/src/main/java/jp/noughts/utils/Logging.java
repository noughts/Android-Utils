package jp.noughts.utils;

import android.util.Log;

public class Logging {

    public static boolean enabled;
    public static String appName = "REPLACE_ME";

    private static final String getStringFromObject( Object obj ){
        String str = "";
        if( obj != null ){
            str = obj.toString();
        }
        return str;
    }

    public static final void verbose(Object obj){
        if( !enabled ){
            return;
        }
        Log.v(appName, getFileName() +" - "+ getStringFromObject(obj));
    }
    public static final void debug(Object obj){
        if( !enabled ){
            return;
        }
        Log.d(appName, getFileName() + " - " + getStringFromObject(obj));
    }
    public static final void info(Object obj){
        if( !enabled ){
            return;
        }
        Log.i(appName, getFileName() + " - " + getStringFromObject(obj));
    }
    public static final void warn(Object obj){
        if( !enabled ){
            return;
        }
        Log.w(appName, getFileName() + " - " + getStringFromObject(obj));
    }
    public static final void error(Object obj){
        if( !enabled ){
            return;
        }
        Log.e(appName, getFileName() + " - " + getStringFromObject(obj));
    }
    public static final void error(Throwable obj){
        if( !enabled ){
            return;
        }
        if( obj == null ){
            return;
        }
        Log.e(appName, getFileName() + " - " + obj);
    }
    public static final void error(String message, Throwable e){
        if( !enabled ){
            return;
        }
        if( e == null ){
            return;
        }
        Log.e(appName, getFileName() + " - " + message +" error="+ getStringFromObject(e));
    }







    /**
     * デバッグレベルでログを残す
     * @param message
     */
    private static final void d(String message){
        Log.d(appName, getFileName() + " - " + message);
    }

    private static final void d(String message, Throwable e){
        Log.d(appName, getFileName() +" - " + message);
        printThrowable(e);
        if (e.getCause() != null){
            printDebugThrowable(e.getCause());
        }
    }

    /**
     * インフォレベルでログを残す。
     * @param message
     */
    private static final void i(String message){
        Log.i(appName, getFileName() +" - "+ message);
    }

    /**
     * エラーレベルでログを残す。
     * @param message
     */
    private static final void e(String message){
        Log.e(appName, getFileName() +" - "+ message);
    }

    /**
     * ワーニングレベルでログを残す。
     * @param message
     */
    private static final void w(String message){
        Log.w(appName, getFileName() +" - "+ message);
    }

    /**
     * ファイル名と行番号を取得
     *
     * コールスタックで2階層上のファイル名と行番号を取得する。
     * @return
     */
    private static String getFileName(){
        Exception e = new Exception();
        StackTraceElement[] elements = e.getStackTrace();
        String ret = "";
        if (elements.length > 2){
            ret = "("+elements[2].getFileName();
            ret += ":";
            ret += elements[2].getLineNumber()+")";
        }
        return ret;
    }

    /**
     * エラーレベルで例外のスタックトレースとともにログを残す
     * @param message
     * @param e
     */
    private static final void e(String message, Throwable e){
        if( !enabled ){
            return;
        }
        Log.e(appName, getFileName() +" - "+message);
        printThrowable(e);
        if (e.getCause() != null){
            printThrowable(e.getCause());
        }
    }

    /**
     * 例外のスタックトレースをログに出力する
     * @param e
     */
    private static final void printDebugThrowable(Throwable e){
        if( !enabled ){
            return;
        }
        Log.d(appName,e.getClass().getName()+": "+e.getMessage());
        for (StackTraceElement element : e.getStackTrace()){
            Log.d(appName, "  at "+element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+":"+element.getLineNumber()+")");
        }
    }

    /**
     * 例外のスタックトレースをログに出力する
     * @param e
     */
    private static final void printThrowable(Throwable e){
        if( !enabled ){
            return;
        }
        Log.e(appName,e.getClass().getName()+": "+e.getMessage());
        for (StackTraceElement element : e.getStackTrace()){
            Log.e(appName, "  at "+element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+":"+element.getLineNumber()+")");
        }
    }
}
