package com.example.teddy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {
    private ArrayList<Attributes> mAttributeList;
    private OnItemClickListener mListener;
    private ArrayList<Attributes> attributesListFiltered;
    private Context context;
    private AttributesAdapterListener listener;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewNaam;
        public TextView mTextViewAdres;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextViewNaam = itemView.findViewById(R.id.textView);
            mTextViewAdres = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup  parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,
                false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Attributes currentItem = mAttributeList.get(position);

        holder.mTextViewNaam.setText(currentItem.getNaam_label());
        holder.mTextViewAdres.setText(currentItem.getDistrict());



    }

    @Override
    public int getItemCount() {
        return mAttributeList.size();
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    attributesListFiltered = mAttributeList;
                }else{
                    ArrayList<Attributes> filteredList = new ArrayList<>();
                    for (Attributes row : mAttributeList){
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNaam_label().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    attributesListFiltered = filteredList;
                    Log.d("FILTER adapter", "performFiltering: "+attributesListFiltered.get(0));
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = attributesListFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                attributesListFiltered = (ArrayList<Attributes>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.mListener = listener;
    }


    public ExampleAdapter(ArrayList<Attributes> attributeList){

        mAttributeList = attributeList;
    }




    public interface AttributesAdapterListener {
        void onContactSelected(Attributes contact);
    }

    public void AttributesAdapter(Context context, ArrayList<Attributes> attributeList, AttributesAdapterListener listener){
        this.context = context;
        this.listener = listener;
        this.mAttributeList = attributeList;
        this.attributesListFiltered = attributeList;
    }




}
