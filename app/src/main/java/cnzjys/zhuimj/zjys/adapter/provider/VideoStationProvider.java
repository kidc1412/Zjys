package cnzjys.zhuimj.zjys.adapter.provider;

import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import java.util.ArrayList;
import java.util.List;
import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;
import cnzjys.zhuimj.zjys.model.bean.HomeBean;
import cnzjys.zhuimj.zjys.model.bean.VideoStationBean;

public class VideoStationProvider extends BaseItemProvider<HomeMultipleItem, BaseViewHolder>{
    private List<VideoStationBean> videoStationBeans = new ArrayList<>();

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
            int pos = helper.getLayoutPosition() - 1;
            /*先将在HomeBean中的VideoStationBean遍历并转换出来 然后再存入VideoStationBean集合
              然后便于使用当前的position获取正确的数据 填充到span中*/
            for (HomeBean homeBean : data.getData()){
                if (homeBean.getBEAN_TYPE() == HomeMultipleItem.VIP){
                    VideoStationBean vsBean = (VideoStationBean) homeBean;
                    videoStationBeans.add(vsBean);
                }
            }
            if (!videoStationBeans.isEmpty()) {
                helper.setText(R.id.video_station_name_tv, videoStationBeans.get(pos)
                        .getVideoStationName());
                ImageView videoStationIv = helper.getView(R.id.video_station_icon_iv);
                Glide.with(mContext).load(videoStationBeans.get(pos).getVideoStationIcon())
                        .into(videoStationIv);
            }
        }

    }

    @Override
    public void onClick(BaseViewHolder helper, HomeMultipleItem data, int position) {
        super.onClick(helper, data, position);
        Toast.makeText(mContext, "点击了" + position + "\n"
                + videoStationBeans.get(position - 1).getVideoStationName(), Toast.LENGTH_SHORT).show();

    }
}
