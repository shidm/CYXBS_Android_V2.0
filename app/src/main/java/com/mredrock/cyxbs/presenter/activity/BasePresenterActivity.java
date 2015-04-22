package com.mredrock.cyxbs.presenter.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.mredrock.cyxbs.presenter.IPresenter;
import com.mredrock.cyxbs.presenter.activity.swipebacklayout.app.SwipeBackActivity;
import com.mredrock.cyxbs.view.IVu;

import de.greenrobot.event.EventBus;

/**
 * @param <V>
 */
public abstract class BasePresenterActivity<V extends IVu> extends SwipeBackActivity implements IPresenter<V>{

    protected V vu;
    protected FragmentManager fm;
    protected EventBus bus;


    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        bus = EventBus.getDefault();
        try {
            vu = getVuClass().newInstance();
            vu.init(getLayoutInflater(), null);
            setContentView(vu.getView());
            onBindVu();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        setSwipeBackEnable(shouldSwipeBack());
    }

    @Override
    protected final void onPause() {
        beforePause();
        super.onPause();
    }

    protected void beforePause() {
    }

    @Override
    protected final void onResume() {
        super.onResume();
        afterResume();
    }

    protected void afterResume() {
    }

    @Override
    protected final void onDestroy() {
        onDestroyVu();
        vu = null;
        super.onDestroy();
    }

    @Override
    public final void onBackPressed() {
        if (!handleBackPressed()) {
            super.onBackPressed();
        }
    }

    public boolean handleBackPressed() {
        return false;
    }

    protected abstract boolean shouldSwipeBack();

    @Override
    public void onDestroyVu() {

    }

}
