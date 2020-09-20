package com.example.editdelete;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<user> mExampleList;
    private Context context;
    CardView cardView;

    public ExampleAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<user> getmExampleList() {
        return mExampleList;
    }

    public void setmExampleList(ArrayList<user> mExampleList) {
        this.mExampleList = mExampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, final int position) {
        final user user = getmExampleList().get(position);
        holder.mTextView1.setText(mExampleList.get(position).getName());
        holder.mTextView2.setText(mExampleList.get(position).getAge() + " years old");
        holder.mTextView3.setText(mExampleList.get(position).getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserDetail.class);
                intent.putExtra("mExampleList", mExampleList.get(position));
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.nama_cv);
            mTextView2 = itemView.findViewById(R.id.age_cv);
            mTextView3 = itemView.findViewById(R.id.address_cv);

            cardView = itemView.findViewById(R.id.cardView);
        }

//            this.onNoteListener = onNoteListener;

//            itemView.setOnClickListener(this);


//        @Override
//        public void onClick(View view) {
//            onNoteListener.OnNoteClick(getAdapterPosition());
//        }
//    }

//    public interface OnNoteListener{
//        void OnNoteClick (int position);
//    }

//    public ExampleAdapter(ArrayList<user> exampleList, OnNoteListener onNoteListener){
//        mExampleList = exampleList;
////        this.mOnNoteListener = onNoteListener;
//    }

//        @NonNull
//        @Override
//        public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
//            ExampleViewHolder evh = new ExampleViewHolder(v);
//            return evh;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ExampleViewHolder holder, final int position) {
//            final user user = getmExampleList().get(position);
//            holder.mTextView1.setText(user.getName());
//            holder.mTextView2.setText(user.getAge() + " years old");
//            holder.mTextView3.setText(user.getAddress());
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, UserDetail.class);
//                    intent.putExtra("mExampleList", mExampleList.get(position));
//                    intent.putExtra("position", position);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mExampleList.size();
//        }
    }
}
