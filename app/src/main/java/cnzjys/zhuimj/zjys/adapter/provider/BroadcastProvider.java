package cnzjys.zhuimj.zjys.adapter.provider;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;

import java.util.ArrayList;
import java.util.List;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.model.HomeMultipleItem;
import cnzjys.zhuimj.zjys.model.bean.BroadcastBean;
import cnzjys.zhuimj.zjys.model.bean.HomeBean;

import static android.view.Gravity.CENTER;

public class BroadcastProvider extends BaseItemProvider<HomeMultipleItem, BaseViewHolder>{
    private ViewFlipper mViewFlipper;
    private List<BroadcastBean> mBroadcastBeans = new ArrayList<>();

    @Override
    public int viewType() {
        return HomeMultipleItem.BROADCAST;
    }

    @Override
    public int layout() {
        return R.layout.broadcast_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeMultipleItem data, int position) {
        if (!data.getData().isEmpty()){
            int count = 0;
            for (HomeBean homeBean : data.getData()){
                if (homeBean.getBEAN_TYPE() == HomeMultipleItem.BROADCAST){
                    BroadcastBean bean = (BroadcastBean) homeBean;
                    mViewFlipper = helper.getView(R.id.broadcast_vf);

                    TextView broadcastTv = new TextView(mContext);
                    ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    broadcastTv.setId(count);
                    broadcastTv.setId(count);
                    params.gravity = Gravity.START|CENTER;
                    broadcastTv.setLayoutParams(params);
                    broadcastTv.setTextColor(Color.BLACK);
                    broadcastTv.setText(bean.getContent());
                    mViewFlipper.addView(broadcastTv);

                    BroadcastBean bb = new BroadcastBean();
                    bb.setContent(bean.getContent());
                    bb.setContentUrl(bean.getContentUrl());
                    mBroadcastBeans.add(bb);

                    count++;
                }
            }
            //. 设置动画切换的时间间隔
            mViewFlipper.setFlipInterval(2000);
            //. 设置进入的动画
            mViewFlipper.setInAnimation(mContext, R.anim.broadcast_roll_in);
            //. 设置退出的动画
            mViewFlipper.setOutAnimation(mContext, R.anim.broadcast_roll_out);
            //. 启动动画，开始循环；停止循环：stopFlipping
            mViewFlipper.startFlipping();
            //. 设置监听
            mViewFlipper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了: " + mBroadcastBeans.get(
                            mViewFlipper.getCurrentView().getId()).getContent(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
