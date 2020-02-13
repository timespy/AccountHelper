package com.example.accounthelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BillAdapter extends ArrayAdapter<Bill> {
    private int resourceId;

    public BillAdapter(Context context, int textViewResourceId, List<Bill> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Bill bill = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.type = (TextView)view.findViewById(R.id.bill_type);
            viewHolder.descrip = (TextView)view.findViewById(R.id.bill_descrip);
            viewHolder.price = (TextView)view.findViewById(R.id.bill_price);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        assert bill != null;
        viewHolder.type.setText(bill.getType());
        viewHolder.descrip.setText(String.valueOf(bill.getDescrip()));
        viewHolder.price.setText(String.valueOf(bill.getPrice()));
        return view;
    }

    class ViewHolder{
        TextView type;
        TextView descrip;
        TextView price;
    }
}
