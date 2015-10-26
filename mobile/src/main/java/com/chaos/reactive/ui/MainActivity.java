package com.chaos.reactive.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chaos.reactive.R;
import com.chaos.reactive.events.Event;
import com.chaos.reactive.presenters.ActivityMainPresenter;
import com.chaos.reactive.ui.adapters.BaseListAdapter;
import com.chaos.reactive.ui.adapters.EventListAdapter;
import com.chaos.reactive.ui.views.FragmentEventView;

import java.util.List;

public class MainActivity extends ActionBarRecyclerActivity<ActivityMainPresenter, Event, EventListAdapter.ViewHolder> implements FragmentEventView {

    @NonNull
    @Override
    protected ActivityMainPresenter createPresenter() {
        return ActivityMainPresenter.create(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected BaseListAdapter<Event, EventListAdapter.ViewHolder> createAdapter() {
        return new EventListAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                presenter.addFragment();
                return true;

            case R.id.remove:
                presenter.removeFragment();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public Fragment getFragment(String tag) {
        return getSupportFragmentManager()
                .findFragmentByTag(tag);
    }

    @Override
    public List<Fragment> getFragments() {
        return getSupportFragmentManager()
                .getFragments();
    }

    @Override
    public void addFragment(Fragment fragment) {
        String tag = fragment.getClass().getName();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame, fragment, tag)
                .commit();
    }

    @Override
    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    @Override
    public void registerEvent(Event event) {
        add(event);
    }
}
