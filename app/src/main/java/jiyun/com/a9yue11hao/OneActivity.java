package jiyun.com.a9yue11hao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import jiyun.com.a9yue11hao.Bean.Entity;

public class OneActivity extends Activity {
    ListView mlist;
    private ProgressDialog myDialog;
    private int count = 1;
    private ArrayList<Entity.DataBean> arrayList = new ArrayList<>();
    private MyAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        mlist= (ListView) findViewById(R.id.mlist);
        adapter = new MyAdapter(arrayList,this);
        mlist.setAdapter(adapter);
        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(OneActivity.this,TwoActivity.class);
                intent.putExtra("image",arrayList.get(i).getGoods_img());
                intent.putExtra("title",arrayList.get(i).getEfficacy());
                intent.putExtra("count",arrayList.get(i).getGoods_name());
                startActivity(intent);
            }
        });
        rec();
        initData();
    }

    private void initData() {
        OkhttpUtils.getInstance().getNetData("http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17", new OkhttpUtils.CallBacks() {
            @Override
            public void getString(String ss) {
                Gson gson = new Gson();
                Entity json = gson.fromJson(ss, Entity.class);
                List<Entity.DataBean> data = json.getData();
                arrayList.addAll(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        myDialog.dismiss();
                    }
                });
            }
        });
    }

    private void rec() {
        myDialog = new ProgressDialog(OneActivity.this); // 得到一个对象
        myDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // 设置为矩形进度条
        myDialog.setTitle("提示");
        myDialog.setMessage("数据加载中，请稍后...");
        myDialog.setIcon(R.mipmap.ic_launcher);
        myDialog.setIndeterminate(false); // 设置进度条是否为不明确
        myDialog.setCancelable(true);
        myDialog.setMax(100); // 设置进度条的最大值
        myDialog.setProgress(0); // 设置当前默认进度为 0
        myDialog.setSecondaryProgress(1000); // 设置第二条进度值为100

        // 为进度条添加取消按钮
        myDialog.setButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                myDialog.cancel();
            }
        });

        myDialog.show(); // 显示进度条
        new Thread() {
            public void run() {
                while (count <= 100) {
                    myDialog.setProgress(count++);
                    try {
                        Thread.sleep(100);  //暂停 0.1秒
                    } catch (Exception e) {
                        Log.i("msg", "线程异常..");
                    }
                }
                initData();
            }
        }.start();

    }


}

