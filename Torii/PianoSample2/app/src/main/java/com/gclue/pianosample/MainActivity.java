package com.gclue.pianosample;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    /** Button1の定義。 */
    private Button mButton1;
    /** Button2の定義。 */
    private Button mButton2;
    /** Button3の定義。 */
    private Button mButton3;
    /** Button4の定義。 */
    private Button mButton4;
    /** Button5の定義。 */
    private Button mButton5;
    /** Button6の定義。 */
    private Button mButton6;
    /** Button7の定義。 */
    private Button mButton7;
    /** Button8の定義。 */
    private Button mButton8;

    /** サウンド1の定義。 */
    private MediaPlayer sound1;
    /** サウンド2の定義。 */
    private MediaPlayer sound2;
    /** サウンド3の定義。 */
    private MediaPlayer sound3;
    /** サウンド4の定義。 */
    private MediaPlayer sound4;
    /** サウンド5の定義。 */
    private MediaPlayer sound5;
    /** サウンド6の定義。 */
    private MediaPlayer sound6;
    /** サウンド7の定義。 */
    private MediaPlayer sound7;
    /** サウンド8の定義。 */
    private MediaPlayer sound8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.activity_main);

        mButton1 = (Button)findViewById(R.id.button1);
        mButton2 = (Button) findViewById( R.id.button2 );
        mButton3 = (Button) findViewById( R.id.button3 );
        mButton4 = (Button) findViewById( R.id.button4 );
        mButton5 = (Button) findViewById( R.id.button5 );
        mButton6 = (Button) findViewById( R.id.button6 );
        mButton7 = (Button) findViewById( R.id.button7 );
        mButton8 = (Button) findViewById( R.id.button8 );

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener( this );
        mButton3.setOnClickListener( this );
        mButton4.setOnClickListener( this );
        mButton5.setOnClickListener( this );
        mButton6.setOnClickListener( this );
        mButton7.setOnClickListener( this );
        mButton8.setOnClickListener( this );

        // サウンドを用意する
        sound1 = MediaPlayer.create( this, R.raw.sound01 );
        sound2 = MediaPlayer.create( this, R.raw.sound02 );
        sound3 = MediaPlayer.create( this, R.raw.sound03 );
        sound4 = MediaPlayer.create( this, R.raw.sound04 );
        sound5 = MediaPlayer.create( this, R.raw.sound05 );
        sound6 = MediaPlayer.create( this, R.raw.sound06 );
        sound7 = MediaPlayer.create( this, R.raw.sound07 );
        sound8 = MediaPlayer.create( this, R.raw.sound08 );

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

    @Override
    public void onClick(View mView) {
        // クリックされたViewがmButtonの場合
        if ( mView.equals( mButton1 ) ) {
            sound1.seekTo( 0 );
            sound1.start();
        }
        else if ( mView.equals( mButton2 ) ) {
            sound2.seekTo( 0 );
            sound2.start();
        }
        else if ( mView.equals( mButton3 ) ) {
            sound3.seekTo( 0 );
            sound3.start();
        }
        else if ( mView.equals( mButton4 ) ) {
            sound4.seekTo( 0 );
            sound4.start();
        }
        else if ( mView.equals( mButton5 ) ) {
            sound5.seekTo( 0 );
            sound5.start();
        }
        else if ( mView.equals( mButton6 ) ) {
            sound6.seekTo( 0 );
            sound6.start();
        }
        else if ( mView.equals( mButton7 ) ) {
            sound7.seekTo( 0 );
            sound7.start();
        }
        else if ( mView.equals( mButton8 ) ) {
            sound8.seekTo( 0 );
            sound8.start();
        }
    }
}
