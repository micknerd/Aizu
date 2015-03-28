package com.gclue.viewsample_1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;

/**
 * 描画用のクラス.
 */
class MyView extends View {

    /**
     * コンストラクタ.
     *
     * @param context コンテキスト
     */

    private Bitmap myBitmap;

    private final static String TAG = "DRAW";

    /**
     * X.
     */
    private int x;

    /**
     * Y.
     */
    private int y;


    public MyView(Context context) {
        super(context);
        setFocusable(true);

        // Resourceインスタンスの生成
        Resources res = this.getContext().getResources();
        // 画像の読み込み(res/drawable/gclue_logo.gif)
        myBitmap = BitmapFactory.decodeResource(res, R.drawable.opusone);
    }

    /**
     * 描画処理を行う.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 背景色を設定
        canvas.drawColor(Color.BLUE);

        // 描画するための線の色を設定.
        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(255, 255, 0, 0);

        canvas.drawBitmap(myBitmap, x, y, mPaint);

        // ② 四隅をとる.
        int viewWidth = this.getWidth();
        int viewHeight = this.getHeight();
        int imgWidth = myBitmap.getWidth();
        int imgHeight = myBitmap.getHeight();

        Log.i(TAG, "viewWidth:" + viewWidth);
        Log.i(TAG, "viewHeight:" + viewHeight);
        Log.i(TAG, "X:" + x);
        Log.i(TAG, "Y:" + y);


    }


 /*
@Override
public boolean onTouchEvent( MotionEvent event ) {
        // タッチした時に実行
        if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
            // X,Y座標の取得
            x = (int) event.getX();
            y = (int) event.getY();
            // 再描画の指示
            invalidate();
        }
        return true;
    }
*/


    /**
     * タッチイベント.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // タッチした時に実行
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // X,Y座標の取得
            x = (int) event.getX();
            y = (int) event.getY();
            // 再描画の指示
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // X,Y座標の取得
            x = (int) event.getX();
            y = (int) event.getY();
            // 再描画の指示
            invalidate();
        }
        return true;
    }
}
