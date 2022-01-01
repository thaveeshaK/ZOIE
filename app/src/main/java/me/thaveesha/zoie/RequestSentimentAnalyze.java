package me.thaveesha.zoie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestSentimentAnalyze extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_sentiment_analyze);

        EditText editText = findViewById(R.id.txtGetSentence);
        Button button = findViewById(R.id.btnGetAnalyzed);
        textView = findViewById(R.id.txtResponseView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAnalysis(editText.getText().toString());
            }
        });


    }

    public void getAnalysis(String req){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(RequestSentimentAnalyze.this);
        String url ="http://thaveeshazoie.pythonanywhere.com?string="+ req;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println("Response is: "+ response);
                        textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        }){
            protected Map<String,String> getParams(){
                Map<String,String> paramV = new HashMap<>();
                paramV.put("string","I am angry");
                return paramV;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
class AppController {

    private static AppController mInstance;

    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private AppController(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized AppController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AppController(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(@NonNull final Request<T> request) {
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueueWithTag(@NonNull final Request<T> request, String tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
}
