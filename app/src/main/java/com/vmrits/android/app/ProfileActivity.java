package com.vmrits.android.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private TextView textView_full_Name,textView_fName,textView_mName,textView_mNumber,textView_dob,textView_address,textView_gender,textView_rName;
    private LoginSessionManager loginSessionManager;
    private String string_mobile_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loginSessionManager=new LoginSessionManager(ProfileActivity.this);
        loginSessionManager.checkLogin();
        loginSessionManager.checkLogin();

        // get user data from session
        HashMap<String, String> user = loginSessionManager.getUserDetails();
        // number
        string_mobile_number = user.get(LoginSessionManager.KEY_MOBILE_NUMBER);


        initialiazeViews();
    }

    private void initialiazeViews() {
        textView_full_Name=findViewById(R.id.id_activity_profile_name_textView);
        textView_fName=findViewById(R.id.id_activity_profile_father_name_textView);
        textView_mName=findViewById(R.id.id_activity_profile_mother_name_textView);
        textView_mNumber=findViewById(R.id.id_activity_profile_mobile_no_textView);
        textView_dob=findViewById(R.id.id_activity_profile_dob_textView);
        textView_address=findViewById(R.id.id_activity_profile_pAddress_textView);
        textView_gender=findViewById(R.id.id_activity_profile_gender_textView);
        textView_rName=findViewById(R.id.id_activity_profile_reference_name_textView);

        loadProfileData();
    }

    private void loadProfileData() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URLUtility.PROFILE+"?phone="+string_mobile_number, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("profile_response"+response);
                JSONArray jsonArray= null;
                try {
                    jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        textView_full_Name.setText(jsonObject.getString("full_name"));
                        textView_fName.setText(jsonObject.getString("father_name"));
                        textView_mName.setText(jsonObject.getString("mother_name"));
                        textView_mNumber.setText(jsonObject.getString("phone"));
                        textView_dob.setText(jsonObject.getString("dob"));
                        textView_address.setText(jsonObject.getString("permanent_address"));
                        textView_gender.setText(jsonObject.getString("gender"));
                        textView_rName.setText(jsonObject.getString("ref_name"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Profile_error:"+error.networkResponse);
                Toast.makeText(ProfileActivity.this,"Sorry! Server not found!!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<>();
                params.put("phone",string_mobile_number);
                System.out.println("params:"+params);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest,"profile");
    }
}
