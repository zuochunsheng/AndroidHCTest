package com.android.myapplicationtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.myapplicationtest.R;

public class StaticLayoutActivity extends AppCompatActivity {

    private String text = "我是找的别人的思路，但是里面逻辑处理比较垃圾，问题多，我就多研究了会实现原理，知道了这个布局的一些api，改造了比较简单通俗易懂处理方式，还可以兼容列表情况下的展示,现在项目遇到了一个需求文字能够自动换行，本来想通过当前view的宽度和字体大小进行处理。在查阅资料后，发现android本身就提供了这方面的功能。StaticLayout能够让文字进行自动行，直接上代码";
    private int maxLine = 3;
    private int lineCount;

    private boolean isZhankai = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_layout);
        TextView textView = findViewById(R.id.tv);
        TextView tvSitch = findViewById(R.id.tv_switch);

        textView.setText(text);

        textView.post(()->{
            lineCount = textView.getLineCount();
            if(lineCount > 3){
                textView.setMaxLines(3);
                tvSitch.setVisibility(View.VISIBLE);
                isZhankai = true;
            }else {
                tvSitch.setVisibility(View.GONE);
                isZhankai = false;
            }

        });

        tvSitch.setOnClickListener((view)->{
            if(lineCount > 3){
                if(isZhankai){
                    textView.setMaxLines(3);
                    tvSitch.setText("...展开");
                    tvSitch.setVisibility(View.VISIBLE);
                }else {
                    textView.setMaxLines(lineCount);
                    tvSitch.setText("收缩");
                    tvSitch.setVisibility(View.VISIBLE);
                }
                isZhankai = !isZhankai;
            }else {

            }

        });

    }
}