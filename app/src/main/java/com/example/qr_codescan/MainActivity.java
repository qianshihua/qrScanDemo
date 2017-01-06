package com.example.qr_codescan;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private final static int SCANNIN_GREQUEST_CODE = 1;
	/**
	 * 显示扫描结果
	 */
	private TextView mTextView ;
	private boolean nextShouldBeOrder = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.result);
//		mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
		mTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
		//点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
		//扫描完了之后调到该界面
		Button mButton = (Button) findViewById(R.id.button1);
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String errMsg = checkOrderValid();
				if(errMsg==null || "".equals(errMsg)){
					gotoScanPage();
				}else{
					AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle("提示信息").
							setMessage(errMsg).
							setIcon(R.drawable.ic_launcher).create();
					alertDialog.show();
				}

			}
		});
	}

	/**
	 *
	 * 订单文本框是否有内容。有内容则校验订单是否有效，没有内容则扫描的一定要是订单编号。需要计算出接下来是否是订单
	 *
	 * @return true表示继续扫描。false提示错误信息
     */
	private String checkOrderValid() {
//		return "111111揭晓来要侧击测试很长的中文信息看下效果回事咋回事呢样是";
		nextShouldBeOrder=true;
		return "";
	}

	/**
	 * 跳转到扫描二维码的界面
	 */
	private void gotoScanPage() {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, MipcaActivityCapture.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case SCANNIN_GREQUEST_CODE:
				if(resultCode == RESULT_OK){
					Bundle bundle = data.getExtras();
					//显示扫描到的内容
					String orderOrQrcode = bundle.getString("result");
					parseOrderOrQr(orderOrQrcode);
					mTextView.setText(mTextView.getText()+"\r\n"+ orderOrQrcode);
					gotoScanPage();
					//显示
//				mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
				}
				break;
		}
	}

	/**
	 * 判断是订单号还是二维码
	 * @param orderOrQrcode
	 * @return
     */
	private String parseOrderOrQr(String orderOrQrcode) {
		return "";
	}

}
