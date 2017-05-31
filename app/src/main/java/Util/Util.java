package Util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sagar on 29/05/17.
 */
public class Util {

    public Util() {

        // Empty constructor
    }

    public Date convertStringtoDate(String dateString) {

    Date date = null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    try

    {
        date = format.parse(dateString);
        System.out.println(date);
    }

    catch(
    ParseException e
    )

    {
        e.printStackTrace();
    }
        return date;
}

}
