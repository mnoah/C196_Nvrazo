package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196_nvrazo.R;
import com.example.c196_nvrazo.SchoolScheduler.Entities.Term;

import java.util.List;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;

        private TermViewHolder(View itemview) {
            super(itemview);
            termItemView = itemview.findViewById(R.id.textView2);
            itemview.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View view){
                    int position=getAdapterPosition();
                    final Term current=mTerms.get(position);
                    Intent intent= new Intent(context, TermAdapter.class)

                }

            });
        }

    }
    private List<Term> mTerms;
    private final Context  contex;

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }

    public void setTerms(List<Term> term){
        mTerms=term;
        notifyDataSetChanged();

    }
}
