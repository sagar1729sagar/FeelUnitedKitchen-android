package ssapps.com.feelunitedkitchen;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

import Adapters.MenuItemsAdapter;
import Adapters.ViewPagerAdapter;
import DataBaseUtility.MenuDB;
import Models.Item;
import Util.Preferences;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by sagar on 29/05/17
 */
public class Kitchen_Fragment extends Fragment {

 //   private ViewPager viewPager;
    private TabLayout tabLayout;
    private Preferences prefs;
    private MenuDB db;
  //  private FeatureCoverFlow mCoverFlow;
  //  private MenuItemsAdapter mAdapter;
    private FloatingActionButton refresh;
    private SweetAlertDialog dialog;
    private ArrayList<Item> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kitchen_fragment,container,false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        viewPager = (ViewPager)view.findViewById(R.id.viewpager_kitchen);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_kitchen);
        tabLayout.addTab(tabLayout.newTab().setText("STARTERS"));
        tabLayout.addTab(tabLayout.newTab().setText("MAIN COURSE"));
        tabLayout.addTab(tabLayout.newTab().setText("DESERTS"));

        dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.circleColor, null));
        }
        dialog.setTitleText("Updating Menu ....");
        dialog.setCancelable(false);

        db = new MenuDB(getContext());

        refresh = (FloatingActionButton) view.findViewById(R.id.fab_menu);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMenuData(view);
            }
        });


        // set cover flow
//        items = db.getMenuItems();
//        if (items.size() == 0) {
//            ArrayList<Item> placeholder = new ArrayList<>();
//            placeholder.add(placeHolderItem());
//            setMenuDisplay(view, placeholder);
//            getMenuData(view);
//        } else {
//            setMenuDisplay(view, items);
//        }

    }


//
//        setupViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager);
//
//        prefs = new Preferences(getContext());
//
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//           // prefs.setCurrentView(position+1);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });



 //   }

//
//    private void setupViewPager(ViewPager viewPager) {
//
//        // configure view pager
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
//        viewPagerAdapter.addFragment(new Menu_Fragment(),"STARTERS");
//        viewPagerAdapter.addFragment(new Menu_Fragment(),"MAIN COURSE");
//        viewPagerAdapter.addFragment(new Menu_Fragment(),"DESERT");
//        viewPager.setAdapter(viewPagerAdapter);
//    }

    private void getMenuData(final View view){
        dialog.show();
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize(100);
        Backendless.Data.of(Item.class).find(queryBuilder, new AsyncCallback<List<Item>>() {
            @Override
            public void handleResponse(List<Item> response) {
                dialog.dismiss();
                db.resetDB();
                if (response.size() == 0){
                    Toast.makeText(getContext(), "No items in menu", Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < response.size(); i++) {
                        db.addMenuItem(response.get(i));

                    }
                 //   setMenuDisplay(view,items);
                }
                Log.v("Item count ", String.valueOf(db.getCount()));
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                dialog.dismiss();
                Log.v("fetch fault", fault.getMessage());
                new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Couldnt update menu!!!")
                        .setContentText("The following error has occurred while trying to update the menu\n"+fault.getMessage()+"\nPlease try again")
                        .show();
            }
        });
    }
//
//    private void setMenuDisplay(View view,ArrayList<Item> data){
//        mCoverFlow = (FeatureCoverFlow)view.findViewById(R.id.coverflow_menu);
//        mAdapter = new MenuItemsAdapter(data,getContext());
//        mCoverFlow.setAdapter(mAdapter);
//    }

    private Item placeHolderItem(){

        Item item = new Item();
        item.setItemName("Place holder item");
        item.setItemUrl("test");
        item.setPriceToday("0");
        item.setPriceTomorrow("0");
        item.setPriceLater("0");
        item.setAvailableMonday("0");
        item.setAvailableTuesday("0");
        item.setAvailableWedenesday("0");
        item.setAvailableThrusday("0");
        item.setAvailableFriday("0");
        item.setAvailableSaturday("0");
        item.setAvailableSunday("0");
        item.setItemCategeory("Starters");
        item.setFoodType("Veg");
        item.setItemDescription("This is just a placeholder item so that the app doesnt come down crashing in case of empty menu data");
        return item;
    }


}
