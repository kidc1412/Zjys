package cnzjys.zhuimj.zjys.model.bean;

import cnzjys.zhuimj.zjys.model.HomeMultipleItem;

public class BannerBean extends HomeBean{

    private String mBannerUrl;
    private String mBannerImageUrl;


    public BannerBean() {
        setBEAN_TYPE(HomeMultipleItem.BANNER);
    }

    public String getBannerUrl() {
        return mBannerUrl;
    }

    public void setBannerUrl(String mBannerUrl) {
        this.mBannerUrl = mBannerUrl;
    }

    public String getBannerImageUrl() {
        return mBannerImageUrl;
    }

    public void setBannerImageUrl(String mBannerImageUrl) {
        this.mBannerImageUrl = mBannerImageUrl;
    }
}
