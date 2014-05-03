package com.loopj.android.http.sample;

import com.loopj.android.http.handlers.AsyncHttpIResponseHandler;
import com.loopj.android.http.interfaces.IAsyncHttpClient;
import com.loopj.android.http.interfaces.IRequestHandle;
import com.loopj.android.http.interfaces.IResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class PostSample extends SampleParentActivity {
    private static final String LOG_TAG = "PostSample";

    @Override
    public IRequestHandle executeSample(IAsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, IResponseHandler responseHandler) {
        return client.post(this, URL, headers, /*entity*/ null, responseHandler);
    }

    @Override
    public int getSampleTitle() {
        return R.string.title_post_sample;
    }

    @Override
    public boolean isRequestBodyAllowed() {
        return true;
    }

    @Override
    public boolean isRequestHeadersAllowed() {
        return true;
    }

    @Override
    public String getDefaultURL() {
        return "http://httpbin.org/post";
    }

    @Override
    public IResponseHandler getResponseHandler() {
        return new AsyncHttpIResponseHandler() {

            @Override
            public void onStart() {
                clearOutputs();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugResponse(LOG_TAG, new String(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugThrowable(LOG_TAG, e);
                if (errorResponse != null) {
                    debugResponse(LOG_TAG, new String(errorResponse));
                }
            }
        };
    }
}

