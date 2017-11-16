package com.example.dac.app_moki.view.tutorial;

/**
 * Created by PhungVanQuang on 9/17/2017.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.dac.app_moki.R;

//import com.a20171.app.moki6.R;
//
//import static com.a20171.app.moki6.utils.Constants.GIGABYTES;

public class PageIndicator extends View {
    private int cellSize;
    private int devH;
    private Paint dotPaint;
    private Paint highLightDotPaint;
    private Pager pager;
    private OnPageChangeListener scrollListener = new C28501();

    class C28501 implements OnPageChangeListener {
        C28501() {
        }

        public void onPageChange(Pager scroller) {
            PageIndicator.this.update();
        }

        public void onPageCountChange(Pager scroller) {
            PageIndicator.this.updatePageCount();
        }

        public void pageScroll(int l, int t, int oldl, int oldt) {
        }
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public PageIndicator(Context context) {
        super(context);
        initPaints();
    }

    private final void initPaints() {
        this.dotPaint = new Paint();
        this.dotPaint.setColor(-7829368);
        this.dotPaint.setStyle(Style.FILL_AND_STROKE);
        this.dotPaint.setStrokeWidth(3.0f);
        this.highLightDotPaint = new Paint();
        this.highLightDotPaint.setColor(getContext().getResources().getColor(R.color.red_button));
        this.highLightDotPaint.setStyle(Style.FILL_AND_STROKE);
        this.highLightDotPaint.setStrokeWidth(3.0f);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        this.devH = metrics.heightPixels;
        this.cellSize = metrics.widthPixels / 15;
    }

    public void setPager(Pager pager) {
        if (pager != null) {
            pager.removeOnPageChangeListener(this.scrollListener);
        }
        this.pager = pager;
        if (pager != null) {
            pager.addOnPageChangeListener(this.scrollListener);
        }
    }

    public void update() {
        invalidate();
    }

    public void updatePageCount() {
        requestLayout();
        invalidate();
    }

    private int getNumPages() {
        return this.pager == null ? 1 : this.pager.getPageCount();
    }

    private int getActivePage() {
        return this.pager == null ? 0 : this.pager.getCurrentPage();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
//        if (specMode == GIGABYTES) {
//            return specSize;
//        }
        int result = getNumPages() * this.cellSize;
        if (specMode == Integer.MIN_VALUE) {
            return Math.min(result, specSize);
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
//        if (specMode == GIGABYTES) {
//            return specSize;
//        }
        int result = this.cellSize;
        if (specMode == Integer.MIN_VALUE) {
            return Math.min(result, specSize);
        }
        return result;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = getNumPages();
        int current = getActivePage();
        int x = this.devH / 80;
        int i = 0;
        while (i < count) {
            Paint paint;
            if (i == current) {
                paint = this.highLightDotPaint;
            } else {
                paint = this.dotPaint;
            }
            canvas.drawCircle((float) ((this.devH / 80) + x), (float) (this.devH / 80), (float) (this.devH / 150), paint);
            i++;
            x += this.cellSize;
        }
    }
}
