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

    public Preferences(Context context){
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME,PVT_MODE);
        editor = prefs.edit();
    }

    public void setCurrentView(int code){
        /**
         * 0 - Menu Page
         * 1 - Cart Page
         * 2 - Active Orders page
         * 3 - Delivery ORders Page
         * 4 - Feedback Page
         * 5 - Accounts Page
         */
        editor.putInt(CURRENT_VIEW,code);
        editor.commit();
    }
    public int isInView(){
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
