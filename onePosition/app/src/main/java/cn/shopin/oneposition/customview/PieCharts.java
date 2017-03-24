package cn.shopin.oneposition.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcs on 2017/3/21.
 */

public class PieCharts extends View {
    private float b = 0.0F;
    private float c = 0.0F;
    private Canvas canvas = null;
    private int[] colors = {-2271232, -15574883, -543744, -15772372, -2677685, -9520739, -5301891, -10539386, -2239358, -6475733, -16437166, -6692353, -10994366, -12290777, -3431268, -2285824, -14310886, -2907194, -532736, -9603398, -10537176, -8207659, -1260373, -6338282, -7692522, -15985215, -15941206, -4097268, -9792750, -4125684, -15941283, -2585952, -2979112, -7677992, -5121910, -2577014, -6029020, -16671068, -12147711, -5999615};
    private float end_o_starty = 0.0F;
    private float h = 0.0F;
    private boolean isCan = false;
    private boolean isTouch = false;
    private int j = 0;
    private List<PieFT> list = null;
    private float o = 0.0F;
    private Paint paint = null;
    private float startY = 0.0F;
    private float t = -90.0F;
    private float w = 0.0F;
    private float x = 0.0F;
    private float y = 0.0F;

    public PieCharts(Context paramContext) {
        super(paramContext);
    }

    public PieCharts(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        this.list = new ArrayList();
        this.paint = new Paint();
        this.paint.setStrokeWidth(4.0F);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
    }

    private void draw() {
        if ((this.list != null) && (this.list.size() != 0)) {
            for (int i = 0; i < this.list.size(); i++)
                drawIn(((PieFT) this.list.get(i)).getW(), (float) ((PieFT) this.list.get(i)).getH(), i, ((PieFT) this.list.get(i)).getIsTrue());
            if (this.startY < this.o) {
                this.t = -90.0F;
                this.startY += this.end_o_starty;
                invalidate();
            }
        }
    }

