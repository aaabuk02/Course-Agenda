package edu.aaabuk02.courselogger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeadlineAdapter extends RecyclerView.Adapter {

    private ArrayList<DeadlineInfoContact> deadlineData;
    private View.OnClickListener myOnItemClickListener;
    private Context parentContext;
    private boolean isDeleting;



    public class DeadlineViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewListDeadlineInfo;
        public TextView textViewListDeadlineDate;
        public Button deleteButton;


        public DeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewListDeadlineInfo = itemView.findViewById(R.id.textViewListDeadlineInfo);
            textViewListDeadlineDate = itemView.findViewById(R.id.textViewListDeadlineDate);
            deleteButton = itemView.findViewById(R.id.buttonDeleteDeadline);
        }
        public TextView getTextViewListDeadlineInfo() {return textViewListDeadlineInfo;}
        public TextView getTextViewListDeadlineDate() {return textViewListDeadlineDate;}
        public Button getDeleteButton() {
            return deleteButton;
        }
    }


    public DeadlineAdapter(ArrayList<DeadlineInfoContact> arrayList, Context context)
    {
        deadlineData = arrayList;
        parentContext = context;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deadline_list_item, parent, false);
        return new DeadlineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DeadlineViewHolder dvh = (DeadlineAdapter.DeadlineViewHolder) holder;
        dvh.getTextViewListDeadlineDate().setText(deadlineData.get(position).getDeadlineDate());
        dvh.getTextViewListDeadlineInfo().setText(deadlineData.get(position).getDeadlineInfo());

        if(isDeleting){
            dvh.getDeleteButton().setVisibility(View.VISIBLE);
            dvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
        else{
            dvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return deadlineData.size();
    }

    private void deleteItem(int position) {
        DeadlineInfoContact deadlineInfoContact = deadlineData.get(position);
        CourseDataSource cs = new CourseDataSource(parentContext);
        try{
            cs.open();
            boolean didDelete = cs.deleteDeadlineInfoContact(deadlineInfoContact.getDeadLineContactInfoID());
            cs.close();
            if(didDelete){
                deadlineData.remove(position);
                notifyDataSetChanged();
            }
            else{
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e){
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }
    public void setDelete(boolean b){
        isDeleting = b;
    }

}
