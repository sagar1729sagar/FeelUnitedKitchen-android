package ssapps.com.feelunitedkitchen;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sagar on 02/06/17.
 */
public class MenuDisplayVIewPager extends ViewPager {

    public MenuDisplayVIewPager(Context context) {
        super(context);
    }

    public MenuDisplayVIewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev){
        return false;
    }
}
