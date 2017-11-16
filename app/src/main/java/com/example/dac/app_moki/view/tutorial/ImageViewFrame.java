package com.example.dac.app_moki.view.tutorial;

/**
 * Created by PhungVanQuang on 9/17/2017.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageViewFrame extends ImageView {
    Frame frame;

    public ImageViewFrame(Context paramContext) {
        super(paramContext);
    }

    public ImageViewFrame(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public ImageViewFrame(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public Frame getFrame() {
        return this.frame;
    }

    public float getX() {
        if (this.frame != null) {
            return this.frame.x;
        }
        return super.getX();
    }

    public float getY() {
        if (this.frame != null) {
            return this.frame.y;
        }
        return super.getY();
    }

    public void setFrame(Frame paramFrame) {
        ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
        if (localLayoutParams != null) {
            localLayoutParams.width = paramFrame.width;
            localLayoutParams.height = paramFrame.height;
            setX(paramFrame.x);
            setY(paramFrame.y);
            setLayoutParams(localLayoutParams);
        }
    }
}
