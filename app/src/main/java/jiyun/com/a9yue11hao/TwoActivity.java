package jiyun.com.a9yue11hao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.ArrayList;

public class TwoActivity extends Activity {

    Banner mbanner;
    ArrayList<String> images = new ArrayList<>();
    TextView text;
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initView();
        Intent intent = getIntent();
        String image = intent.getStringExtra("image");
        String title = intent.getStringExtra("title");
        String count = intent.getStringExtra("count");
        for (int i = 0; i <3 ; i++) {
            images.add(image);

        }

        text.setText(title);
        text1.setText(count);
        Banner banner = (Banner) findViewById(R.id.mbanner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoder());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();



    }




    private void initView() {
        mbanner= (Banner) findViewById(R.id.mbanner);
        text= (TextView) findViewById(R.id.tet1);
        text1= (TextView) findViewById(R.id.text1);
    }

}
