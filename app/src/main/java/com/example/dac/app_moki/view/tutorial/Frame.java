package com.example.dac.app_moki.view.tutorial;

/**
 * Created by PhungVanQuang on 9/17/2017.
 */


public class Frame
{
    public int height;
    public int width;
    public int x;
    public int y;

    public Frame() {}

    public Frame(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        this.x = paramInt1;
        this.y = paramInt2;
        this.width = paramInt3;
        this.height = paramInt4;
    }

    public static Frame FrameMake(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        return new Frame(paramInt1, paramInt2, paramInt3, paramInt4);
    }
}
