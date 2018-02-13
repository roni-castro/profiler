package com.example.roni.profiler.ui.profilePage.edit;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.roni.profiler.R;
import com.example.roni.profiler.dataModel.auth.User;
import com.example.roni.profiler.dataModel.database.Profile;
import com.example.roni.profiler.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by roni on 13/02/18.
 */

public class EditProfileFragment extends BaseFragment implements EditProfileContract.AppView{
    @Inject
    EditProfileContract.Presenter<EditProfileContract.AppView> presenter;

    @BindView(R.id.edt_bio)
    EditText bioEditText;

    @BindView(R.id.edt_interests)
    EditText interestsEditText;

    private boolean showConfirmationMenu;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfilePageFragment.
     */
    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public int getFragmentViewResId() {
        return R.layout.fragment_edit_profile;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.confirmation_menu, menu);
        menu.findItem(R.id.confirm_menu_action).setVisible(showConfirmationMenu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm_menu_action:
                presenter.onConfirmUpdateMenuClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void setUpCreatedView(View view) {
        setHasOptionsMenu(true);

        User user = loadUserDataFromCache();
        presenter.getUserProfile(user.getUserUid());
    }

    @Override
    protected void injectViewIntoComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    public void attachViewToPresenter() {
        presenter.onAttach(this);
    }

    @Override
    public void detachViewFromPresenter() {
        presenter.onDetach();
    }

    @Override
    public void updateProfileDataConfirmed() {
        getActivity().finish();
    }

    @Override
    public void setUpProfileData(Profile profile) {
        bioEditText.setText(profile.getBio());
        interestsEditText.setText(profile.getInterests());
    }

    @Override
    public String getInterests() {
        return interestsEditText.getText().toString();
    }

    @Override
    public String getBio() {
        return bioEditText.getText().toString();
    }

    @Override
    public void goBackToProfilePageWithMessage() {
        getActivity().finish();
    }

    @Override
    public void setUpConfirmationMenuVisibilty(boolean showMenu) {
        showConfirmationMenu = showMenu;
        getActivity().invalidateOptionsMenu(); // call onCreateOptions menu again
    }

}
