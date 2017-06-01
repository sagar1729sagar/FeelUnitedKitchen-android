package ssapps.com.feelunitedkitchen;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

import DataBaseUtility.MenuDB;
import Models.Item;
import Util.Preferences;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by sagar on 29/05/17.
 */
public class Menu_Fragment extends Fragment {
    private Preferences prefs;
    private MenuDB db;
    private FloatingActionButton refresh;
    private SweetAlertDialog dialog;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment,container,false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Preferences(getContext());
        db = new MenuDB(getContext());
        dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.PROGRESS_TYPE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.circleColor,null));
        }
        dialog.setTitleText("Updating Menu ....");
        dialog.setCancelable(false);

        refresh = (FloatingActionButton)view.findViewById(R.id.fab_menu);
        refresh.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMenuData();
            }
        });
    }

    private void getMenuData(){
        dialog.show();
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize(100);
        Backendless.Data.of(Item.class).find(queryBuilder, new AsyncCallback<List<Item>>() {
            @Override
            public void handleResponse(List<Item> response) {
                dialog.dismiss();
                db.resetDB();
                for (int i=0 ; i<response.size();i++){
                    db.addMenuItem(response.get(i));

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


//        private void getMenuData(){
////        int check = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
////        if (check != -1) {
////            prefs.setInternetPermissionStatus(true);
////        }
//       // Log.v("getm menu data","called");
////        if (!prefs.isInternetPermmisionAvailable()){
////            Log.v("Internet permission","Not available");
////            askInternetPermission();
////        } else {
//          //  Log.v("Internet permission","Not available");
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
//
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

    }
