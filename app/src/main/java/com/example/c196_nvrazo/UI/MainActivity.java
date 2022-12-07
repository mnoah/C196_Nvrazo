package com.example.c196_nvrazo.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.c196_nvrazo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.EnterButtonMainActivity);
        button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Intent intent=new Intent(MainActivity.this, TermList.class);
                    startActivity(intent);
        }

        });

    }
}