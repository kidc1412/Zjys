package cnzjys.zhuimj.zjys.model.bean;

import cnzjys.zhuimj.zjys.model.HomeMultipleItem;

public class VideoStationBean extends HomeBean{

    private String videoStationIcon;
    private String videoStationName;
    private String videoStationUrl;

    public VideoStationBean() {
        setBEAN_TYPE(HomeMultipleItem.VIP);
    }

    public String getVideoStationIcon() {
        return videoStationIcon;
    }

    public void setVideoStationIcon(String videoStationIcon) {
        this.videoStationIcon = videoStationIcon;
    }

    public String getVideoStationName() {
        return videoStationName;
    }

    public void setVideoStationName(String videoStationName) {
        this.videoStationName = videoStationName;
    }

    public String getVideoStationUrl() {
        return videoStationUrl;
    }

    public void setVideoStationUrl(String videoStationUrl) {
        this.videoStationUrl = videoStationUrl;
    }
}
