package cnzjys.zhuimj.zjys.adapter.provider;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import java.util.ArrayList;
import java.util.List;
import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.constant.Constant;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;
import cnzjys.zhuimj.zjys.model.bean.HomeBean;
import cnzjys.zhuimj.zjys.model.bean.VideoStationBean;
import cnzjys.zhuimj.zjys.view.activity.WebViewActivity;

public class VideoStationProvider extends BaseItemProvider<HomeMultipleItem, BaseViewHolder>{
    private List<VideoStationBean> videoStationBeans = new ArrayList<>();
    //videoStationBeans的偏移量 position不一定正确 因此需要进行计算
    private int posOffset = 0;
    private int count = 0;

    @Override
    public int viewType() {
        return HomeMultipleItem.VIP;
    }

    @Override
    public int layout() {
        return R.layout.vip_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultipleItem data, int position) {
        //因为是异步下载 所以在初始化的时候data是为空的
        if (!data.getData().isEmpty()) {
            int currentPos = 0;
            /*先将在HomeBean中的VideoStationBean遍历并转换出来 然后再存入VideoStationBean集合
              然后便于使用当前的position获取正确的数据 填充到span中*/
            for (HomeBean homeBean : data.getData()){
                if (homeBean.getBEAN_TYPE() == HomeMultipleItem.VIP){
                    //如果count为0 说明此时position还是第一个 此时的position就是偏移量
                    posOffset = count == 0 ? position : posOffset;
                    /*减去偏移量 才能拿到正确的videoStationBeans的pos 因为position可能不准确
                    比如有可能position为3那么你减去3 你才能利用videoStationBeans.get(0)
                    拿到这个集合的第一个元素*/
                    currentPos = position - posOffset;
                    VideoStationBean vsBean = (VideoStationBean) homeBean;
                    if (videoStationBeans.size() < Constant.VIDEO_STATION_SIZE) {
                        videoStationBeans.add(vsBean);
                    }
                    count++;
                }
            }
            if (!videoStationBeans.isEmpty()) {
                helper.setText(R.id.video_station_name_tv, videoStationBeans.get(currentPos)
                        .getVideoStationName());
                ImageView videoStationIv = helper.getView(R.id.video_station_icon_iv);
                Glide.with(mContext).load(videoStationBeans.get(currentPos).getVideoStationIcon())
                        .into(videoStationIv);
            }
        }

    }

    @Override
    public void onClick(BaseViewHolder helper, HomeMultipleItem data, int position) {
        super.onClick(helper, data, position);
        //点击事件发生时候的position可能不是准确的 此时需要减去偏移量 才能得到正确的position
        startToWebActivity(position - posOffset);
    }

    /**
     * 跳转到内置浏览器
     * @param pos 视频站列表的position
     */
    private void startToWebActivity(int pos){
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra(Constant.WEB_URL, videoStationBeans.get(pos).getVideoStationUrl());
        intent.putExtra(Constant.CURRENT_WEB_NAME, videoStationBeans.get(pos)
                .getVideoStationName());
        mContext.startActivity(intent);
    }

}
