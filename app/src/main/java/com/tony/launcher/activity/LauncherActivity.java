package com.tony.launcher.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tony.launcher.R;
import com.tony.launcher.adapter.BaseFragmentAdapter;
import com.tony.launcher.fragment.LauncherBaseFragment;
import com.tony.launcher.fragment.PrivateMessageLauncherFragment;
import com.tony.launcher.fragment.RewardLauncherFragment;
import com.tony.launcher.fragment.StereoscopicLauncherFragment;
import com.tony.launcher.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;

public class LauncherActivity extends FragmentActivity {

    private GuideViewPager vPager;
    private List<LauncherBaseFragment> list = new ArrayList<LauncherBaseFragment>();
    private BaseFragmentAdapter adapter;

    private ImageView[] points;
    private int currentSelect;
    private ViewPager.OnPageChangeListener changeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launcher);
        //初始化下方点点控件
        initPoints();
        //获取自定义viewpager ，然后设置背景图片
        vPager = (GuideViewPager) findViewById(R.id.viewpager_launcher);
        vPager.setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.bg_kaka_launcher));
        /**
         * 初始化三个fragment  并且添加到list中
         */
        RewardLauncherFragment rewardFragment = new RewardLauncherFragment();
        PrivateMessageLauncherFragment privateMFragment = new PrivateMessageLauncherFragment();
        StereoscopicLauncherFragment stereoscopicFragment = new StereoscopicLauncherFragment();
        list.add(rewardFragment);
        list.add(privateMFragment);
        list.add(stereoscopicFragment);

        adapter = new BaseFragmentAdapter(getSupportFragmentManager(),list);
        vPager.setAdapter(adapter);
        vPager.setOffscreenPageLimit(2);
        vPager.setCurrentItem(0);
        vPager.setOnPageChangeListener(changeListener);
    }

    private void initPoints() {
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        points = new ImageView[3];
        for (int i = 0;i < points.length;i ++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));
            if (i == 0){
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            }else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            points[i] = imageView;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 20;//设置点点点view的左边距
            params.rightMargin = 20;//设置点点点view的右边距
            group.addView(imageView,params);
        }

        /**
         * 监听viewpager的移动
         */
        changeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position);//改变点点点的切换效果
                LauncherBaseFragment fragment = list.get(position);
                list.get(currentSelect).stopAnimation();//停止前一个页面的动画
                fragment.startAnimation();//开启当前页面的动画
                currentSelect = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    private void setImageBackground(int selectItems) {
        for (int i = 0;i < points.length;i ++){
            if (i == selectItems){
                points[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                points[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }
}
