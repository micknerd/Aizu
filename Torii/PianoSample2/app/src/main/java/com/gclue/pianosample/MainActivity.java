package com.gclue.pianosample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity  extends Activity implements View.OnClickListener {

    /** Button配列の定義。 */
    private ArrayList<Button> buttons = new ArrayList< Button >();
    /** サウンド配列の定義。 */
    private ArrayList<MediaPlayer> sounds = new ArrayList< MediaPlayer >();
    /** /res/layout/pianosample001_layout.xmlに記述したボタンの数。 */
    private int howManyButtons = 8;

    /** Data. */
    //private int[] mMerody = {0,1,2,1,2,3,1,2,3,1,2,3};

    /** Data. */
    private String mMerodyData = "{\"title\" : \"MySound\",\"data\" : [" +
            "{\"merody\" : 0,\"time\" : 500}," +
            "{\"merody\" : 1,\"time\" : 500}," +
            "{\"merody\" : 2,\"time\" : 500}," +
            "{\"merody\" : 1,\"time\" : 500}," +
            "{\"merody\" : 2,\"time\" : 300}," +
            "{\"merody\" : 3,\"time\" : 100}," +
            "{\"merody\" : 1,\"time\" : 500}," +
            "{\"merody\" : 2,\"time\" : 300}," +
            "{\"merody\" : 3,\"time\" : 100}," +
            "{\"merody\" : 1,\"time\" : 500}," +
            "{\"merody\" : 2,\"time\" : 300}," +
            "{\"merody\" : 3,\"time\" : 100},]}";

    /** Button play. */
    private Button mButtonPlay;

    /** Button get. */
    private Button mButtonGet;

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.activity_main );

        // Play Button
        mButtonPlay = (Button)findViewById(R.id.buttonPlay);
        mButtonPlay.setOnClickListener( this );

        mButtonGet = (Button)findViewById(R.id.buttonGet);
        mButtonGet.setOnClickListener( this );

        // /res/layout/pianosample_layout.xml に記述したボタンを読み込む
        addButtonAndSound(howManyButtons);
    }

    /**
     * ボタンをボタン配列に、サウンドをサウンド配列に格納する。
     * @param num ボタンの数
     */
    private void addButtonAndSound( int num ) {
        for ( int i = 0; i < num; i++ ) {
            String n = Integer.toString( i + 1 );

            // ボタンをボタン配列に格納する
            int buttonId = getResources().getIdentifier( "button" + n, "id", getPackageName() );
            Button mButton = (Button) findViewById( buttonId );
            mButton.setOnClickListener( this );
            buttons.add( mButton );

            // サウンドをサウンド配列に格納する
            // サウンドファイルがsound01のように、0が付いた2桁表示になっているため、数値の頭に0を付けた文字列を作成する
            if ( i < 10 ) {
                n = "0" + n;
            }
            int soundId = getResources().getIdentifier( "sound" + n, "raw", getPackageName() );

            MediaPlayer sound = MediaPlayer.create( this, soundId );
            sounds.add( sound );
        }
    }

    /**
     * コンポーネントがクリックされると呼び出される。
     */
    @Override
    public void onClick( View mView ) {
        if(mView.equals(mButtonPlay)) {

            /*
            for(int i = 0; i < mMerody.length; i++) {
                int onkai = mMerody[i];
                sounds.get(onkai).seekTo(0);
                sounds.get(onkai).start();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            */

            JSONObject mMerodyJson = null;
            try {
                // MYDATAという名前のSharedPreference
                SharedPreferences settings = this.getSharedPreferences("MYDATA", this.MODE_PRIVATE);
                String mMerodyData = settings.getString("DATA","");

                mMerodyJson = new JSONObject(mMerodyData);

                Log.i("PIANO", "title:" + mMerodyJson.get("title"));

                JSONArray datas = mMerodyJson.getJSONArray("data");

                for(int i = 0; i < datas.length(); i++) {
                    int onkai = datas.getJSONObject(i).getInt("merody");
                    //sounds.get(onkai).seekTo(0);
                    //sounds.get(onkai).start();
                    buttons.get(onkai).performClick();

                    int sleepTime = datas.getJSONObject(i).getInt("time");
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if(mView.equals(mButtonGet)) {
            Intent selectIntent = new Intent();
            selectIntent.setClassName("com.gclue.pianosample","com.gclue.pianosample.ListViewActivity");
            startActivity(selectIntent);
        } else {
            // button配列に格納してあるButtonを一つずつ取り出し、クリックされたView(mView)とButtonが一致した場合、音を再生する。
            for (int i = 0; i < howManyButtons; i++) {
                if (mView.equals(buttons.get(i))) {
                    Log.i("PIANO", "Button" + Integer.toString(i + 1) + "がクリックされました。");
                    sounds.get(i).seekTo(0);
                    sounds.get(i).start();
                    return;
                }
            }
        }
    }
}

