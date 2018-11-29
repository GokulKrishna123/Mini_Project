package com.pda.pradjna.heart;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultPage extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        textView=(TextView)findViewById(R.id.textView);


        SharedPreferences sharedPreferences=getSharedPreferences("otp_details", Context.MODE_PRIVATE);

       // username =sharedPreferences.getString("userid","00");
        String output = sharedPreferences.getString("otp","Person Does not have Heart Disease ");

        if (output.equals("good")){
            textView.setText("This Person Does not a Heart Disease ");
        }else if(output.equals("average")){
            textView.setText("This Person Have Heart Disease ");
        }else{
            textView.setText("This Person Have Heart Disease ");
        }




    }
}
