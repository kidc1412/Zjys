package cnzjys.zhuimj.zjys.model.bean;

import cnzjys.zhuimj.zjys.model.HomeMultipleItem;

public class HomeAdBean extends HomeBean{
    private String adImageUrl;
    private String adUrl;

    public HomeAdBean() {
        setBEAN_TYPE(HomeMultipleItem.AD);
    }

    public String getAdImageUrl() {
        return adImageUrl;
    }

    public void setAdImageUrl(String adImageUrl) {
        this.adImageUrl = adImageUrl;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }
}
