package com.example.roni.profiler;

/**
 * Created by roni on 01/02/18.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showToast(int stringId);

    void showToast(String message);
}
