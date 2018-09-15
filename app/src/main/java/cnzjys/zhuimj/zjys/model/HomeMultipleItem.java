package cnzjys.zhuimj.zjys.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import cnzjys.zhuimj.zjys.model.bean.HomeBean;

public class HomeMultipleItem implements MultiItemEntity{

    public static final int BANNER = 1;
    public static final int BROADCAST = 2;
    public static final int VIP_HEADER = 3;
    public static final int VIP = 4;
    public static final int AD = 5;

    public static final int BANNER_SPAN_SIZE = 4;
    public static final int BROADCAST_SPAN_SIZE = 4;
    public static final int VIP_HEADER_SPAN_SIZE = 4;
    public static final int VIP_SPAN_SIZE = 1;
    public static final int AD_SPAN_SIZE = 4;

    private int itemType;
    private int spanSize;
    private List<HomeBean> mData;

    public HomeMultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public HomeMultipleItem(int itemType, int spanSize, List<HomeBean> bean) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        mData = bean;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public List<HomeBean> getData() {
        return mData;
    }

    public void setData(List<HomeBean> mData) {
        this.mData = mData;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
