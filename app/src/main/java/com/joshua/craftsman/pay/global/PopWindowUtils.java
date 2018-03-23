package com.joshua.craftsman.pay.global;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.joshua.craftsman.R;
import com.joshua.craftsman.pay.util.PaySuccess;

import static com.joshua.craftsman.R.id.ll_popup;

/**
 * ============================================================
 * <p>
 * 版 权 ： 吴奇俊  (c) 2018
 * <p>
 * 作 者 : 吴奇俊
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2018/3/18 0:02
 * <p>
 * 描 述 ：
 * <p>
 * ============================================================
 **/

public class PopWindowUtils {
    public static void showPop(Activity activity, View parentView, final PayAction action){
        final PopupWindow pop = new PopupWindow(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.choose_to_pay, null);
        final LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_pop);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);

        LinearLayout bt1 = (LinearLayout) view
                .findViewById(R.id.ll_alipay);
        LinearLayout bt2 = (LinearLayout) view
                .findViewById(R.id.ll_weixinpay);

        ll_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action.aliPay();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                action.wxPay();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

}
