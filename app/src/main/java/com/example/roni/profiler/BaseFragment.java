package com.example.roni.profiler;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by roni on 02/02/18.
 */

public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;

    public abstract int getFragmentViewResId();

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getFragmentViewResId(), container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    public void showToast(int stringResId) {
        Toast.makeText(getActivity().getApplication(), stringResId, Toast.LENGTH_LONG).show();
    }

    public void showToast(String message) {
        Toast.makeText(getActivity().getApplication(), message, Toast.LENGTH_LONG).show();
    }
}
