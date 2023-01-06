package com.example.c196_nvrazo.SchoolScheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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
                    Intent intent= new Intent(context, TermInfo.class);
                    intent.putExtra("TermId", current.getTermID());
                    intent.putExtra("TermName", current.getTermName());
                    intent.putExtra("TermStartDate", current.getTermStartDate());
                    intent.putExtra("TermEndDate", current.getTermEndDate());
                    context.startActivity(intent);

                }

            });
        }

    }
    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item, parent, false);

        return new TermViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if(mTerms!=null){
            Term current =mTerms.get(position);
            String name=current.getTermName();
            String start=current.getTermStartDate();
            String end=current.getTermEndDate();
            holder.termItemView.setText(end);
            holder.termItemView.setText(name + " " + start+  " " + end);


        }else{
            holder.termItemView.setText("No term name");
        }

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
