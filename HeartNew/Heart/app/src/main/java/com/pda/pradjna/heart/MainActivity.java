package com.pda.pradjna.heart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    Button predict;
    EditText age,bp,cholesterol,sugar,ec_result,heart_rate,induced_agina,st_depression,no_vessels;
    private RadioGroup radioGroup;
    RadioButton sex;
    String gender;
    private String sexs;
    String[] thalassemiaarr = { "None", "Normal", "Fixed Defect", "Reversible Defect"};
    String[] chaistpainer = { "Typical Agina", "Atypical Agina", "Non Aginal Pain", "Asymptotic"};
   // String[] ecresulter = { "None", "Normal", "ST Wave Abnormality", "Probable Left Vhypertrophy"};
    String[] sloper = { "None", "Up Slopping", "Flat", "Down Slopping"};
   // private Spinner chest_pain,slopp;
    private String thalassemias,chest_pains,slopes;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.r0);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();

                     sexs=rb.getText().toString();

                }
            }
        });


        Spinner spin = (Spinner) findViewById(R.id.thalassemia);
        spin.setOnItemSelectedListener(this);

        spin.setPrompt("None");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,thalassemiaarr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


        Spinner chest_pain = (Spinner) findViewById(R.id.editText3);
        chest_pain.setOnItemSelectedListener(this);

        chest_pain.setPrompt("Typical Agina");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aachest_pain = new ArrayAdapter(this,android.R.layout.simple_spinner_item,chaistpainer);
        aachest_pain.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        chest_pain.setAdapter(aachest_pain);





        Spinner slopp = (Spinner) findViewById(R.id.editText11);
        slopp.setOnItemSelectedListener(this);

        slopp.setPrompt("None");

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaslop = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sloper);
        aaslop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        slopp.setAdapter(aaslop);








        predict=(Button)findViewById(R.id.b1);
        age=(EditText)findViewById(R.id.editText1);

       // sex= (RadioButton) findViewById(R.id.r1);


      //  chest_pain=(EditText)findViewById(R.id.editText3);
        bp=(EditText)findViewById(R.id.editText4);
        cholesterol=(EditText)findViewById(R.id.editText5);
        sugar=(EditText)findViewById(R.id.editText6);
        ec_result=(EditText)findViewById(R.id.editText7);
        heart_rate=(EditText)findViewById(R.id.editText8);
        induced_agina=(EditText)findViewById(R.id.editText9);
        st_depression=(EditText)findViewById(R.id.editText10);
       // slope=(EditText)findViewById(R.id.editText11);
        no_vessels=(EditText)findViewById(R.id.editText12);
       // thalassemia= (EditText) findViewById(R.id.r0);


        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              onLoad();

            }
        });


    }
    public void onClear(View v) {
        /* Clears all selected radio buttons to default */
        radioGroup.clearCheck();
    }

    public void onSubmit(View v) {
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
    }



    private void onLoad() {



        final String ages=age.getText().toString();
       // String sexs=sex.getText().toString();
       // String chest_pains=chest_pain.getText().toString();
        final String bps=bp.getText().toString();
        final String cholosterols=cholesterol.getText().toString();
        final String sugars=sugar.getText().toString();
        final String ec_results=ec_result.getText().toString();
        final String heart_rates=heart_rate.getText().toString();
        final String induced_aginas=induced_agina.getText().toString();
        final String st_depressions= st_depression.getText().toString();
      //  final String slopes= slope.getText().toString();
        final String no_vesselss=no_vessels.getText().toString();
      //  String thalassemias=thalassemia.getText().toString();



        //first getting the values



        //validating inputs
        if (TextUtils.isEmpty(ages)) {
            age.setError("Please enter your age");
            age.requestFocus();
            return;

            } else if (TextUtils.isEmpty(heart_rates)) {
            heart_rate.setError("Please enter your heart rate");
            heart_rate.requestFocus();
            return;

             }else if (TextUtils.isEmpty(no_vesselss)) {
            no_vessels.setError("Please enter data");
            no_vessels.requestFocus();
            return;

        }else if (TextUtils.isEmpty(st_depressions)) {
            st_depression.setError("Please Enter The Depression");
            st_depression.requestFocus();
            return;

        }/*else if (TextUtils.isEmpty(thalassemias)) {
            thalassemia.setError("Please Enter The Thalassemias");
            thalassemia.requestFocus();
            return;

        }*/else if (TextUtils.isEmpty(induced_aginas)) {
            induced_agina.setError("Please Enter The Induced Aginas");
            induced_agina.requestFocus();
            return;

        }else if (TextUtils.isEmpty(bps)) {
            bp.setError("Please enter your Bp");
            bp.requestFocus();
            return;
        }else {


            //if everything is fine

            String otpurl = "http://www.pradjna.com/candidate_repcalc/";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, otpurl,
                    new Response.Listener<String>() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onResponse(String response) {
                            //progressBar.setVisibility(View.GONE);


                            try {
                                //converting response to json object
                                JSONObject obj = new JSONObject(response);
                               String success = obj.getString("result");
                               String message = obj.getString("message");
                               // userid = obj.getString("userid");


                                String nolonger = "id or password or email not match";

                                if (success.equals("success")) {


                                    SharedPreferences sharedPreferences = getSharedPreferences("otp_details", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("otp", message);
                                    editor.apply();


                                    Intent signup = new Intent(MainActivity.this, ResultPage.class);
                                    startActivity(signup);


                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "NUMBER IS NOT REGISTERED WITH US ", Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast.show();


                                }

                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.v("error checking varriable chjange", String.valueOf(e));

                                Toast toast6 = Toast.makeText(getApplicationContext(), "Check the number", Toast.LENGTH_LONG);
                                toast6.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                                toast6.show();

                                startActivity(getIntent());
                                finish();

                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast toast = Toast.makeText(getApplicationContext(), "Please check the internet connection", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                            startActivity(getIntent());
                            finish();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("age", ages);
                    params.put("sex", sexs);
                    params.put("chest_pain", String.valueOf(2));
                    params.put("bp_low", String.valueOf(80));
                    params.put("bp_high", bps);
                    params.put("cholesterol", cholosterols);
                    params.put("sugar", sugars);
                    params.put("ec_result", ec_results);
                    params.put("heart_rate", heart_rates);
                    params.put("induced_agina",induced_aginas);
                    params.put("st_depression",st_depressions);
                    params.put("slope",slopes);
                    params.put("no_vessels",no_vesselss);
                    params.put("thalassemia",thalassemias);
                    params.put("pred_value", String.valueOf(1));

                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.thalassemia) {





          Toast.makeText(getApplicationContext(),thalassemiaarr[position] , Toast.LENGTH_LONG).show();

            if(thalassemiaarr[position].equals("None")){

                thalassemias= String.valueOf(1);

            }else if (thalassemiaarr[position].equals("Normal")){
                thalassemias= String.valueOf(2);

            }else if (thalassemiaarr[position].equals("Fixed Defect")){
                thalassemias= String.valueOf(3);

            }else if (thalassemiaarr[position].equals("Reversible Defect")){
                thalassemias= String.valueOf(4);

            }
            Toast.makeText(getApplicationContext(),thalassemias , Toast.LENGTH_LONG).show();


        } else if (parent.getId() == R.id.editText3) {



           Toast.makeText(getApplicationContext(),chaistpainer[position] , Toast.LENGTH_LONG).show();

            if(chaistpainer[position].equals("Typical Agina")){

                chest_pains= String.valueOf(1);

            }else if (chaistpainer[position].equals("Atypical Agina")){
                chest_pains= String.valueOf(2);

            }else if (chaistpainer[position].equals("Non Aginal Pain")){
                chest_pains= String.valueOf(3);

            }else if (chaistpainer[position].equals("Asymptotic")){
                chest_pains= String.valueOf(4);

            }
          //  Toast.makeText(getApplicationContext(),chest_pains , Toast.LENGTH_LONG).show();




            //do this
        } else if (parent.getId() == R.id.editText11) {

            Toast.makeText(getApplicationContext(),sloper[position] , Toast.LENGTH_LONG).show();

            if(sloper[position].equals("None")){

                slopes= String.valueOf(1);
            }else if (sloper[position].equals("Up Slopping")){
                slopes= String.valueOf(2);

            }else if (sloper[position].equals("Flat")){
                slopes= String.valueOf(3);

            }else if (sloper[position].equals("Down Slopping")){
                slopes= String.valueOf(4);

            }

          //  Toast.makeText(getApplicationContext(),slopes , Toast.LENGTH_LONG).show();
            //do this

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}