package com.scavi.de.gw2imp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scavi.de.gw2imp.R;
import com.scavi.de.gw2imp.application.IApplication;
import com.scavi.de.gw2imp.dagger2.component.ApplicationComponent;
import com.scavi.de.gw2imp.dagger2.component.DaggerOverviewComponent;
import com.scavi.de.gw2imp.dagger2.module.OverviewModule;
import com.scavi.de.gw2imp.presenter.OverviewPresenter;
import com.scavi.de.gw2imp.ui.view.IOverviewView;

import javax.inject.Inject;

public class OverviewFragment extends AbstractFragment implements IOverviewView {
    @Inject
    OverviewPresenter mPresenter;


    /**
     * Called to have the fragment instantiate its user interface view. This is optional, and
     * non-graphical fragments can return null (which is the default implementation). This will
     * be called between onCreate(Bundle) and onActivityCreated(Bundle).
     *
     * @param inflater           LayoutInflater: The LayoutInflater object that can be used to
     *                           inflate any views in the fragment,
     * @param container          ViewGroup: If non-null, this is the parent view that the
     *                           fragment's UI should be attached to. The fragment should not add
     *                           the view itself, but this can be used to generate the
     *                           LayoutParams of the view.
     * @param savedInstanceState Bundle: If non-null, this fragment is being re-constructed from
     *                           a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        injectComponent(((IApplication) getContext().getApplicationContext()).getComponent());
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }


    /**
     * This method will be called to setup the dagger module of the current activity
     *
     * @param applicationComponent the application component
     */
    private void injectComponent(final ApplicationComponent applicationComponent) {
        OverviewModule module = new OverviewModule(this);
        DaggerOverviewComponent.builder()
                .applicationComponent(applicationComponent)
                .overviewModule(module)
                .build()
                .inject(this);
    }


    /**
     *
     */
    @Override
    public void onShowProgress() {
        // TODO
    }


    /**
     *
     */
    @Override
    public void onHideProgress() {
        // TODO
    }


    /**
     *
     */
    @Override
    public void onHideProgressAfterError() {
        // TODO
    }
}