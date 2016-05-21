package com.niks.gcmdemo;

import android.content.Context;
import android.content.Intent;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	public GCMIntentService() {
		super(CommonUtilities.SENDER_ID);
	}

	private static final String TAG = "debug";

	@Override
	protected void onRegistered(Context arg0, String registrationId) {
		CustomLogger.Log(TAG, "onRegistered = " + registrationId);
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		CustomLogger.Log(TAG, "onUnregistered = " + arg1);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		CustomLogger.Log(TAG, "onMessage");
		String message = intent.getExtras().getString("message");
		CustomLogger.Log(TAG,message);
	}

	@Override
	protected void onError(Context arg0, String errorId) {
		CustomLogger.Log(TAG, "onError " + errorId);
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		return super.onRecoverableError(context, errorId);
	}

}
