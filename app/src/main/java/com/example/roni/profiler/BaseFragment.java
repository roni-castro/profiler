package com.example.roni.profiler;

import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by roni on 02/02/18.
 */

public abstract class BaseFragment extends Fragment {

    public void showToast(int stringResId) {
        Toast.makeText(getActivity(), stringResId, Toast.LENGTH_LONG).show();
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }
}
