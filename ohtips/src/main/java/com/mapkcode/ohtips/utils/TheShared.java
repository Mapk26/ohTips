package com.mapkcode.ohtips.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TheShared {

    private Context theContext;

    /**
     * SharedPreferences
     */
    private static String PACKAGE_NAME;
    private SharedPreferences shared;
    private SharedPreferences.Editor editor;

    public TheShared(Context context, String s){

        this.theContext = context;
        PACKAGE_NAME = context.getPackageName() + s;
        shared = context.getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);

    }


    public int readShared(String k){

        return shared.getInt(k, 0);
    }

    public String readSharedString(String k){

        return shared.getString(k, "");
    }

    public long readSharedLong(String k){

        return shared.getLong(k, 0);
    }

    public void writeShared(String k, int i){
        editor = shared.edit();
        editor.putInt(k, i);
        editor.apply();
    }

    public void writeShared(String k, String s){
        editor = shared.edit();
        editor.putString(k, s);
        editor.apply();
    }

    public void writeSharedLong(String k, long l){
        editor = shared.edit();
        editor.putLong(k, l);
        editor.apply();
    }

    public void clearAll(){
        shared.edit().clear().apply();
    }

}
