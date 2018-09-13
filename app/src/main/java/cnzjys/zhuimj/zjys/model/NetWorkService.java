package cnzjys.zhuimj.zjys.model;

import java.util.List;

import cnzjys.zhuimj.zjys.model.entity.HomeBannerEntity;
import cnzjys.zhuimj.zjys.model.entity.HomePageEntity;
import cnzjys.zhuimj.zjys.model.entity.VideoStationEntity;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface NetWorkService {

    /**
     * 更新APP
     * @param url 更新软件的APP链接
     * @return ResponseBody
     */
    @Streaming
    @GET
    Observable<ResponseBody> updateApp(@Url String url);

    /**
     * 获取首页滚动广告
     * @return 滚动广告的数据列表
     */
    @GET("/get_home_banner")
    Observable<List<HomeBannerEntity>> getHomeBanner();

    /**
     * 获取首页视频站信息
     * @return 视频站的图标以及名字
     */
    @GET("/get_video_station")
    Observable<List<VideoStationEntity>> getVideoStation();

    @GET("/get_home_page")
    Observable<HomePageEntity> getHomePage();

}