    private void drawIn(float paramFloat1, float paramFloat2, int paramInt, boolean paramBoolean) {
        this.paint.setColor(this.colors[(paramInt % 40)]);
        this.b = (360.0F * paramFloat1 / paramFloat2);
        this.c = (90.0F + (this.b / 2.0F + this.t));
        ((PieFT) this.list.get(paramInt)).setB(this.b);
        ((PieFT) this.list.get(paramInt)).setT(this.t);
        if (paramBoolean)
            this.canvas.drawArc(new RectF(this.w / 2.0F - 2.0F * this.h / 5.0F + (float) (this.startY * Math.sin(3.141592653589793D * this.c / 180.0D)), 1.0F * this.h / 10.0F - (float) (this.startY * Math.cos(3.141592653589793D * this.c / 180.0D)), this.w / 2.0F + 2.0F * this.h / 5.0F + (float) (this.startY * Math.sin(3.141592653589793D * this.c / 180.0D)), 9.0F * this.h / 10.0F - (float) (this.startY * Math.cos(3.141592653589793D * this.c / 180.0D))), this.t, this.b, true, this.paint);
        while (true) {
            this.t += this.b;
            return;
//            this.canvas.drawArc(new RectF(this.w / 2.0F - 2.0F * this.h / 5.0F, 1.0F * this.h / 10.0F, this.w / 2.0F + 2.0F * this.h / 5.0F, 9.0F * this.h / 10.0F), this.t, this.b, true, this.paint);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent) {
        switch (paramMotionEvent.getAction()) {
            default:
            case 0:
        }
        do
            return super.dispatchTouchEvent(paramMotionEvent);
        while (this.isTouch);
/*        this.isTouch = true;
        this.isCan = false;
        this.startY = 0.0F;
        this.t = -90.0F;*/
//        if ((this.list != null) && (this.list.size() != 0)) {
//            for (int i = 0; i < this.list.size(); i++)
//                ((PieFT) this.list.get(i)).setTrue(false);
//            this.x = paramMotionEvent.getX();
//            this.y = paramMotionEvent.getY();
//            if (Math.sqrt((this.x - this.w / 2.0F) * (this.x - this.w / 2.0F) + (this.y - this.h / 2.0F) * (this.y - this.h / 2.0F)) <= 2.0F * this.h / 5.0F) {
//                if ((this.x != this.w / 2.0F) || (this.y >= this.h / 2.0F)) {
//                } else {
//                    this.j = -90;
//                    this.isCan = true;
//                }
//            }
//            label235:
//            if (!this.isCan) ;
//        }
//        label235:
//        for (int k = 0; ; k++) {
//            if (k < this.list.size()) {
//                if ((this.j - ((PieFT) this.list.get(k)).getT() < ((PieFT) this.list.get(k)).getB()) && (this.j - ((PieFT) this.list.get(k)).getT() >= 0.0F))
//                    ((PieFT) this.list.get(k)).setTrue(true);
//            } else {
//                invalidate();
//                this.isTouch = false;
//                break;
//                label355:
//                if ((this.x == this.w / 2.0F) && (this.y > this.h / 2.0F)) {
//                    this.j = 90;
//                    this.isCan = true;
//                    break label235;
//                }
//                if ((this.y == this.h / 2.0F) && (this.x > this.w / 2.0F)) {
//                    this.j = 0;
//                    this.isCan = true;
//                    break label235;
//                }
//                if ((this.y == this.h / 2.0F) && (this.x < this.w / 2.0F)) {
//                    this.j = 180;
//                    this.isCan = true;
//                    break label235;
//                }
//                if ((this.x > this.w / 2.0F) && (this.y < this.h / 2.0F)) {
//                    this.j = (-(int) Math.abs(180.0D * Math.atan((this.y - this.h / 2.0F) / (this.x - this.w / 2.0F)) / 3.141592653589793D));
//                    this.isCan = true;
//                    break label235;
//                }
//                if ((this.x > this.w / 2.0F) && (this.y > this.h / 2.0F)) {
//                    this.j = ((int) Math.abs(180.0D * Math.atan((this.y - this.h / 2.0F) / (this.x - this.w / 2.0F)) / 3.141592653589793D));
//                    this.isCan = true;
//                    break label235;
//                }
//                if ((this.x < this.w / 2.0F) && (this.y < this.h / 2.0F)) {
//                    this.j = (180 + (int) Math.abs(180.0D * Math.atan((this.y - this.h / 2.0F) / (this.x - this.w / 2.0F)) / 3.141592653589793D));
//                    this.isCan = true;
//                    break label235;
//                }
//                if ((this.x < this.w / 2.0F) && (this.y > this.h / 2.0F)) {
//                    this.j = (180 - (int) Math.abs(180.0D * Math.atan((this.y - this.h / 2.0F) / (this.x - this.w / 2.0F)) / 3.141592653589793D));
//                    this.isCan = true;
//                    break label235;
//                }
//                this.isCan = false;
//                break label235;
//            }
//        }
    }

    protected void onDraw(Canvas paramCanvas) {
        this.canvas = paramCanvas;
        draw();
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, paramInt2);
        this.w = (paramInt1 - View.MeasureSpec.getMode(paramInt1));
        this.h = (paramInt2 - View.MeasureSpec.getMode(paramInt2));
        this.o = (1.0F * this.h / 30.0F);
        if (this.o < 1.0F)
            this.o = 1.0F;
        this.end_o_starty = (this.o / 6.0F);
        if (this.end_o_starty < 1.0F)
            this.end_o_starty = 1.0F;
    }

    public void setPieFT(List<PieFT> paramList) {
/*        if (this.list != null)
            this.list.clear();*/
        this.startY = 0.0F;
        this.t = -90.0F;
        paramList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PieFT pieft = new PieFT(1, 1000);
            paramList.add(pieft);
        }
        for (int i = 0; i < paramList.size(); i++)
            this.list.add(paramList.get(i));
        invalidate();
    }
}
