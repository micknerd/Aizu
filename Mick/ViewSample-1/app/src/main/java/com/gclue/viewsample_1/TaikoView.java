package com.gclue.viewsample_1;

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
import android.view.ViewDebug;

import java.io.IOException;

/**
 * 描画用のクラス.
 */
class TaikoView extends View {

    /**
     * コンストラクタ.
     *
     * @param context コンテキスト
     */

    private Bitmap TaikoImage;

    private final static String TAG = "DRAW";

    /**
     * X.
     */
    private int mTaikoX=100;

    /**
     * Y.
     */
    private int mTaikoY=50;


    /**
     * サウンド再生データを保持する.
     */
    private MediaPlayer mMediaPlayer;


    public TaikoView(Context context) {
        super(context);
        setFocusable(true);

        // Resourceインスタンスの生成
        Resources res = this.getContext().getResources();
        // 画像の読み込み(res/drawable/gclue_logo.gif)
        TaikoImage = BitmapFactory.decodeResource(res, R.drawable.taiko);
        // サウンドデータを読み込む（/res/raw/pon.mp3）
        mMediaPlayer = MediaPlayer.create(context, R.raw.pon);

    }

    /**
     * 描画処理を行う.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 背景色を設定
        canvas.drawColor(Color.BLACK);

        // 描画するための線の色を設定.
        Paint mPaint = new Paint();
        canvas.drawBitmap(TaikoImage, mTaikoX, mTaikoY, mPaint);

        // ② 四隅をとる.
        int viewWidth = this.getWidth();
        int viewHeight = this.getHeight();
        int imgWidth = TaikoImage.getWidth();
        int imgHeight = TaikoImage.getHeight();

        /*
        Log.i(TAG, "viewWidth:" + viewWidth);
        Log.i(TAG, "viewHeight:" + viewHeight);
        Log.i(TAG, "X:" + x);
        Log.i(TAG, "Y:" + y);
        */

    }


    /**
     * タッチイベント.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // タッチした時に実行
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // X,Y座標の取得
            //mTaikoX = (int) event.getX();
            //mTaikoY = (int) event.getY();

            // 指がタッチされたx,y座標の取得.
            int mTouchX = (int) event.getX();
            int mTouchY = (int) event.getY();

            Log.i(TAG, "touch X:" + mTouchX);
            Log.i(TAG, "touch Y:" + mTouchY);

            // 太鼓の中心座標を計算
            int mCenterX = mTaikoX + TaikoImage.getWidth()/2;
            int mCenterY = mTaikoY + TaikoImage.getHeight()/2;

            // 太鼓の中心座標と指の距離を計算
            double mDistance = Math.sqrt(Math.pow( (mCenterX - mTouchX),2)+ Math.pow( (mCenterY - mTouchY),2));

            // 太鼓画像の半径
            int mTaikoR = TaikoImage.getWidth()/2;

            // あたり判定
            if ( mDistance < mTaikoR ) {
                // サウンド再生
                Log.i(TAG, "X:" + mTaikoX);
                Log.i(TAG, "Y:" + mTaikoY);
                mMediaPlayer.start();
            }
        }
        
            /*
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // X,Y座標の取得
            mTaikoX = (int) event.getX();
            mTaikoY = (int) event.getY();
            // 再描画の指示
            invalidate();
      } else if ( event.getAction() == MotionEvent.ACTION_UP ) {
            // ACTION_UPは指が離れた時.
            // 音を停止する.
            mMediaPlayer.stop();

            // 一度再生をstop()してから再び音を再生する場合は、prepare()を呼び出す必要がある
            try {
                mMediaPlayer.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }*/
        return true;
    }
}
