package cn.shopin.oneposition.customview;

/**
 * Created by zcs on 2017/3/21.
 */

public class PieFT
{
    private float b = 0.0F;
    private long h = 0L;
    private boolean isTrue = false;
    private float t = 0.0F;
    private int w = 0;

    public PieFT(int paramInt, long paramLong)
    {
        this.w = paramInt;
        this.h = paramLong;
    }

    public float getB()
    {
        return this.b;
    }

    public long getH()
    {
        return this.h;
    }

    public boolean getIsTrue()
    {
        return this.isTrue;
    }

    public float getT()
    {
        return this.t;
    }

    public int getW()
    {
        return this.w;
    }

    public void setB(float paramFloat)
    {
        this.b = paramFloat;
    }

    public void setH(int paramInt)
    {
        this.h = paramInt;
    }

    public void setT(float paramFloat)
    {
        this.t = paramFloat;
    }

    public void setTrue(boolean paramBoolean)
    {
        this.isTrue = paramBoolean;
    }

    public void setW(int paramInt)
    {
        this.w = paramInt;
    }
}