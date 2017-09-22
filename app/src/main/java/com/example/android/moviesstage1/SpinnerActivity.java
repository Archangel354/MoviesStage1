package com.example.android.moviesstage1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String data = parent.getItemAtPosition(position).toString();
        data = "sillyness";
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + data,
                Toast.LENGTH_SHORT).show();
        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
        mIntent.putExtra("PopOrRated", data);
        startActivity(mIntent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
