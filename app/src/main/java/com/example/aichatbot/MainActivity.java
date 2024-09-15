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

import Adapter.UserAdapter;
import Adapter.botItemAdapter;
import ApiService.ResponeApi;
import Model.User;
import Model.botModel;

public class MainActivity extends AppCompatActivity {
    private EditText inputText;
    private ImageView sendButton;
    private RecyclerView chatView;
    private ChatFutures chatModel;
    private botItemAdapter botAdapter;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botAdapter = new botItemAdapter(this);
        userAdapter = new UserAdapter(this);

        inputText = findViewById(R.id.inputText);
        sendButton = findViewById(R.id.sendButton);
        chatView = findViewById(R.id.chatLayout);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        chatView.setLayoutManager(layoutManager);

        chatModel = getChatModel();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = inputText.getText().toString();
                inputText.setText("");

                chatbody( query, userAdapter);
                GeminiResp.getRespone(chatModel, query, new ResponeApi() {
                    @Override
                    public void onResponse(String respone) {
                        chatbody( respone, botAdapter);
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

    private void chatbody( String query, RecyclerView.Adapter adapter) {
        if (adapter instanceof botItemAdapter) {
            ((botItemAdapter) adapter).addItem(new botModel(query));
            chatView.setAdapter(adapter);
        } else if (adapter instanceof UserAdapter) {
            ((UserAdapter) adapter).addUserItem(new User(query));
            chatView.setAdapter(adapter);
        }

        adapter.notifyDataSetChanged();
    }
}