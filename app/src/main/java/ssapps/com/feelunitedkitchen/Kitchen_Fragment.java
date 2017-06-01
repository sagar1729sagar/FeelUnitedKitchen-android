package ssapps.com.feelunitedkitchen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import Adapters.ViewPagerAdapter;
import Util.Preferences;

/**
 * Created by sagar on 29/05/17.
 */
public class Kitchen_Fragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Preferences prefs;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kitchen_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager)view.findViewById(R.id.viewpager_kitchen);
        tabLayout = (TabLayout)view.findViewById(R.id.tabs_kitchen);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        prefs = new Preferences(getContext());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
           // prefs.setCurrentView(position+1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }


    private void setupViewPager(ViewPager viewPager) {

        // configure view pager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new Menu_Fragment(),"STARTERS");
        viewPagerAdapter.addFragment(new Menu_Fragment(),"MAIN COURSE");
        viewPagerAdapter.addFragment(new Menu_Fragment(),"DESERT");
        viewPager.setAdapter(viewPagerAdapter);
    }





}
