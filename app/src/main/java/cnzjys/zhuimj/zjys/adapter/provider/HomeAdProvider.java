package cnzjys.zhuimj.zjys.adapter.provider;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;
import cnzjys.zhuimj.zjys.model.bean.HomeAdBean;
import cnzjys.zhuimj.zjys.model.bean.HomeBean;

public class HomeAdProvider extends BaseItemProvider<HomeMultipleItem, BaseViewHolder>{

    @Override
    public int viewType() {
        return HomeMultipleItem.AD;
    }

    @Override
    public int layout() {
        return R.layout.home_ad_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultipleItem data, int position) {
        if (!data.getData().isEmpty()){
            for (HomeBean homeBean : data.getData()){
                if (homeBean.getBEAN_TYPE() == HomeMultipleItem.AD){
                    HomeAdBean homeAdBean = (HomeAdBean) homeBean;
                    ImageView homeAdIv = helper.getView(R.id.home_ad_iv);
//                    RequestOptions requestOptions = new RequestOptions().fitCenter();
                    Glide.with(mContext).load(homeAdBean.getAdImageUrl())
                            .into(homeAdIv);
                }
            }

        }

    }
}
