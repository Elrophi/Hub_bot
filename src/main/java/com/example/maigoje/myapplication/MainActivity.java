package com.example.maigoje.myapplication;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.example.maigoje.myapplication.Adapter.Custom_Adapter;
import com.example.maigoje.myapplication.Helper.HttpDataHandler;
import com.example.maigoje.myapplication.models.chatModel;
import com.example.maigoje.myapplication.models.simsimiModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    EditText edittext;
    List<chatModel> list_chat =new ArrayList<>();
    FloatingActionButton btn_send_mesage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.list_of_message);
        edittext = (EditText) findViewById(R.id.user_message);
        btn_send_mesage = (FloatingActionButton) findViewById(R.id.fab);

    btn_send_mesage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String text = edittext.getText().toString();
            chatModel model = new  chatModel(text,true);
            list_chat.add(model);
            new SimsimAPI().execute(list_chat);

            edittext.setText("");
        }
    });
    }

    private class SimsimAPI extends AsyncTask<List<chatModel>, Void, String > {
        String stream =null;
        List<chatModel> models;
        String text = edittext.getText().toString();
        @Override
        protected String doInBackground(List<chatModel>... lists) {
           String url = String.format("https://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s",getString(R.string.simsimi_api),text);
           models = lists[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHttpData(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            simsimiModel response = gson.fromJson(s, simsimiModel.class);

            chatModel chatmodel = new chatModel(response.getResponse(),false);
            models.add(chatmodel);
            Custom_Adapter adapter = new Custom_Adapter(models, getApplicationContext());
            listview.setAdapter(adapter);
        }
    }
}
