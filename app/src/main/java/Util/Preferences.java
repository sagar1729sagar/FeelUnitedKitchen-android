package Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by sagar on 30/05/17.
 */
public class Preferences {



    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;

    int PVT_MODE = 0;
    String PREF_NAME = "Kitchen Preferences";
    private static final String CURRENT_VIEW = "CurrentView";
    private static final String INTERNET_PERMISSION = "InternetPermission";
    private static final String CURRENT_DATE = "CurrentDate";
    private static final String SELECTED_DATE = "SelectedDate";

    public Preferences(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME,PVT_MODE);
        editor = prefs.edit();
    }


    public void setCurrentDate(String date){
        editor.putString(CURRENT_DATE,date);
        editor.commit();
    }

    public String getCurrentDate(){
        return prefs.getString(CURRENT_DATE,"0");
    }

    public void setSelectedDate(String date){
        editor.putString(SELECTED_DATE,"0");
        editor.commit();
    }

    public String getSelectedDate(){
        return prefs.getString(SELECTED_DATE,"0");
    }

    public void setCurrenCourse(int code){
        /**
         * 0 - Starters
         * 1 - Main course
         * 2 -Deserts
         */
        editor.putInt(CURRENT_VIEW,code);
        editor.commit();
    }
    public int getCourse(){
        return prefs.getInt(CURRENT_VIEW,0);
    }

    public void setInternetPermissionStatus(boolean status){
        editor.putBoolean(INTERNET_PERMISSION,status);
        editor.commit();
    }
    public Boolean isInternetPermmisionAvailable(){
        return prefs.getBoolean(INTERNET_PERMISSION,false);
    }

}
