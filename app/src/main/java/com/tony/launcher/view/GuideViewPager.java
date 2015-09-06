package com.tony.launcher.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2015/9/6.
 */
public class GuideViewPager extends ViewPager {

    private Bitmap bitmap;
    private Paint mPaint = new Paint(1);


    public GuideViewPager(Context context) {
        super(context);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写viewpager   在dispatchDraw方法中控制显示的背景图片区域,
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (bitmap != null) {
            int width = this.bitmap.getWidth();
            int height = this.bitmap.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();
            // 子View中背景图片需要显示的宽度，放大背景图或缩小背景图。
            int n = height * getWidth() / getHeight();
            /**
             * (width - n) / (count - 1)表示除去显示第一个ViewPager页面用去的背景宽度，剩余的ViewPager需要显示的背景图片的宽度。
             * getWidth()等于ViewPager一个页面的宽度，即手机屏幕宽度。在该计算中可以理解为滑动一个ViewPager页面需要滑动的像素值。
             * ((width - n) / (count - 1)) /getWidth()也就表示ViewPager滑动一个像素时，背景图片滑动的宽度。
             * x * ((width - n) / (count - 1)) /  getWidth()也就表示ViewPager滑动x个像素时，背景图片滑动的宽度。
             * 背景图片滑动的宽度的宽度可以理解为背景图片滑动到达的位置。
             */
            int w = x * ((width - n) / (count - 1)) / getWidth();
            canvas.drawBitmap(this.bitmap, new Rect(w, 0, n + w, height),
                    new Rect(x, 0, x + getWidth(), getHeight()), this.mPaint);
        }
        super.dispatchDraw(canvas);
    }

    public void setBackground(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.mPaint.setFilterBitmap(true);
    }
}
