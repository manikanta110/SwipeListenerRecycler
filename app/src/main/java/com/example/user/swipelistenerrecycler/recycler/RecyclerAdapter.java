package com.example.user.swipelistenerrecycler.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.swipelistenerrecycler.R;
import com.example.user.swipelistenerrecycler.model.Result;
import com.example.user.swipelistenerrecycler.waste.Item;

import java.util.List;

/**
 * Created by user on 22-Jan-18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>  {
    Context context;
    List<Result> resultList;

    public RecyclerAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.name.setText(resultList.get(position).getName());
        holder.desc.setText(resultList.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

   public  void dismiss(int pos){

        resultList.remove(pos);
        notifyItemRemoved(pos);


      }
    public void restoreItem(Result result, int position) {
        resultList.add(position, result);
        // notify item added by position
        notifyItemInserted(position);
    }





    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,desc;
       public LinearLayout linearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txtname);
            desc = (TextView)itemView.findViewById(R.id.desc);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear);
        }
    }
}
