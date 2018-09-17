package cnzjys.zhuimj.zjys.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cnzjys.zhuimj.zjys.R;
import cnzjys.zhuimj.zjys.utils.StateBarUtils;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏颜色
        StateBarUtils.compat(this, getResources().getColor(R.color.colorPrimary));

    }

}
