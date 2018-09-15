package cnzjys.zhuimj.zjys.model.entity;

import java.util.List;

public class HomePageEntity {

    private List<HomeBannerEntity> homeBannerList;
    private List<BroadcastEntity> broadcastList;
    private List<VideoStationEntity> videoStationList;
    private List<HomeAdEntity> homeAdList;

    public List<HomeBannerEntity> getHomeBannerList() {
        return homeBannerList;
    }

    public void setHomeBannerList(List<HomeBannerEntity> homeBannerList) {
        this.homeBannerList = homeBannerList;
    }

    public List<VideoStationEntity> getVideoStationList() {
        return videoStationList;
    }

    public void setVideoStationList(List<VideoStationEntity> videoStationList) {
        this.videoStationList = videoStationList;
    }

    public List<BroadcastEntity> getBroadcastList() {
        return broadcastList;
    }

    public void setBroadcastList(List<BroadcastEntity> broadcastList) {
        this.broadcastList = broadcastList;
    }

    public List<HomeAdEntity> getHomeAdList() {
        return homeAdList;
    }

    public void setHomeAdList(List<HomeAdEntity> homeAdList) {
        this.homeAdList = homeAdList;
    }
}
