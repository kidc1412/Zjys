package cnzjys.zhuimj.zjys.adapter.provider;


import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;

public class VipHeaderProvider extends BaseItemProvider<HomeMultipleItem, BaseViewHolder>{

    @Override
    public int viewType() {
        return HomeMultipleItem.VIP_HEADER;
    }

    @Override
    public int layout() {
        return R.layout.video_station_header;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultipleItem data, int position) {

    }
}
