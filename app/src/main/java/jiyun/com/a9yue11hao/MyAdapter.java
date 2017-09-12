package jiyun.com.a9yue11hao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jiyun.com.a9yue11hao.Bean.Entity;

/**
 * Created by 97387 on 2017/9/11.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Entity.DataBean> arrayList;
    private Context context;
    private MyHolder holder;

    public MyAdapter(ArrayList<Entity.DataBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            holder = new MyHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            holder.imageView = (ImageView) view.findViewById(R.id.mimage);
            holder.textView = (TextView) view.findViewById(R.id.tv_title);
            holder.textView1 = (TextView) view.findViewById(R.id.tv_count);
            view.setTag(holder);
        } else
            Glide.with(context).load(arrayList.get(i).getGoods_img()).into(holder.imageView);
        holder.textView.setText(arrayList.get(i).getEfficacy());
        holder.textView1.setText(arrayList.get(i).getGoods_name());
        holder = (MyHolder) view.getTag();
        return view;
    }

    class MyHolder {
        ImageView imageView;
        TextView textView, textView1;
    }
}


