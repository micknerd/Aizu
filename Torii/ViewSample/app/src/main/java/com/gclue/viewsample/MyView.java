package com.gclue.viewsample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;

/**
 * 描画用のクラス.
 */
class MyView extends View {

    private Bitmap myBitmap;

    private final static String TAG = "DRAW";

    private int mTaikox = 100;
    private int mTaikoy = 50;

    private MediaPlayer mMediaPlayer;

    /**
     * コンストラクタ.
     *
     * @param context コンテキスト
     */
    public MyView(Context context) {
        super(context);
        setFocusable(true);

        Resources res = this.getContext().getResources();
        myBitmap = BitmapFactory.decodeResource(res, R.drawable.pre0802);
        mMediaPlayer = MediaPlayer.create(context, R.raw.pon);

    }

    /**
     * 描画処理を行う.
     */
    @Override
    protected void onDraw(Canvas canvas ) {
        super.onDraw(canvas);

        // 背景色を設定
        canvas.drawColor(Color.BLACK);

        // 描画するための線の色を設定.
        Paint mPaint = new Paint();
        //mPaint.setStyle( Paint.Style.FILL );
        //mPaint.setARGB(255, 255, 0, 0);
        /*
        mPaint.setARGB( 255, 255, 255, 100 );
        mPaint.setTextSize(100);

        // 文字を描画.
        canvas.drawText( "HELLO ANDROID", 20, 500, mPaint );
        canvas.drawText("HELLO AIZU", 50, 1000, mPaint);

        // 四角を描画
        canvas.drawRect(50, 100, 200, 250, mPaint);

        // 円を描画
        canvas.drawCircle(500, 500, 200, mPaint);

        // 点を描画
        canvas.drawPoint(1300, 1300, mPaint);
*/
        // Bitmapイメージの描画
        //canvas.drawBitmap(myBitmap, 0, 0, mPaint);
        canvas.drawBitmap(myBitmap, mTaikox, mTaikoy, mPaint);

        // 四隅をとる
        int viewWidth = this.getWidth();
        int viewHeight = this.getHeight();
        int imgWidth = myBitmap.getWidth();
        int imgHeight = myBitmap.getHeight();
/*
        Log.i(TAG, "viewWidth:" + viewWidth);
        Log.i(TAG, "viewHeight:" + viewHeight);
        Log.i(TAG, "imgWidth:" + imgWidth);
        Log.i(TAG, "imgHeight:" + imgHeight);
*/
/*
        // 線で描画.
        canvas.drawLine( 0, 0, 50, 50, mPaint );

        // 描画するための線の色を設定.
        mPaint = new Paint();
        mPaint.setStyle( Paint.Style.FILL );
        mPaint.setStrokeWidth(10);
        mPaint.setARGB( 255, 255, 0, 0 );

        // 線で描画.
        canvas.drawLine( 0, 0, 500, 500, mPaint );
*/

    }

    /**
     * タッチイベント.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event ) {
        // タッチした時に実行
        if ( event.getAction() == MotionEvent.ACTION_DOWN ) {
            // X,Y座標の取得
            //mTaikox = (int) event.getX();
            //mTaikoy = (int) event.getY();
            int mTouchX = (int) event.getX();
            int mTouchY = (int) event.getY();

            // 太鼓の中心座標を計算
            int mCenterX = mTaikox + myBitmap.getWidth()/2;
            int mCenterY = mTaikoy + myBitmap.getHeight()/2;

            // 太鼓の中心座標と指の距離を計算
            double mDistance = Math.sqrt(Math.pow( (mCenterX - mTouchX),2)+ Math.pow((mCenterY-mTouchY),2));

            // 太鼓画像の半径
            int mTaikoR = myBitmap.getWidth()/2;

            //あたり判定
            if ( mDistance < mTaikoR) {
                // サウンド再生
                Log.i(TAG, "in Circle");
                mMediaPlayer.start();
            }

            // 再描画の指示
            //invalidate();
            //mMediaPlayer.seekTo(0);
            //mMediaPlayer.start();
            Log.i(TAG, "ACTION DOWN EVENT");

            //invalidate();


        }
        /*
        else if ( event.getAction() == MotionEvent.ACTION_MOVE) {
            // X,Y座標の取得
            //mTaikox = (int) event.getX();
            //mTaikoy = (int) event.getY();
            // 再描画の指示
            //invalidate();
            Log.i(TAG, "ACTION MOVE EVENT");
        } else if ( event.getAction() == MotionEvent.ACTION_UP) {
            Log.i(TAG, "ACTION UP EVENT:1");
            //mMediaPlayer.reset();
            //mMediaPlayer.release();
            mMediaPlayer.stop();
            //mMediaPlayer.reset();
            //mMediaPlayer.release();
            Log.i(TAG, "ACTION UP EVENT:2");

            try{
                mMediaPlayer.prepare();
            } catch ( IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/
        return true;
    }

}