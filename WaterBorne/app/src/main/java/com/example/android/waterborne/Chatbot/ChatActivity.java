package com.example.android.waterborne.Chatbot;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.waterborne.Adapters.ChatArrayAdapter;
import com.example.android.waterborne.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {
    //private static final String TAG = "ChatActivity";
    ChatArrayAdapter chatArrayAdapter;
    EditText chatText;
    ListView chatList;
    Button sendbtn;
    Button begin;
    CircleImageView mic;
    public String Authorization = "";
    String Recieved = "";
    Context mContext = this;
    int contentview;

    //boolean typing=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startingv2);
        contentview = R.layout.startingv2;
        begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setContentView(R.layout.activity_chat);
                contentview = R.layout.activity_chat;
                chatList = (ListView) findViewById(R.id.chatlist);
                chatText = (EditText) findViewById(R.id.msgtyped);
                mic = findViewById(R.id.cv_mic);
                changeview();
                chatArrayAdapter = new ChatArrayAdapter(mContext, new ArrayList<ChatMessage>(), chatList);
                chatList.setAdapter(chatArrayAdapter);
                chatArrayAdapter.add(new ChatMessage(1, "typing..."));
                //  chatArrayAdapter.getItem(0).setIsMine(1);
                new Welcome(mContext).execute();
            }
        });

    }

    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeview() {
        sendbtn = (Button) findViewById(R.id.send_btn);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String message = chatText.getText().toString();
                ChatMessage chatMessage = new ChatMessage(0, message);

                chatArrayAdapter.add(chatMessage);

                JSONObject jsn = new JSONObject();
                try {
                    jsn.put("message", message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Chat(mContext).execute(Authorization, jsn.toString());
                chatText.setText("");
                chatArrayAdapter.add(new ChatMessage(1, "typing..."));
                chatList.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });
    }

    public void ServerWelcome(JSONObject res) {
        try {
            Recieved = (res.getString("message"));
            Authorization = res.getString("uuid");
            chatArrayAdapter.remove((ChatMessage) chatArrayAdapter.chatList.getItemAtPosition(chatArrayAdapter.getCount() - 1));
            chatArrayAdapter.notifyDataSetChanged();

            ChatMessage chatMessage = new ChatMessage(1, Recieved);
            chatArrayAdapter.add(chatMessage);
            chatList.setSelection(chatArrayAdapter.getCount() - 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //  this method recieves the server response
    public void ServerChat(JSONObject res) {
        try {
            Recieved = (res.getString("message"));
            Log.d("nfndskjfnjkdsn   ", Recieved);
            chatArrayAdapter.remove((ChatMessage) chatArrayAdapter.chatList.getItemAtPosition(chatArrayAdapter.getCount() - 1));
            chatArrayAdapter.notifyDataSetChanged();

            ChatMessage chatMessage = new ChatMessage(1, Recieved);
            chatArrayAdapter.add(chatMessage);
            chatList.setSelection(chatArrayAdapter.getCount() - 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.get(0).equals("mail"))
                        chatText.setText("male");
                    else
                        chatText.setText(result.get(0));
                }
                break;
        }
    }


}

