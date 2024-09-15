package com.example.aichatbot;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import Adapter.UserAdapter;
import Model.User;

public class test extends AppCompatActivity {

    private RecyclerView chatView;
    private UserAdapter uAdapter;
    private ImageView sendButton;
    private EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        sendButton = findViewById(R.id.sendButton);
        chatView = findViewById(R.id.chatLayout);

        uAdapter = new UserAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        chatView.setLayoutManager(layoutManager);

        uAdapter.setData(getList());
        chatView.setAdapter(uAdapter);

    }
    private List<User> getList()
    {
        List<User> list = new ArrayList<>();
        list.add(new User("vclll"));
        return list;
    }
}
