package com.example.dac.app_moki.view.tutorial;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.dac.app_moki.R;
import com.example.dac.app_moki.view.login.Login_Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

//import com.a20171.app.moki6.manage.AppPreference;
//import com.a20171.app.moki6.utils.Utils;

//import static com.a20171.app.moki6.utils.Constants.ANCHOR_MAX_WIDTH_RATIO;
//import static com.a20171.app.moki6.utils.Constants.PARALLAX_RATIO;


public class StartUpTutorialActivity extends AppCompatActivity {
    private static final int NUM_PAGES = 4;
    private MotionImage bgImage;
    int currentZoomingIconIndex;
    private int devHeight;
    private int devWidth;
    private MotionImage deviceImage;
    private ImageViewFrame ensureImg;
    private CircularImageViewFrame fifthZoomImg;
    private MotionImage firstImg;
    private CircularImageViewFrame firstZoomImg;
    private CircularImageViewFrame fouthZoomImg;
    private ArrayList<MotionImage> iconList = new ArrayList();
    private PageIndicator indicator;
    private boolean isStoppedZooming;
    private ScrollView productImgScroll;
    private Pager scroller;
    private MotionImage secondImg;
    private CircularImageViewFrame secondZoomImg;
    private Button skipButton;
    private boolean stopZoom;
    private boolean stopZoomReverse;
    private MotionImage thirdImg;
    private ImageViewFrame thirdPageDeviceImage;
    private CircularImageViewFrame thirdZoomImg;
    private Timer timer;
    private TextView tvFourthPage;
    private MotionTextView tvSecondPage;
    private TextView tvThirdPage;
    private Frame zoomFrame;
    int zoomImgSize;
    CircularImageViewFrame zoomingIcon = null;
    private ArrayList<ImageView> zoomingListImg = new ArrayList();

    class ActionOnClickListener implements View.OnClickListener {
        ActionOnClickListener() {
        }

        public void onClick(View v) {
           StartUpTutorialActivity.this.startActivity(new Intent(StartUpTutorialActivity.this, Login_Activity.class));
            //AppPreference.INSTANCE.setFristload(false);
            finish();
        }
    }

    class ActionOnTouchListener implements OnTouchListener {
        ActionOnTouchListener() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class RunnableCustom implements Runnable {

        class TimerTaskCustom extends TimerTask {

            class RunnablesCustom implements Runnable {
                RunnablesCustom() {
                }

                public void run() {
                    if (StartUpTutorialActivity.this.scroller.getScrollX() < 50) {
                        StartUpTutorialActivity.this.scroller.smoothScrollBy(10, 0);
                    } else if (StartUpTutorialActivity.this.timer != null) {
                        StartUpTutorialActivity.this.timer.cancel();
                        StartUpTutorialActivity.this.timer = null;
                    }
                }
            }

            TimerTaskCustom() {
            }

            public void run() {
                StartUpTutorialActivity.this.runOnUiThread(new RunnablesCustom());
            }
        }

        RunnableCustom() {
        }

        public void run() {
            StartUpTutorialActivity.this.timer.schedule(new TimerTaskCustom(), 0, 10);
        }
    }

    class AnimatorListenerCustom implements AnimatorListener {

        class AnimatorListenerRunnable implements Runnable {
            AnimatorListenerRunnable() {
            }

            public void run() {
                StartUpTutorialActivity.this.zoomIcon();
            }
        }

