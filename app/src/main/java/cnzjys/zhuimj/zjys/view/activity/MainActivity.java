package cnzjys.zhuimj.zjys.view.activity;


import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.adapter.ContentVpAdapter;
import cnzjys.zhuimj.zjys.utils.StateBarUtils;
import cnzjys.zhuimj.zjys.view.fragment.HomeFragment;
import cnzjys.zhuimj.zjys.view.fragment.HotFragment;
import cnzjys.zhuimj.zjys.view.fragment.MeFragment;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        HotFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener, View.OnClickListener{

    private ViewPager mViewPager;
    private HomeFragment mHomeFragment;
    private HotFragment mHotFragment;
    private MeFragment mMeFragment;
    private List<Fragment> mFragmentList;
    private ContentVpAdapter mContentVpAdapter;
    private RadioButton mHomeTabRb;
    private RadioButton mHotTabRb;
    private RadioButton mMeTabRb;
    private View mStateBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StateBarUtils.transparentStateBar(this);
        initView();
        initListener();
    }

    private void initView(){
        mStateBar = findViewById(R.id.main_state_bar_view);
        ViewGroup.LayoutParams stateBarParams = mStateBar.getLayoutParams();
        stateBarParams.height = StateBarUtils.getStatusBarHeight(this);
        mStateBar.setLayoutParams(stateBarParams);

        mHomeTabRb = findViewById(R.id.home_tab_rb);
        mHotTabRb = findViewById(R.id.hot_tab_rb);
        mMeTabRb = findViewById(R.id.me_tab_rb);

        initViewPager();
    }

    private void initViewPager(){
        mViewPager = findViewById(R.id.main_view_pager);
        mViewPager.setOffscreenPageLimit(3);

        mFragmentList = new ArrayList<>();
        mHomeFragment = new HomeFragment();
        mHotFragment = new HotFragment();
        mMeFragment = new MeFragment();

        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mHotFragment);
        mFragmentList.add(mMeFragment);

        mContentVpAdapter = new ContentVpAdapter(getSupportFragmentManager());
        mContentVpAdapter.setFragments(mFragmentList);

        mViewPager.setAdapter(mContentVpAdapter);
    }

    private void initListener(){
        mHomeTabRb.setOnClickListener(this);
        mHotTabRb.setOnClickListener(this);
        mMeTabRb.setOnClickListener(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_tab_rb:
                mViewPager.setCurrentItem(0);
                mHomeTabRb.setChecked(true);
                break;
            case R.id.hot_tab_rb:
                mViewPager.setCurrentItem(1);
                mHotTabRb.setChecked(true);
                break;
            case R.id.me_tab_rb:
                mViewPager.setCurrentItem(2);
                mMeTabRb.setChecked(true);
                break;

        }
    }
}
