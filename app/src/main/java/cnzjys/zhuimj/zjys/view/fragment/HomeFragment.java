package cnzjys.zhuimj.zjys.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.adapter.HomeRvAdapter;
import cnzjys.zhuimj.zjys.constant.Constant;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;
import cnzjys.zhuimj.zjys.model.bean.BannerBean;
import cnzjys.zhuimj.zjys.model.bean.BroadcastBean;
import cnzjys.zhuimj.zjys.model.bean.HomeAdBean;
import cnzjys.zhuimj.zjys.model.bean.HomeBean;
import cnzjys.zhuimj.zjys.model.bean.VideoStationBean;
import cnzjys.zhuimj.zjys.model.entity.BroadcastEntity;
import cnzjys.zhuimj.zjys.model.entity.HomeAdEntity;
import cnzjys.zhuimj.zjys.model.entity.HomeBannerEntity;
import cnzjys.zhuimj.zjys.model.entity.HomePageEntity;
import cnzjys.zhuimj.zjys.model.entity.VideoStationEntity;
import cnzjys.zhuimj.zjys.model.network.NetWork;

import cnzjys.zhuimj.zjys.utils.StateBarUtils;
import cnzjys.zhuimj.zjys.view.custom_view.DividerGridItemDecoration;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends Fragment implements OnBannerListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView mHomeRv;
    private List<HomeBean> mHomeBeanList = new ArrayList<>();
    private List<HomeMultipleItem> mMultipleItems = new ArrayList<>();
    private Context mContext;
    private HomeRvAdapter mHomeRvAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View mView;
    private ViewGroup mSearchPanel;

    //List<String> mBannerImgList = new ArrayList<>();
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        initView(mView);
        getHomePageData();
        return mView;
    }


    private void initView(View view){
        mSearchPanel = view.findViewById(R.id.fm_home_search_layout);
        initHomeRv(view);
    }


    private void initHomeRv(View view){
       mHomeRv = view.findViewById(R.id.fm_home_rv);
       mHomeRv.setLayoutManager(new GridLayoutManager(mContext, 4));
       mHomeRv.addItemDecoration(new DividerGridItemDecoration(mContext));

       mHomeRvAdapter = new HomeRvAdapter(mMultipleItems);
       mHomeRvAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = mMultipleItems.get(position).getItemType();
                switch (type){
                    case HomeMultipleItem.BANNER:
                        return HomeMultipleItem.BANNER_SPAN_SIZE;
                    case HomeMultipleItem.BROADCAST:
                        return HomeMultipleItem.BROADCAST_SPAN_SIZE;
                    case HomeMultipleItem.VIP_HEADER:
                        return HomeMultipleItem.VIP_HEADER_SPAN_SIZE;
                    case HomeMultipleItem.VIP:
                        return HomeMultipleItem.VIP_SPAN_SIZE;
                    case HomeMultipleItem.AD:
                        return HomeMultipleItem.AD_SPAN_SIZE;
                }
                return 0;
            }
       });
       mHomeRv.setAdapter(mHomeRvAdapter);
    }

    private void getHomePageData(){
        NetWork.getInstance().startNetWork().getHomePage()
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePageEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomePageEntity homePageEntity) {
                        mMultipleItems.add(new HomeMultipleItem(HomeMultipleItem.BANNER,
                                HomeMultipleItem.BANNER_SPAN_SIZE, mHomeBeanList));
                        mMultipleItems.add(new HomeMultipleItem(HomeMultipleItem.BROADCAST,
                                HomeMultipleItem.BANNER_SPAN_SIZE, mHomeBeanList));
                        mMultipleItems.add(new HomeMultipleItem(HomeMultipleItem.VIP_HEADER,
                                HomeMultipleItem.VIP_SPAN_SIZE));
                        for (int i = 0; i < Constant.VIDEO_STATION_SIZE; i++) {
                            mMultipleItems.add(new HomeMultipleItem(HomeMultipleItem.VIP,
                                    HomeMultipleItem.VIP_SPAN_SIZE, mHomeBeanList));
                        }
                        mMultipleItems.add(new HomeMultipleItem(HomeMultipleItem.AD,
                                HomeMultipleItem.AD_SPAN_SIZE, mHomeBeanList));

                        List<HomeBannerEntity> bannerEntities = homePageEntity.getHomeBannerList();
                        for (HomeBannerEntity bannerEntity : bannerEntities){
                            BannerBean bannerBean = new BannerBean();
                            bannerBean.setBannerUrl(bannerEntity.getContentUrl());
                            bannerBean.setBannerImageUrl(bannerEntity.getBannerUrl());
                            mHomeBeanList.add(bannerBean);
                        }

                        List<BroadcastEntity> broadcastEntities = homePageEntity.getBroadcastList();
                        for (BroadcastEntity broadcastEntity : broadcastEntities){
                            BroadcastBean broadcastBean = new BroadcastBean();
                            broadcastBean.setContent(broadcastEntity.getContent());
                            broadcastBean.setContentUrl(broadcastEntity.getContentUrl());
                            mHomeBeanList.add(broadcastBean);
                        }

                        List<VideoStationEntity> videoStationEntities =
                                homePageEntity.getVideoStationList();
                        for (VideoStationEntity videoStationEntity : videoStationEntities){
                            VideoStationBean videoStationBean = new VideoStationBean();
                            videoStationBean.setVideoStationUrl(videoStationEntity.getVideoStationUrl());
                            videoStationBean.setVideoStationName(videoStationEntity.getVideoStationName());
                            videoStationBean.setVideoStationIcon(videoStationEntity.getVideoStationIcon());
                            mHomeBeanList.add(videoStationBean);
                        }

                        List<HomeAdEntity> homeAdEntities = homePageEntity.getHomeAdList();
                        for (HomeAdEntity homeAdEntity : homeAdEntities){
                            HomeAdBean homeAdBean = new HomeAdBean();
                            homeAdBean.setAdImageUrl(homeAdEntity.getAdImageUrl());
                            homeAdBean.setAdUrl(homeAdEntity.getAdUrl());
                            mHomeBeanList.add(homeAdBean);
                        }
                        mHomeRvAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void OnBannerClick(int position) {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