        AnimatorListenerCustom() {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            if (!(StartUpTutorialActivity.this.currentZoomingIconIndex == 0 && StartUpTutorialActivity.this.stopZoomReverse) && (StartUpTutorialActivity.this.currentZoomingIconIndex != 4 || StartUpTutorialActivity.this.stopZoomReverse)) {
                StartUpTutorialActivity.this.zoomIcon();
            } else {
                new Handler().postDelayed(new AnimatorListenerRunnable(), 1000);
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_tutorial);
        this.skipButton = (Button) findViewById(R.id.skip_button);
        this.skipButton.setOnClickListener(new ActionOnClickListener());
        this.skipButton.setVisibility(View.INVISIBLE);
        DisplayMetrics metrics = getApplication().getResources().getDisplayMetrics();
        this.devWidth = metrics.widthPixels;
        this.devHeight = metrics.heightPixels;
        int device_image_top_margin = this.devHeight / 20;
        this.scroller = (Pager) findViewById(R.id.scrollView);
        this.indicator = (PageIndicator) findViewById(R.id.indicator);
        this.indicator.setPager(this.scroller);
        final FrameLayout zoomAndMotionLayout = (FrameLayout) findViewById(R.id.layout);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        this.deviceImage = (MotionImage) findViewById(R.id.deviceImage);
        int device_image_width = this.devWidth - ((int) (((double) this.devWidth) / 1.7d));
        int device_image_height = (int) (((double) device_image_width) * 2.1d);
        LayoutParams indicatorParams = this.indicator.getLayoutParams();
        indicatorParams.height = this.devHeight / 25;
        this.indicator.setLayoutParams(indicatorParams);
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                this.zoomFrame = Frame.FrameMake((this.devWidth - device_image_width) / 2, device_image_top_margin, device_image_width, device_image_height);
                this.deviceImage.setupZoomArea(Frame.FrameMake((-this.devWidth) / 11, -((int) (((double) this.devHeight) / 3.8d)), (int) (((double) this.devWidth) + (((double) this.devWidth) / 5.9d)), (int) (((double) this.devHeight) + (((double) this.devHeight) / 2.25d))), this.zoomFrame);

                this.firstImg = (MotionImage) findViewById(R.id.firstImg);
                this.firstImg.setupPostion(Frame.FrameMake((int) (((double) (this.zoomFrame.x + this.zoomFrame.width)) - (((double) this.zoomFrame.x) /  1.9)), this.zoomFrame.y - 10, 0, 0), 3.2f);
                this.firstImg.setLayoutParams(new FrameLayout.LayoutParams(this.zoomFrame.width / 4, this.zoomFrame.width / 4));

                this.secondImg = (MotionImage) findViewById(R.id.secondImg);
                this.secondImg.setupPostion(Frame.FrameMake(this.zoomFrame.x - (this.zoomFrame.x / 5), (int) (((double) this.zoomFrame.y) + (((double) this.zoomFrame.height) /  1.8)), 0, 0), 3.2f);
                this.secondImg.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) this.zoomFrame.width) / 3.5d), (int) (((double) this.zoomFrame.width) / 3.5d)));

                this.thirdImg = (MotionImage) findViewById(R.id.thirdImg);
                this.thirdImg.setupPostion(Frame.FrameMake((this.zoomFrame.x + this.zoomFrame.width) - (this.zoomFrame.x / 8), (int) (((double) this.zoomFrame.y) + (((double) this.zoomFrame.height) /  1.8)), 0, 0), 3.2f);
                this.thirdImg.setLayoutParams(new FrameLayout.LayoutParams((int) (((double) this.zoomFrame.width) / 4.5d), (int) (((double) this.zoomFrame.width) / 4.5d)));

                this.iconList.add(this.firstImg);
                this.iconList.add(this.secondImg);
                this.iconList.add(this.thirdImg);
                this.deviceImage.moving((float) this.devWidth);
                this.scroller.addPage(new View(this));
            } else if (i == 1) {
                this.scroller.addPage(new View(this));
                this.tvSecondPage = (MotionTextView) findViewById(R.id.second_page_text);
                this.tvSecondPage.setupPostion(Frame.FrameMake(0, 0, 0, 0), 3.2f);
                this.tvSecondPage.setVisibility(View.INVISIBLE);
                this.tvSecondPage.setText("Phù hợp với thói quen mua bán của mẹ trong thời gian mang thai, nuôi con nhỏ");
            } else if (i == 2) {
                View thirdPage = layoutInflater.inflate(R.layout.start_up_tutorial_page2, null);
                this.tvThirdPage = (TextView) thirdPage.findViewById(R.id.third_page_text);
                //this.tvThirdPage.setText(getResources().getString(R.string.startup_tutorial_page2));
                FrameLayout layout = (FrameLayout) thirdPage.findViewById(R.id.thirdPageLayout);
                this.thirdPageDeviceImage = (ImageViewFrame) thirdPage.findViewById(R.id.thirdPageDeviceImage);
                this.thirdPageDeviceImage.setFrame(this.zoomFrame);
                this.productImgScroll = (ScrollView) thirdPage.findViewById(R.id.productImageScroll);
                this.productImgScroll.setX((float) (((double) this.zoomFrame.x) + (((double) this.zoomFrame.width) / 12.9d)));
                this.productImgScroll.setY((float) (((double) this.zoomFrame.y) + (((double) this.zoomFrame.height) / 5.5d)));
                this.productImgScroll.setOnTouchListener(new ActionOnTouchListener());
                LayoutParams productImgScrollParams = this.productImgScroll.getLayoutParams();
                productImgScrollParams.width = (int) (((double) this.zoomFrame.width) - (((double) this.zoomFrame.width) / 7.1d));
                productImgScrollParams.height = (int) (((double) this.zoomFrame.height) - (((double) this.zoomFrame.height) / 3.14d));
                this.productImgScroll.setLayoutParams(productImgScrollParams);
                this.zoomImgSize = device_image_width / 4;
                this.firstZoomImg = (CircularImageViewFrame) thirdPage.findViewById(R.id.firstZoomIcon);
                this.firstZoomImg.setFrame(Frame.FrameMake((this.zoomFrame.x + this.zoomFrame.width) - (this.zoomFrame.x / 5), (int) (((double) this.zoomFrame.y) + (((double) this.zoomFrame.height) / 1.8d)), this.zoomImgSize, this.zoomImgSize));

                this.secondZoomImg = (CircularImageViewFrame) thirdPage.findViewById(R.id.secondZoomIcon);
                this.secondZoomImg.setFrame(Frame.FrameMake(this.zoomFrame.x - (this.zoomFrame.x / 5), this.zoomFrame.y + (this.zoomFrame.x / 2), this.zoomImgSize, this.zoomImgSize));
                layout.bringChildToFront(this.secondZoomImg);

                this.thirdZoomImg = (CircularImageViewFrame) thirdPage.findViewById(R.id.thirdZoomIcon);
                this.thirdZoomImg.setFrame(Frame.FrameMake((this.zoomFrame.x + this.zoomFrame.width) - (this.zoomFrame.x / 5), this.zoomFrame.y + (this.zoomFrame.x / 3), this.zoomImgSize, this.zoomImgSize));
                layout.bringChildToFront(this.thirdZoomImg);

                this.fouthZoomImg = (CircularImageViewFrame) thirdPage.findViewById(R.id.fouthZoomIcon);
                this.fouthZoomImg.setFrame(Frame.FrameMake((int) (((double) this.zoomFrame.x) + (((double) this.zoomFrame.width) / 2.2d)), (int) (((double) this.zoomFrame.y) - (((double) this.zoomImgSize) / 2.2d)), this.zoomImgSize, this.zoomImgSize));

                this.fifthZoomImg = (CircularImageViewFrame) thirdPage.findViewById(R.id.fifthZoomIcon);
                this.fifthZoomImg.setFrame(Frame.FrameMake((int) (((double) this.zoomFrame.x) - (((double) this.zoomFrame.x) / 3.5d)), (int) (((double) this.zoomFrame.y) + (((double) this.zoomFrame.height) /  1.9)), this.zoomImgSize, this.zoomImgSize));
                layout.bringChildToFront(this.fifthZoomImg);

                this.zoomingListImg.add(this.firstZoomImg);
                this.zoomingListImg.add(this.secondZoomImg);
                this.zoomingListImg.add(this.thirdZoomImg);
                this.zoomingListImg.add(this.fouthZoomImg);
                this.zoomingListImg.add(this.fifthZoomImg);
                this.isStoppedZooming = true;
                autoScrollProductList();
                this.scroller.addPage(thirdPage);
            } else if (i == 3) {
                View fouthPage = layoutInflater.inflate(R.layout.start_up_tutorial_page3, null);
                this.tvFourthPage = (TextView) fouthPage.findViewById(R.id.fouth_page_text);
                //this.tvFourthPage.setText("Giảm thiểu rủi ro lừa đảo.");
                int ensureImgWidth = (int) (((double) this.devWidth) / 3.5d);
                this.ensureImg = (ImageViewFrame) fouthPage.findViewById(R.id.ensureImg);
                this.ensureImg.setFrame(Frame.FrameMake((int) (((double) this.devWidth) - (((double) ensureImgWidth) * 1.6)), ensureImgWidth / 2, ensureImgWidth, ensureImgWidth));
                this.scroller.addPage(fouthPage);
            }
        }
        this.scroller.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageChange(Pager scroller) {
            }

            public void onPageCountChange(Pager scroller) {
            }

            public void pageScroll(int l, int t, int oldl, int oldt) {
                Log.d("pageScroll: ", l + "-" + t + "-" + oldl + "-" + oldt);
                int page = (int) Math.floor((double) (l / StartUpTutorialActivity.this.devWidth));
                StartUpTutorialActivity.this.skipButton.setText("Bỏ qua");
                Iterator it = StartUpTutorialActivity.this.iconList.iterator();
                while (it.hasNext()) {
                    ((MotionImage) it.next()).moving((float) (StartUpTutorialActivity.this.devWidth - l));
                }
                StartUpTutorialActivity.this.tvSecondPage.moving((float) (StartUpTutorialActivity.this.devWidth - l));
                if (page == 0) {
                    StartUpTutorialActivity.this.skipButton.setVisibility(View.INVISIBLE);
                    StartUpTutorialActivity.this.deviceImage.motionRate = 0.0f;
                } else if (page > 0) {
                    zoomAndMotionLayout.bringChildToFront(StartUpTutorialActivity.this.firstImg);
                    zoomAndMotionLayout.bringChildToFront(StartUpTutorialActivity.this.secondImg);
                    zoomAndMotionLayout.requestLayout();
                    StartUpTutorialActivity.this.skipButton.setVisibility(View.VISIBLE);
                    StartUpTutorialActivity.this.deviceImage.motionRate = 1.0f;
                }
                StartUpTutorialActivity.this.deviceImage.moving((float) (StartUpTutorialActivity.this.devWidth - l));
                if (page == 1) {
                    StartUpTutorialActivity.this.tvSecondPage.setVisibility(View.VISIBLE);
                }
                StartUpTutorialActivity.this.stopZoom = true;
                if (page == 2) {
                    StartUpTutorialActivity.this.stopZoom = false;
                    StartUpTutorialActivity.this.tvSecondPage.setVisibility(View.INVISIBLE);
                    if (StartUpTutorialActivity.this.isStoppedZooming) {
                        StartUpTutorialActivity.this.zoomIcon();
                        StartUpTutorialActivity.this.autoScrollProductList();
                    }
                }
                if (page == 3) {
                    StartUpTutorialActivity.this.skipButton.setText("Bắt đầu");
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.timer = new Timer();
        new Handler().postDelayed(new RunnableCustom(), 3000);
    }

    private void autoScrollProductList() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this.productImgScroll, "scrollY", new int[]{0, this.productImgScroll.getChildAt(0).getHeight() - this.productImgScroll.getHeight()}).setDuration(2000);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }

    private void zoomIcon() {
        if (this.stopZoom) {
            this.isStoppedZooming = true;
            return;
        }
        this.isStoppedZooming = false;
        if (!this.stopZoomReverse) {
            switch (this.currentZoomingIconIndex) {
                case 0:
                    this.zoomingIcon = this.firstZoomImg;
                    this.stopZoomReverse = true;
                    break;
                case 1:
                    this.zoomingIcon = this.secondZoomImg;
                    this.currentZoomingIconIndex--;
                    break;
                case 2:
                    this.zoomingIcon = this.thirdZoomImg;
                    this.currentZoomingIconIndex--;
                    break;
                case 3:
                    this.zoomingIcon = this.fouthZoomImg;
                    this.currentZoomingIconIndex--;
                    break;
                case 4:
                    this.zoomingIcon = this.fifthZoomImg;
                    this.currentZoomingIconIndex--;
                    break;
                default:
                    break;
            }
        }
        switch (this.currentZoomingIconIndex) {
            case 0:
                this.zoomingIcon = this.firstZoomImg;
                this.currentZoomingIconIndex++;
                break;
            case 1:
                this.zoomingIcon = this.secondZoomImg;
                this.currentZoomingIconIndex++;
                break;
            case 2:
                this.zoomingIcon = this.thirdZoomImg;
                this.currentZoomingIconIndex++;
                break;
            case 3:
                this.zoomingIcon = this.fouthZoomImg;
                this.currentZoomingIconIndex++;
                break;
            case 4:
                this.zoomingIcon = this.fifthZoomImg;
                this.stopZoomReverse = false;
                break;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this.zoomingIcon, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0f, 1.5f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{1.0f, 1.5f})});
        objectAnimator.setDuration(250);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.addListener(new AnimatorListenerCustom());
        objectAnimator.start();
    }
}

