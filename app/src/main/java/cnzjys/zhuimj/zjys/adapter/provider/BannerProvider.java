package cnzjys.zhuimj.zjys.adapter.provider;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;
import cnzjys.zhuimj.zjys.model.bean.BannerBean;
import cnzjys.zhuimj.zjys.model.bean.HomeBean;

public class BannerProvider extends BaseItemProvider<HomeMultipleItem, BaseViewHolder> {
    private Banner mBanner;
    private List<String> mBannerImgList = new ArrayList<>();

    @Override
    public int viewType() {
        return HomeMultipleItem.BANNER;
    }

    @Override
    public int layout() {
        return R.layout.banner_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultipleItem data, int position) {

        if (!data.getData().isEmpty()) {
            initBanner(helper.getView(R.id.home_fragment_banner));

            for (HomeBean homeBean : data.getData()){
                if (homeBean.getBEAN_TYPE() == HomeMultipleItem.BANNER){
                    BannerBean bannerBean = (BannerBean) homeBean;
                    mBannerImgList.add(bannerBean.getBannerImageUrl());
                }
            }
            //设置图片集合
            mBanner.setImages(mBannerImgList);
            //banner设置方法全部调用完毕时最后调用
            mBanner.start();
        }

    }

    private void initBanner(View view){
        mBanner = (Banner) view;
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1800);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
    }

    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

}
