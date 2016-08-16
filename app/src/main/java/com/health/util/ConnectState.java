package com.health.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectState {
	private static Context context;
	public ConnectState(Context context){
		this.context=context;
	}
	
	public static boolean getWiFiState(){
	ConnectivityManager con=(ConnectivityManager)context.getSystemService(Activity.CONNECTIVITY_SERVICE);  
	NetworkInfo networkInfo = con.getActiveNetworkInfo();  
    if(networkInfo == null || !networkInfo.isAvailable())  
    {  
        T.showShort(context, "当前无可用网络!");
        return false;
    }  
    else   
    {  
    	return true;
    }  
}
}
