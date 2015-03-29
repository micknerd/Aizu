package com.gclue.pianosample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ListViewActivity extends Activity {

    /**
     * ListView
     */
    private ListView mListView;

    /** Data. */
    private String mMerodyData[];

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        // Adapterの作成
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        // 値を追加
        mAdapter.add("曲A");
        mAdapter.add("曲B");
        mAdapter.add("曲C");

        mMerodyData = new String[3];
        // 曲Aデータ
        mMerodyData[0]
                = "{\"title\" : \"MySound\",\"data\" : [" +
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

        // 曲Bデータ
        mMerodyData[1]
                = "{\"title\" : \"MySound\",\"data\" : [" +
                "{\"merody\" : 6,\"time\" : 500}," +
                "{\"merody\" : 5,\"time\" : 500}," +
                "{\"merody\" : 4,\"time\" : 500}," +
                "{\"merody\" : 3,\"time\" : 500}," +
                "{\"merody\" : 2,\"time\" : 300}," +
                "{\"merody\" : 2,\"time\" : 100}," +
                "{\"merody\" : 2,\"time\" : 500}," +
                "{\"merody\" : 3,\"time\" : 300}," +
                "{\"merody\" : 4,\"time\" : 100}," +
                "{\"merody\" : 5,\"time\" : 500}," +
                "{\"merody\" : 6,\"time\" : 300}," +
                "{\"merody\" : 7,\"time\" : 100},]}";

        // 曲Cデータ
        mMerodyData[2] = "{\"title\" : \"MySound\",\"data\" : [" +
                "{\"merody\" : 3,\"time\" : 500}," +
                "{\"merody\" : 4,\"time\" : 500}," +
                "{\"merody\" : 2,\"time\" : 500}," +
                "{\"merody\" : 1,\"time\" : 500}," +
                "{\"merody\" : 2,\"time\" : 300}," +
                "{\"merody\" : 3,\"time\" : 100}," +
                "{\"merody\" : 3,\"time\" : 500}," +
                "{\"merody\" : 3,\"time\" : 300}," +
                "{\"merody\" : 3,\"time\" : 100}," +
                "{\"merody\" : 3,\"time\" : 500}," +
                "{\"merody\" : 3,\"time\" : 300}," +
                "{\"merody\" : 3,\"time\" : 100},]}";

        // ListViewの取り込み
        mListView = (ListView)findViewById(R.id.listView1);

        // アダプターをセット
        mListView.setAdapter(mAdapter);

        // Contextを取得.
        final Context mContext =  this.getBaseContext();

        // クリックされた時のイベントを追加
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*
                // MYDATAという名前のSharedPreference.
                SharedPreferences settings = mContext.getSharedPreferences("MYDATA", mContext.MODE_PRIVATE);

                // SharePreferenceに保存.
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("DATA", mMerodyData[position]);
                editor.commit();
*/
                final int mpos = position;
                (new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String response = "";
                        DefaultHttpClient client = new DefaultHttpClient();
                        //HttpGet httpGet = new HttpGet("https://raw.githubusercontent.com/micknerd/Aizu/master/akira/data" + mpos + ".json");
                        HttpGet httpGet = new HttpGet("http://52.68.52.167/torii/getMelodyData.php?index=" + mpos);
                        try {
                            HttpResponse execute = client.execute(httpGet);
                            InputStream content = execute.getEntity().getContent();

                            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                            String line = "";
                            while ((line = buffer.readLine()) != null) {
                                response += line;
                            }

                            Log.i("LIFE", response);
                        } catch (Exception e) {
                            Log.i("LIFE", "error" + e);

                        }

                        // MYDATAという名前のSharedPreference.
                        SharedPreferences settings = mContext.getSharedPreferences("MYDATA", mContext.MODE_PRIVATE);

                        // SharePreferenceに保存.
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("DATA", response);
                        editor.commit();
                    }
                })).start();

                finish();

            }
        });
    }
}