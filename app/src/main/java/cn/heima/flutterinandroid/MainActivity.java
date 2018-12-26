package cn.heima.flutterinandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import io.flutter.facade.Flutter;

public class MainActivity extends AppCompatActivity {

    private int width;
    private int height;
    private View flutterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toFlutter(View view) {
        getAndroiodScreenProperty();
        flutterView = Flutter.createView(
                MainActivity.this,
                getLifecycle(),
                "route1"
        );
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(width, height);
//        layout.leftMargin = 100;
//        layout.topMargin = 200;
        addContentView(flutterView, layout);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (flutterView!=null){
                ViewGroup vg = (ViewGroup) flutterView.getParent();
                vg.removeView(flutterView);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getAndroiodScreenProperty() {
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        // 屏幕宽度（像素）
        width = dm.widthPixels;
        // 屏幕高度（像素）
        height = dm.heightPixels;
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)

//
//        Log.d("h_bl", "屏幕宽度（像素）：" + width);
//        Log.d("h_bl", "屏幕高度（像素）：" + height);
//        Log.d("h_bl", "屏幕密度（0.75 / 1.0 / 1.5）：" + density);
//        Log.d("h_bl", "屏幕密度dpi（120 / 160 / 240）：" + densityDpi);
//        Log.d("h_bl", "屏幕宽度（dp）：" + screenWidth);
//        Log.d("h_bl", "屏幕高度（dp）：" + screenHeight);
    }
}
