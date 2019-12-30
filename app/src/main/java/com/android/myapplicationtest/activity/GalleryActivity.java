package com.android.myapplicationtest.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;
import com.android.myapplicationtest.R;
import com.android.myapplicationtest.annotation.TestAnnotation;
import com.android.myapplicationtest.hello.HelloWorld;
import com.android.myapplicationtest.util.LogUtil;

import androidx.recyclerview.widget.RecyclerView;


//@Route(path = "/activity")
//@Hello("MainTest")   //自定义的Hello注解
@TestAnnotation("abc")
public class GalleryActivity extends Activity implements ViewSwitcher.ViewFactory {

    //@BindView(R.id.gallery)
    Gallery gallery;
    //private MyGallery gallery;
    private ImageSwitcher imageSwitcher;
    private ImageAdapter imageAdapter;

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
       // BindViewTools.bind(this);

        gallery = findViewById(R.id.gallery);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.is);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        initGallery();

        // 需要编译之后才会有
        HelloWorld.test();


        initRecyclerView();
    }

    @Override
    public View makeView() {
        ImageView image = new ImageView(this);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return image;
    }


    private void initGallery() {
        //设置图片适配器   
        imageAdapter = new ImageAdapter(this);
        gallery.setAdapter(imageAdapter);
        gallery.setSpacing(10);//图与图之间的横向距离
        //gallery.setSelection(2);
        //设置监听器       
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                LogUtil.e("galley", "onItemClick 点击 " + position + "张图片");
            }
        });
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                LogUtil.e("galley", "onItemSelected 选中 " + position + "张图片");
                // 选中Gallery中某个图像时，放大显示该图像
                imageAdapter.setSelectItem(position);
                imageAdapter.notifyDataSetChanged();//当滑动时，事件响应，通知适配器更新数据

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        imageSwitcher.setFactory(this);
        //添加动画
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
    }


    private class ImageAdapter extends BaseAdapter {

        private Context context;
        //图片源数组   
        private Integer[] imageInteger = {
                R.drawable.logo, R.drawable.phone_order, R.drawable.timg, R.drawable.vp
        };
        private int selectItem;

        public ImageAdapter(Context c) {
            context = c;
        }

        public void setSelectItem(int selectItem) {
            this.selectItem = selectItem;
        }

        // 获取图片的个数   
        @Override
        public int getCount() {
            //return imageInteger.length;
            //1
            return Integer.MAX_VALUE;//最大值能使图片无限滑动
        }

        // 获取图片在库中的位置     
        @Override
        public Object getItem(int position) {
            return imageInteger[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("galley", "position=" + position + ",res的角标=" + position % imageInteger.length);

            ImageView imageView = new ImageView(context);
            // 给ImageView设置资源       
            //imageView.setImageResource(imageInteger[position]);
            //2
            imageView.setImageResource(imageInteger[position % imageInteger.length]);//实现循环滑动
            // 设置显示比例类型         
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 设置布局 图片120*80        

            //imageView.setLayoutParams(new Gallery.LayoutParams(120, 80));
            //imageView.setLayoutParams(new Gallery.LayoutParams(200, 160));
            if (selectItem == position) {
                imageView.setLayoutParams(new Gallery.LayoutParams(320, 240));
            } else {
                imageView.setLayoutParams(new Gallery.LayoutParams(160, 120));//未选中
            }
            return imageView;
        }

    }


    private void initRecyclerView(){



    }
}
