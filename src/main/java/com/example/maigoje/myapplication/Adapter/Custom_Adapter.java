package com.example.maigoje.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.maigoje.myapplication.models.chatModel;
import com.github.library.bubbleview.BubbleTextView;
import java.util.List;

import java.util.List;

public class Custom_Adapter extends BaseAdapter {

    private List<chatModel> list_chat_model;
    private Context context;
    private LayoutInflater layoutInflater;

    public Custom_Adapter(List<chatModel> list_chat_model, Context context, LayoutInflater layoutInflater) {
        this.list_chat_model = list_chat_model;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_chat_model.size();
    }

    @Override
    public Object getItem(int i) {
        return list_chat_model.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      View view1 =view;
      if (view1==null){
          if(list_chat_model.get(i).isSend){
              view1 = layoutInflater.inflate(R.layout.list_item_message_send, null);
          }else{
              view1 = layoutInflater.inflate(R.layout.list_item_message_receieve,null);

              BubbleTextView txv = (BubbleTextView)view.findViewById(R.id.txv);
              txv.setText(list_chat_model.get(i).message);
          }

      }
      return view1;
    }
}
