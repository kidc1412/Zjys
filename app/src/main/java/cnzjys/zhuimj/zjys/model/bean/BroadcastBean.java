package cnzjys.zhuimj.zjys.model.bean;

import cnzjys.zhuimj.zjys.model.HomeMultipleItem;

public class BroadcastBean extends HomeBean{
    private String content;
    private String contentUrl;

    public BroadcastBean() {
        setBEAN_TYPE(HomeMultipleItem.BROADCAST);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }
}
