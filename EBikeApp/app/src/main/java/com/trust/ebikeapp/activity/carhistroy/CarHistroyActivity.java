package com.trust.ebikeapp.activity.carhistroy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.trust.ebikeapp.R;

public class CarHistroyActivity extends AppCompatActivity {
    private Context context = CarHistroyActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_histroy);

    }

    public void chooseTime(View v){
        startActivity(new Intent(context,ChooseTimeActivity.class));
    }


}
