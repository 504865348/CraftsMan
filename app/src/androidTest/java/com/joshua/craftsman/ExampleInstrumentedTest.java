package com.joshua.craftsman;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.joshua.craftsman.activity.MainActivity;
import com.joshua.craftsman.entity.Server;
import com.joshua.craftsman.http.HttpCommonCallback;
import com.joshua.craftsman.http.HttpCookieJar;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.joshua.craftsman.R.id.et_pwd;
import static com.joshua.craftsman.R.id.et_username;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest extends Activity{
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.joshua.craftsman", appContext.getPackageName());
    }

private static final String TAG="LOG";
    @Test
    public void apiTest() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.d(TAG, "login: " + "connecting");

        OkHttpClient mClient= new OkHttpClient.Builder()
                .cookieJar(new HttpCookieJar((appContext)))
                .build();
        RequestBody params = new FormBody.Builder()
                .add("method", Server.SERVER_LOGIN)
                .add("type", "normal")
                .build();

        final Request request = new Request.Builder()
                .url(Server.SERVER_REMOTE)
                .post(params)
                .build();
        Call call = mClient.newCall(request);
        call.enqueue(new HttpCommonCallback(this) {
            @Override
            protected void success(String result) {
                Log.d(TAG, "success: "+result);
            }

            @Override
            protected void error() {

            }
        });
    }
    }

