package com.example.c196_nvrazo.SchoolScheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196_nvrazo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        RecyclerView recyclerView=findViewById(R.id.TermListRecyclerView);
        FloatingActionButton button = findViewById(R.id.TermListAddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermList.this, TermInfo.class);
                startActivity(intent);
            }

        });
    }
}