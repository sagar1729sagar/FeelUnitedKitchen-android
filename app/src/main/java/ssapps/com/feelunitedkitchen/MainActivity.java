package ssapps.com.feelunitedkitchen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

import DataBaseUtility.MenuDB;
import Models.Item;
import Util.Preferences;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // private ImageView refreshImage;
    private Preferences prefs;
  //  private SweetAlertDialog alertDialog;
    private static final String appID = "9D0D0B57-308E-B7E3-FFBE-DD24A0BDD400";
    private static final String appKey = "072A0949-08E5-D05C-FF3A-C91A8C9F4600";
    private static final int INTRNT_PRMSN = 1;
    private MenuDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Feel United Kitchen");
        setSupportActionBar(toolbar);

        Backendless.initApp(getApplicationContext(), appID, appKey);
        db = new MenuDB(this);

        prefs = new Preferences(this);
//        alertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alertDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.circleColor, null));
//        }
//        alertDialog.setTitleText("Updating Menu ....");
//        alertDialog.setCancelable(false);

        // refreshImage = (ImageView)findViewById(R.id.refrsh_image);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Open HomeFragment on Launch

        drawer.post(new Runnable() {
            @Override
            public void run() {
              //  prefs.setCurrentView(1);
                makeScreenTransition(new Kitchen_Fragment());
            }
        });

        //On click listener for refresh image

//        refreshImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                refreshClicked();
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kitchen) {
          //  prefs.setCurrentView(1);
            // refreshImage.setVisibility(View.VISIBLE);
            makeScreenTransition(new Kitchen_Fragment());
        } else if (id == R.id.nav_fb) {
            //  refreshImage.setVisibility(View.VISIBLE);
            makeScreenTransition(new Feedback_fragment());
        } else if (id == R.id.nav_account) {
            //  refreshImage.setVisibility(View.GONE);
            makeScreenTransition(new Login_Fragment());
        } else if (id == R.id.nav_cart) {
            makeScreenTransition(new Cart_Fragment());
        } else if (id == R.id.nav_active_orders) {
            makeScreenTransition(new Active_Orders_Fragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    private void refreshClicked() {
//
//        switch (prefs.isInView()) {
//
//            case 1:
//                Toast.makeText(getApplicationContext(), "Menu called", Toast.LENGTH_SHORT).show();
//                // getMenuData();
//                // alertDialog.show();
//                break;
//            case 2:
//                Toast.makeText(getApplicationContext(), "Cart called", Toast.LENGTH_SHORT).show();
//                break;
//            case 3:
//                Toast.makeText(getApplicationContext(), "Active orders called", Toast.LENGTH_SHORT).show();
//                break;
//            case 4:
//                Toast.makeText(getApplicationContext(), "Delivered called", Toast.LENGTH_SHORT).show();
//                break;
//            case 5:
//                Toast.makeText(getApplicationContext(), "Feedback called", Toast.LENGTH_SHORT).show();
//                break;
//
//        }
//    }

    private void makeScreenTransition(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, fragment);
        ft.commit();

    }
}

//    private void getMenuData(){
//        int check = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
//        if (check != -1) {
//            prefs.setInternetPermissionStatus(true);
//        }
//        Log.v("getm menu data","called");
//        if (!prefs.isInternetPermmisionAvailable()){
//            Log.v("Internet permission","Not available");
//            askInternetPermission();
//        } else {
//            Log.v("Internet permission","Not available");
//            alertDialog.show();
//            DataQueryBuilder queryBuilder = DataQueryBuilder.create();
//            queryBuilder.setPageSize(100);
//            Log.v("fetch about to start", "reached here");
//            Backendless.Data.of(Item.class).find(queryBuilder, new AsyncCallback<List<Item>>() {
//                @Override
//                public void handleResponse(List<Item> response) {
//                    alertDialog.dismiss();
//                  //  Toast.makeText(getApplicationContext(), String.valueOf(response.size()), Toast.LENGTH_SHORT).show();
//                    db.resetDB();
//                    for (int i = 0 ; i < response.size();i++){
////                        Item item = new Item();
////                        item.setItemId(response.get(i).getItemId());
////                        item.setItemName(response.get(i).getItemName());
////                        item.setItemUrl(response.get(i).getItemUrl());
////                        item.setPriceToday(response.get(i).getPriceToday());
////                        item.setPriceTomorrow(response.get(i).getPriceTomorrow());
//                        db.addMenuItem(response.get(i));
//
//
//                    }
//                    Log.v("Item count ", String.valueOf(db.getCount()));
//                }

//                @Override
//                public void handleFault(BackendlessFault fault) {
//                    alertDialog.dismiss();
//                    Log.v("fetch fault", fault.getMessage());
//                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
//                            .setTitleText("Couldn't update Menu!!")
//                            .setContentText("The folowing error has occured while trying to update the menu\n" + fault.getMessage() + "\n Please try again")
//                            .show();
////                    .setTitleText("Couldn't update Menu!!")
////                            .setContentText("The folowing error has occured while trying to update the menu\n" + fault.getMessage() + "\n Please try again")
////                            .
//                }
//            });
//        }
//
//    }


//    private void askInternetPermission(){
//
//       // int check = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
//            // ask for permission
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.INTERNET},INTRNT_PRMSN);
//            }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case INTRNT_PRMSN:
//                if( grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//                    prefs.setInternetPermissionStatus(true);
//                    getMenuData();
//
//                } else {
//
//                    Toast.makeText(getApplicationContext(),"Internet access permission is required to fetch menu",Toast.LENGTH_LONG).show();
//
//                }
//                break;
//
//        }
//    }
//}
