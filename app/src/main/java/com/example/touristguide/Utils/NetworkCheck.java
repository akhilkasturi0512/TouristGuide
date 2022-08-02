package com.example.touristguide.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;

import com.example.touristguide.R;




public class NetworkCheck {

	Context context;
	static AlertDialog alert;

	public NetworkCheck(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;

	}
	
	public  static  boolean isConnected(Context context)
	{
		Boolean flag=false;
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mMobileNewtork = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		if (mWifi.isConnected() || mMobileNewtork.isConnected()) {

			flag=true;
		}
			
		return flag;
		
	}

	public static void showNetworkFailureAlert(Context context) {
		if (context != null  && !((Activity)(context)).isFinishing()  && (alert==null ||  (alert != null && !alert.isShowing()))) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(context.getResources().getString(R.string.no_network_message)).setTitle(context.getResources().getString(R.string.no_internet))
					.setCancelable(false)
					.setNegativeButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

						}
					});
			alert = builder.create();
			alert.show();
			Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
			nbutton.setTextColor(context.getResources().getColor(R.color.app_color));
		}
	}
}
