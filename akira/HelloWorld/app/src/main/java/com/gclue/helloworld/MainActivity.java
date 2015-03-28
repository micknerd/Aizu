package com.gclue.helloworld;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 描画するための線の色を設定.
        Paint mPaint = new Paint();
        mPaint.setStyle( Paint.Style.FILL );
        mPaint.setStrokeWidth(10);
        mPaint.setARGB( 255, 255, 0, 0 );

        // 線で描画.
        canvas.drawLine( 0, 0, 500, 500, mPaint );

        // 描画するための線の色を設定.
        mPaint = new Paint();
        mPaint.setStyle( Paint.Style.FILL );
        mPaint.setStrokeWidth(10);
        mPaint.setARGB( 255, 255, 255, 0 );

        // 線で描画.
        canvas.drawLine( 0, 0, 500, 500, mPaint2 );
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i("LIFE", "onStart()" );
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i("LIFE", "onResume()" );
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i("LIFE", "onPause()" );
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i("LIFE", "onStop()" );
    }

    @Override
    public void onRestart() {
        super.onRestart();

        Log.i("LIFE", "onRestart()" );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("LIFE", "onDestroy()");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
