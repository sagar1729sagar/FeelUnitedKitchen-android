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
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

import Adapters.MenuItemsAdapter;
import DataBaseUtility.MenuDB;
import Models.Item;
import Util.Preferences;
import cn.pedant.SweetAlert.SweetAlertDialog;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


/**
 * Created by sagar on 29/05/17.
 */
public class Menu_Fragment extends Fragment {
    private Preferences prefs;
    private MenuDB db;
    private FloatingActionButton refresh;
    private SweetAlertDialog dialog;
    private FeatureCoverFlow mCoverFlow;
    private MenuItemsAdapter mAdapter;
    private ArrayList<Item> items = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment,container,false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Preferences(getContext());
        db = new MenuDB(getContext());
        dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.PROGRESS_TYPE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dialog.getProgressHelper().setBarColor(getResources().getColor(R.color.circleColor,null));
        }
        dialog.setTitleText("Updating Menu ....");
        dialog.setCancelable(false);
        // set refresh button
        refresh = (FloatingActionButton)view.findViewById(R.id.fab_menu);
        refresh.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark,null));
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMenuData(view);
            }
        });

        // set cover flow
        items = db.getMenuItems();
        if (items.size() == 0){
            getMenuData(view);
        } else {
            setMenuDisplay(view);
        }

    }

    private void setMenuDisplay(View view){
        mCoverFlow = (FeatureCoverFlow)view.findViewById(R.id.coverflow_menu);
        mAdapter = new MenuItemsAdapter(items,getContext());
        mCoverFlow.setAdapter(mAdapter);
    }

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
                    Toast.makeText(getContext(),"No items in menu",Toast.LENGTH_SHORT).show();
                } else {
                    for (int i = 0; i < response.size(); i++) {
                        db.addMenuItem(response.get(i));

                    }
                    setMenuDisplay(view);
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


    }