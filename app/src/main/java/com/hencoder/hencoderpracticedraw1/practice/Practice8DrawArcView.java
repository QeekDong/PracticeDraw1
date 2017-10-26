package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    Paint paint;
    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setAntiAlias(true);
        RectF rectF = new RectF(180, 135, 540, 405);
        canvas.drawArc(180, 135, 540, 405, -20, -90, true, paint);
        canvas.drawArc(180, 135, 540, 405, 45, 90, false, paint);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
    }
}
