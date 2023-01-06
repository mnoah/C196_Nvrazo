package com.example.c196_nvrazo.SchoolScheduler.UI;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Database.Repository;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TermList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        RecyclerView recyclerView = findViewById(R.id.TermListRecyclerView);
        final TermAdapter termAdapter = new TermAdapter( this);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository= new Repository(getApplication());
        List<Term> allTerm= repository.getmAllTerms();
        termAdapter.setTerms(allTerm);
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