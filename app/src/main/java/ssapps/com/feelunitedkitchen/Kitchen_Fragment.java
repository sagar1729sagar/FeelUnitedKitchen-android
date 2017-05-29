package ssapps.com.feelunitedkitchen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import Adapters.ViewPagerAdapter;

/**
 * Created by sagar on 29/05/17.
 */
public class Kitchen_Fragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;


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

    }

    private void setupViewPager(ViewPager viewPager) {

        // configure view pager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new Menu_Fragment(),"HOME");
        viewPagerAdapter.addFragment(new Cart_Fragment(),"CART");
        viewPagerAdapter.addFragment(new Active_Orders_Fragment(),"ACTIVE ORDERS");
        viewPagerAdapter.addFragment(new Delivered_Orders_Fragment(),"DELIVERED ORDERS");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
