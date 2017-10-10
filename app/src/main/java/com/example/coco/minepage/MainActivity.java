package com.example.coco.minepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv;
    private LinearLayoutManager manager;
    private int screenHeight;
    private int screenwWidth,height,width;
    private MineAdapter mineAdapter;
    private ViewGroup.LayoutParams layoutParams;
    private int preHeight;
    private float downY;
    private float my;
    private float distanceY;
    private float newHeight;
    private float newWidth;
    private boolean isDone=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //查找id并为recycler view设置适配器绑定数据
        mRv = (RecyclerView) findViewById(R.id.mRv);
        manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        mineAdapter = new MineAdapter(this);
        mRv.setAdapter(mineAdapter);


        //对rv进行监听
        initListener(mRv);

        //获取屏幕的宽高
        WindowManager manager1 = getWindowManager();
        Display display = manager1.getDefaultDisplay();
        screenHeight = display.getHeight();
        screenwWidth = display.getWidth();


    }

    private void initListener(RecyclerView mRv) {
        mRv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //当手指按下的时候，如果item 0 位置不为空，就获取头部img的宽高属性
                    case MotionEvent.ACTION_DOWN:
                        if (mineAdapter.headViewHolder == null) {
                            return false;
                        }
                        layoutParams = mineAdapter.headViewHolder.mImg.getLayoutParams();
                        height = layoutParams.height;
                        //获取原始高度
                        if (preHeight == 0) {
                            preHeight = height;
                        }
                        //拿到手指按下时y的坐标
                        downY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //获取手指滑动后的距离
                        my = event.getY();
                        //滑动距离减去手指按下的距离
                        distanceY = my - downY;
                        //新的高度几位原始高度+滑动的距离
                        newHeight = preHeight + distanceY;
                        newWidth = screenwWidth + distanceY * 0.5f;
                        //新旧高度的比较
                        if (newHeight < preHeight) {
                            newHeight = preHeight;
                            newWidth = screenwWidth;
                        }else{
                            if (newHeight>=screenHeight/2){
                                newHeight=screenHeight/2;
                                newWidth= (float) (screenwWidth+(newHeight-preHeight)*0.5);

                            }
                        }
                        //更新适配器
                        layoutParams.height= (int) newHeight;
                        layoutParams.width= (int) newWidth;
                        mineAdapter.headViewHolder.mImg.setLayoutParams(layoutParams);


                        break;
                    case MotionEvent.ACTION_UP:
//                        float uy = event.getY();
//                        distanceY= uy - downY;
//                        newHeight=distanceY+preHeight;
//                        newWidth= screenwWidth+distanceY*0.5f;
//                        if (newHeight<preHeight){
//                            newHeight=preHeight;
//                            newWidth=screenwWidth;
//                            isDone=false;
//                        }else {
//                            if (newHeight >= screenHeight/2){
//                                newHeight=screenHeight/2;
//                                newWidth=screenwWidth+(newHeight-preHeight)*0.5f;
//                            }
//                        }
                        layoutParams.height= preHeight;
                        layoutParams.width=screenwWidth;
                        mineAdapter.headViewHolder.mImg.setLayoutParams(layoutParams);


//                        ExecutorService single = Executors.newSingleThreadExecutor();
//                        single.execute(new Runnable() {
//                            @Override
//                            public void run() {
//                                while(newHeight>preHeight){
//                                    SystemClock.sleep(5);
//                                    //newHeight递减
//                                    newHeight-=10;
//                                    newWidth-=5;
//                                    layoutParams.height= (int) newHeight;
//                                    layoutParams.width= (int) newWidth;
//                                    runOnUiThread(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            mineAdapter.headViewHolder.mImg.setLayoutParams(layoutParams);
//                                        }
//                                    });
//                                }
//                            }
//                        });
                        break;
                }
                return false;
            }
        });
    }
}
