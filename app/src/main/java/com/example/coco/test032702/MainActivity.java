package com.example.coco.test032702;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn;
    private Button mBtn2;

    //上传数据的网址
    String HOST = "https://api.tianapi.com/";
    String KEY = "d73d3c76a06d24d93645d3fd735bf449";
    String imgUrl = "http://p3.so.qhmsg.com/sdr/534_768_/t012223d2b2eed285b8.jpg";
    HashMap<String, String> map = new HashMap<>();//上传数据的map集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //查找控件
        mBtn = findViewById(R.id.mBtn);
        mBtn2 = findViewById(R.id.mBtn2);

        mBtn.setOnClickListener(this);
        mBtn2.setOnClickListener(this);

        initDate();//初始化数据源


    }

    private void initDate() {
        //将所需的数据存入map集合
        map.put("key", KEY);
        map.put("page", 1 + "");
        map.put("num", 10 + "");
        map.put("img",imgUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtn://点击上传数据的btn
                OkUtils.UploadSJ(HOST, map, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {//上传失败的方法
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "上传数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {//上传成功的方法
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(MainActivity.this, "上传数据成功", Toast.LENGTH_SHORT).show();
                           }
                       });

                    }
                });

                break;
            case R.id.mBtn2://上传图片的btn
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();//上传图片的路径

                OkUtils.UploadImage(HOST, imgUrl, path, map, new Callback() {//上传图片失败的方法
                    @Override
                    public void onFailure(Call call, IOException e) {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               Toast.makeText(MainActivity.this, "upload img failure", Toast.LENGTH_SHORT).show();
                           }
                       });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {//图片上传成功的方法
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              Toast.makeText(MainActivity.this, "upload img success", Toast.LENGTH_SHORT).show();
                          }
                      });


                    }
                });
                break;
        }
    }
}
