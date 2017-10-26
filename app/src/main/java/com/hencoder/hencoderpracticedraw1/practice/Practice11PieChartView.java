package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.Data;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {
    private static final String TAG = "饼图";
    private float max;
    private float total;
    List<Data> mList;
    Paint paint = new Paint();

    float startAngle;
    float sweepAngle;
    float halfAngle;

    float lineStartX = 0f;
    float lineStartY = 0f;
    float lineEndX;
    float lineEndY;

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources();
    }

    private void initResources(){
        mList = new ArrayList<>();
        mList.add(new Data("Gingerbread", 15.0f, Color.WHITE));
        mList.add(new Data("Ice Cream Sandwich", 20.0f, Color.MAGENTA));
        mList.add(new Data("Jelly Bean", 22.0f, Color.GRAY));
        mList.add(new Data("KitKat", 28.0f, Color.GREEN));
        mList.add(new Data("Lollipop", 30.0f, Color.BLUE));
        mList.add(new Data("Marshmallow", 70.0f, Color.RED));
        mList.add(new Data("Nougat", 50.5f, Color.YELLOW));

        max = Float.MIN_VALUE;
        total = 0;
        for (Data item : mList){
            total += item.getNumber();
            Log.d(TAG, "initResources: total " + total);
            max = Math.max(max, item.getNumber());
        }
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);
        canvas.drawText(TAG, (canvas.getWidth()-paint.measureText(TAG))/2, canvas.getHeight()*0.9f, paint);

        canvas.translate(canvas.getWidth()*0.5f, canvas.getHeight()*0.5f);
        float radius = canvas.getHeight()*0.3f;
        RectF rectF = new RectF(-radius, -radius, radius, radius);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(15);

        startAngle = 0f;
        for (Data data : mList){
            sweepAngle = data.getNumber()/total*360f;
            Log.d(TAG, "onDraw: sweepAngle" + sweepAngle);
            halfAngle = sweepAngle*0.5f+startAngle;
            paint.setColor(data.getColor());

            lineStartX = radius*(float) Math.cos(halfAngle/180*Math.PI);
            lineStartY = radius*(float) Math.sin(halfAngle/180*Math.PI);
            lineEndX = (radius+50)*(float) Math.cos(halfAngle/180*Math.PI);
            lineEndY = (radius+50)*(float) Math.sin(halfAngle/180*Math.PI);

            if (max == data.getNumber()){
                canvas.save();
                canvas.translate(lineStartX*0.1f, lineStartY*0.1f);
                canvas.drawArc(rectF, startAngle, sweepAngle-2, true, paint);
            }else{
                canvas.drawArc(rectF, startAngle, sweepAngle-2,true, paint);
            }

            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);
            if (halfAngle>90 & halfAngle<270){
                canvas.drawLine(lineEndX, lineEndY, lineEndX-50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX-50 - paint.measureText(data.getName())-10, lineEndY,  paint);
            }else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX+50, lineEndY, paint);
                 paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX+60 , lineEndY, paint);
            }
            if (max == data.getNumber()){
                canvas.restore();
            }
            startAngle += sweepAngle;

        }


//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
    }
}
