package com.joshua.craftsman.pay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.joshua.craftsman.R;
import com.joshua.craftsman.pay.util.OrderInfoUtil2_0;


import java.util.Map;

/**
 *  重要说明:
 *  
 *  这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
 *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险； 
 */
public class PayDemoActivity extends FragmentActivity {
//
//	/** 支付宝支付业务：入参app_id */
//	public static final String APPID = "2017062307555030";
//	private static final int SDK_PAY_FLAG = 1;
//
//	@SuppressLint("HandlerLeak")
//	private Handler mHandler = new Handler() {
//		@SuppressWarnings("unused")
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case SDK_PAY_FLAG: {
//				@SuppressWarnings("unchecked")
//				PayResult payResult = new PayResult((Map<String, String>) msg.obj);
//				/**
//				 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//				 */
//				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
//				String resultStatus = payResult.getResultStatus();
//				// 判断resultStatus 为9000则代表支付成功
//				if (TextUtils.equals(resultStatus, "9000")) {
//					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//				} else {
//					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//					Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//				}
//				break;
//			}
//			default:
//				break;
//			}
//		}
//	};
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.pay_main);
//	}
//
//	/**
//	 * 支付宝支付业务
//	 *
//	 * @param v
//	 */
//	public void payV2(View v) {
//
//
//		/**
//		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
//		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
//		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
//		 *
//		 * orderInfo的获取必须来自服务端；
//		 */
//		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, 200,false);
//		//创建订单
//		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//		Log.i("msp", ">>>>>>>>>>>>>>>>>>>>>>>>>>orderParam:"+params.toString());
//		/**
//		 * 访问服务器
//		 * 上传订单信息
//		 */
//		String url="http://192.168.1.100:8080/diandongche/user/getOrder";
//
//		RequestParams order_params = new RequestParams(url);
//		order_params.addParameter("app_id",params.get("app_id"));
//		order_params.addParameter("biz_content",params.get("biz_content"));
//		order_params.addParameter("charset",params.get("charset"));
//		order_params.addParameter("method",params.get("method"));
//		order_params.addParameter("sign_type",params.get("sign_type"));
//		order_params.addParameter("timestamp",params.get("timestamp"));
//		order_params.addParameter("version",params.get("version"));
//		order_params.addParameter("orderParam",orderParam);
//		x.http().post(order_params, new Callback.CommonCallback<String>() {
//			@Override
//			public void onSuccess(String result) {
//				final String orderInfo = result.trim();
//				Log.i("msp","orderInfo:"+result);
//				Runnable payRunnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        PayTask alipay = new PayTask(PayDemoActivity.this);
//                        Map<String, String> result = alipay.payV2(orderInfo, true);
//                        Log.d("msp", "<<<<<<<<<<<<payResult>>>>>>>>>>: "+result.toString());
//						postPayResult(result);
//
//                    }
//                };
//
//				Thread payThread = new Thread(payRunnable);
//				payThread.start();
//
//			}
//
//			@Override
//			public void onError(Throwable ex, boolean isOnCallback) {
//				Log.i("msp", ">>>>>>>>>>>>>>>>>>>>>>>>>>:"+ex.getMessage());
//			}
//
//			@Override
//			public void onCancelled(CancelledException cex) {
//
//			}
//
//			@Override
//			public void onFinished() {
//
//			}
//		});
//
//	}
//
//	/**
//	 * 将支付结果发送给服务器
//	 */
//	private void postPayResult(Map<String,String > result) {
//		String url="http://192.168.1.100:8080/diandongche/user/verifyResult";
//		RequestParams result_params = new RequestParams(url);
//		result_params.addParameter("resultStatus",result.get("resultStatus"));
//		result_params.addParameter("result",result.get("result"));
//		result_params.addParameter("memo",result.get("memo"));
//
//
//		x.http().post(result_params, new Callback.CommonCallback<String>() {
//			@Override
//			public void onSuccess(String result) {
//				if (TextUtils.equals(result, "9000")) {
//					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
//				} else {
//					Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
//				}
//			}
//
//			@Override
//			public void onError(Throwable ex, boolean isOnCallback) {
//				Log.i("msp", ">>>>>>>>>>>>>>>>>>>>>>>>>>o2:"+ex.getMessage());
//			}
//
//			@Override
//			public void onCancelled(CancelledException cex) {
//
//			}
//
//			@Override
//			public void onFinished() {
//
//			}
//		});
//
//	}
//
//
//	/**
//	 * get the sdk version. 获取SDK版本号
//	 *
//	 */
//	public void getSDKVersion() {
//		PayTask payTask = new PayTask(this);
//		String version = payTask.getVersion();
//		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
//	}



}
