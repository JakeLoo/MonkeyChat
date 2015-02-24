package org.jakelcode.monkeychat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {
    @InjectView(R.id.chat_recycle_view) RecyclerView mRecyclerView;
    @InjectView(R.id.chat_text_edit) EditText mTextEdit;
    @InjectView(R.id.chat_button_send) Button mSendButton;

    private ChatAdapter mAdapter;

    private final static int USER_ID = 0;
    private final static int OTHER_USER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Inject views.
        ButterKnife.inject(this);

        List<ChatModel> datas = new ArrayList<>();
        mAdapter = new ChatAdapter(datas);

        setupRecyclerView(mAdapter);
    }

    private void setupRecyclerView(ChatAdapter adapter) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplication());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @OnClick(R.id.chat_button_send)
    public void sendMessage() {
        String chatMessage = mTextEdit.getText().toString();

        ChatModel model = new ChatModel(USER_ID, chatMessage);

        mTextEdit.setText("");
        mTextEdit.requestFocus();

        mAdapter.addChat(model);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
