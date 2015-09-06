package com.tony.launcher.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tony.launcher.fragment.LauncherBaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter{

    private List<LauncherBaseFragment> list;

    public BaseFragmentAdapter(FragmentManager fm, List<LauncherBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
