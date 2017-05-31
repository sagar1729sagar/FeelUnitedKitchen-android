package ssapps.com.feelunitedkitchen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Util.Preferences;

/**
 * Created by sagar on 29/05/17.
 */
public class Login_Fragment extends Fragment {
    private Preferences prefs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragement,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Preferences(getContext());
       //SETCURRE prefs.setCurrentView(6);
    }
}
