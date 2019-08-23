package com.vmrits.android.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransactionHistory extends AppCompatActivity {
    private RecyclerView recyclerView_history;
    private Context context=TransactionHistory.this;
    private TransactionHistoryAdapter transactionHistoryAdapter;
    private ArrayList<TransactionHistoryPOJO> transactionHistoryArrayList;
    private LoginSessionManager loginSessionManager;
    private String string_mobile_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranasaction_history);
        transactionHistoryArrayList=new ArrayList<>();
        loginSessionManager=new LoginSessionManager(TransactionHistory.this);
        loginSessionManager.checkLogin();

        // get user data from session
        HashMap<String, String> user = loginSessionManager.getUserDetails();
        // number
        string_mobile_number = user.get(LoginSessionManager.KEY_MOBILE_NUMBER);

        initializeViews();
    }

    private void initializeViews() {
        recyclerView_history=findViewById(R.id.id_activity_transaction_history_recycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView_history.setLayoutManager(layoutManager);

        initilaizeAdapter();
        volleyLoadTransactions();
    }

    private void volleyLoadTransactions() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLUtility.TRANSACTIONS+"?phone="+string_mobile_number, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("transaction_response"+response);
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        TransactionHistoryPOJO transactionHistoryPOJO=new TransactionHistoryPOJO();
                        transactionHistoryPOJO.setTransaction_amount(jsonObject.getString("amount"));
                        transactionHistoryPOJO.setTransaction_approved_date(jsonObject.getString("approve_date"));
                        transactionHistoryPOJO.setTransaction_date(jsonObject.getString("paid_date"));
                        transactionHistoryArrayList.add(transactionHistoryPOJO);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Transaction_error:"+error.networkResponse);
                Toast.makeText(context,"Sorry! Transaction server not found!!",Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("phone",string_mobile_number);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,"transactions");
    }

    private void initilaizeAdapter() {
        transactionHistoryAdapter=new TransactionHistoryAdapter(context,transactionHistoryArrayList);
        recyclerView_history.setAdapter(transactionHistoryAdapter);

    }
}
