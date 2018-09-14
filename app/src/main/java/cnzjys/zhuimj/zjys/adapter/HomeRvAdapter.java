package cnzjys.zhuimj.zjys.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import java.util.List;
import cnzjys.zhuimj.zjys.adapter.provider.BannerProvider;
import cnzjys.zhuimj.zjys.adapter.provider.BroadcastProvider;
import cnzjys.zhuimj.zjys.adapter.provider.VideoStationProvider;
import cnzjys.zhuimj.zjys.adapter.provider.VipHeaderProvider;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;


public class HomeRvAdapter extends MultipleItemRvAdapter<HomeMultipleItem, BaseViewHolder>{
    private static final int BANNER = HomeMultipleItem.BANNER;
    private static final int BROADCAST = HomeMultipleItem.BROADCAST;
    private static final int VIP_HEADER = HomeMultipleItem.VIP_HEADER;
    private static final int VIP = HomeMultipleItem.VIP;

    public HomeRvAdapter(@Nullable List<HomeMultipleItem> data) {
        super(data);

        finishInitialize();
    }

    @Override
    protected int getViewType(HomeMultipleItem item) {
        //根据实体类判断并返回对应的viewType，具体判断逻辑因业务不同，这里这是简单通过判断type属性
        //According to the entity class to determine and return the corresponding viewType,
        //the specific judgment logic is different because of the business, here is simply by judging the type attribute
        switch (item.getItemType()){
            case HomeMultipleItem.BANNER:
                return BANNER;
            case HomeMultipleItem.BROADCAST:
                return BROADCAST;
            case HomeMultipleItem.VIP_HEADER:
                return VIP_HEADER;
            case HomeMultipleItem.VIP:
                return VIP;
        }
        return 0;
    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        //Register related entries provider
        mProviderDelegate.registerProvider(new BannerProvider());
        mProviderDelegate.registerProvider(new BroadcastProvider());
        mProviderDelegate.registerProvider(new VipHeaderProvider());
        mProviderDelegate.registerProvider(new VideoStationProvider());

    }



}
