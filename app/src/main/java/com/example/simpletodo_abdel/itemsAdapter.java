package com.example.simpletodo_abdel;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;

public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.viewHolder> {

    public interface OnLongClickListener{
        void OnItemLongClicked (int position);
    }

    public interface  OnClickListener{
        void onItemClicked(int position);
    }


    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public itemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener){
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new viewHolder(todoView);
    }

    @Override
    // binding data to particular view holder
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        // Grab item at position
        String item = items.get(position);
        
        //bind the item in the specified view Holder
        
        holder.bind(item);
    }

    @Override

    // Tells the recycler view how many items in the list
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access to views that represent each row of the list
    class viewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);

        }
// update the view inside the view holder with the given data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });

            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed
                    longClickListener.OnItemLongClicked(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
