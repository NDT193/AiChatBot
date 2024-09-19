package com.example.aichatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.ai.client.generativeai.java.ChatFutures;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;

import java.util.ArrayList;
import java.util.List;

import Adapter.itemAdapter;
import ApiService.ResponeApi;
import Model.itemModel;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private ImageView sendButton;
    private RecyclerView chatView;
    private ChatFutures chatModel;

    private itemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputText = findViewById(R.id.inputText);
        sendButton = findViewById(R.id.sendButton);
        chatView = findViewById(R.id.chatLayout);
        itemAdapter= new itemAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        chatView.setLayoutManager(layoutManager);

        chatModel = getChatModel();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = inputText.getText().toString();
                inputText.setText("");

                chatbody( query,true);
                GeminiResp.getRespone(chatModel, query, new ResponeApi() {
                    @Override
                    public void onResponse(String respone) {
                        chatbody( respone, false);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                    }
                });

            }
        });
    }

    private ChatFutures getChatModel() {
        GeminiResp model = new GeminiResp();
        GenerativeModelFutures modelFutures = model.getModel();

        return modelFutures.startChat();
    }

    private void chatbody( String query, boolean isUserMessage) {
        itemModel messageModel;

        if (isUserMessage) {
            messageModel = new itemModel(query, true);
        } else {
            messageModel = new itemModel(query, false);
        }
        if (itemAdapter.imlist == null) {
            itemAdapter.imlist = new ArrayList<>();
        }
        List<itemModel> currentList = itemAdapter.imlist;
        currentList.add(messageModel);
        itemAdapter.setData(currentList);
        chatView.setAdapter(itemAdapter);

        chatView.smoothScrollToPosition(itemAdapter.getItemCount() - 1);

    }
}