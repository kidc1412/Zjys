package cnzjys.zhuimj.zjys.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContentVpAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> mFragments;

    public ContentVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> mFragments){
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
