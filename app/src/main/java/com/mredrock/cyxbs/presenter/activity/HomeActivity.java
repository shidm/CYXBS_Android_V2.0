package com.mredrock.cyxbs.presenter.activity;

import com.mredrock.cyxbs.R;
import com.mredrock.cyxbs.presenter.fragment.NavigationDrawerFragment;
import com.mredrock.cyxbs.presenter.fragment.ScheduleContainerFragment;
import com.mredrock.cyxbs.view.impl.MainVu;


public class HomeActivity extends BasePresenterActivity<MainVu> {
    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected Class<MainVu> getVuClass() {
        return MainVu.class;
    }

    @Override
    protected boolean shouldSwipeBack() {
        return false;
    }

    @Override
    protected void onBindVu() {
        fm.beginTransaction()
                .replace(vu.getContainerId(), ScheduleContainerFragment.newInstance())
                .commit();
        configureToolbar();
        mNavigationDrawerFragment = (NavigationDrawerFragment) fm.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.configureDrawer(vu.getDrawerLayout());
    }

    public void configureToolbar(){
        setSupportActionBar(vu.getToolbar());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}