package com.example.dac.app_moki.view.tutorial;

/**
 * Created by PhungVanQuang on 9/17/2017.
 */

public abstract interface OnPageChangeListener
{
    public abstract void onPageChange(Pager paramPager);

    public abstract void onPageCountChange(Pager paramPager);

    public abstract void pageScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}
