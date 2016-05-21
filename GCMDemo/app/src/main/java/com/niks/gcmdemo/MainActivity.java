package com.niks.gcmdemo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    private String TAG = "debug";
    private TextView mDisplay;
    String regId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkNotNull(CommonUtilities.SENDER_ID, "SENDER_ID");
        GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);

        mDisplay = (TextView) findViewById(R.id.textView1);

        regId = GCMRegistrar.getRegistrationId(this);
        CustomLogger.Log("debug", regId);

        if (regId.equals("")) {
            GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
        } else {
            CustomLogger.Log(TAG, "Already registered");
        }
        new sendIdOnOverServer().execute();
        mDisplay.setText("RegId=" + regId);
    }

    private void checkNotNull(Object reference, String name) {
        if (reference == null) {
            throw new NullPointerException(getString(R.string.error_config));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//		GCMRegistrar.unregister(this);
    }

    public class sendIdOnOverServer extends AsyncTask<String, Void, String> {

        ProgressDialog pd = null;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(MainActivity.this, "Please wait", "Loading please wait..", true);
            pd.setCancelable(true);

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                HttpResponse response = null;
                HttpParams httpParameters = new BasicHttpParams();
                HttpClient client = new DefaultHttpClient(httpParameters);
                String url = "http://mygo-niksworld.rhcloud.com/webserver/gcm/gcmserverpage.php?" + "&regID=" + regId + "msg=Hi";
                CustomLogger.Log("debug", url);
                HttpGet request = new HttpGet(url);

                response = client.execute(request);

                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));

                String webServiceInfo = "";
                while ((webServiceInfo = rd.readLine()) != null) {
                    CustomLogger.Log("debug", "Webservice: " + webServiceInfo);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            try {
                CustomLogger.Log("debug", "response");
                CustomLogger.Log("debug", result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pd.dismiss();

        }

    }

}
