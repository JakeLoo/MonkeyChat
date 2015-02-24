package org.jakelcode.monkeychat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = MainActivity.class.getName();

    @InjectView(R.id.chat_recycle_view) RecyclerView mRecyclerView;
    @InjectView(R.id.chat_text_edit) EditText mTextEdit;
    @InjectView(R.id.chat_button_send) Button mSendButton;
    @InjectView(R.id.chat_preview_hashtag_image) ImageView mPreviewImage;

    private ChatAdapter mAdapter;

    private final static int USER_ID = 0;
    private final static int OTHER_USER_ID = 1;
    private String renderedHashtag;

    private ApiAccess mApiAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Inject views.
        ButterKnife.inject(this);

        List<ChatModel> datas = new ArrayList<>();
        mAdapter = new ChatAdapter(getApplicationContext(), datas);

        mApiAccess = new ApiAccess(getApplicationContext());
        setupRecyclerView(mAdapter);
    }

    private void setupRecyclerView(ChatAdapter adapter) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

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

        if (chatMessage.length() == 0) return;
        mTextEdit.setText("");
        mPreviewImage.setVisibility(View.INVISIBLE);

        ChatModel model = new ChatModel(USER_ID, chatMessage);
        ChatModel mockModel = new ChatModel(OTHER_USER_ID, "Mock response; Lorem ipsum.");


        mAdapter.addChat(model);
        mAdapter.addChat(mockModel);

        mTextEdit.requestFocus();

    }

    // only detect the first hashtag
    @OnTextChanged(R.id.chat_text_edit)
    public void onChatMessageChange() {
        String msg = mTextEdit.getText().toString();

        if (msg == null || msg.length() == 0) return;

        int hashtagIndex = msg.indexOf("#");

        // oops no hashtag
        if (hashtagIndex == -1) return;

        String hashtagSubstring = msg.substring(hashtagIndex);
        Log.d(TAG, hashtagSubstring);

        int finishHashtagIndex = msg.indexOf(" ", hashtagIndex);

        // almost there!!! not finishing hashtagging yet
        String hashtagString;

        if (finishHashtagIndex == -1) { // on typing hashtag
            finishHashtagIndex = msg.substring(hashtagIndex).length();
            hashtagString = msg.substring(hashtagIndex, hashtagIndex + finishHashtagIndex);
        } else { // after indexing the ' ',
            hashtagString = msg.substring(hashtagIndex, finishHashtagIndex);
        }

        Log.d(TAG, "Succeed : " + hashtagString);

        renderHashtag(hashtagString, mPreviewImage);
    }

    private void renderHashtag(String hashtag, ImageView iv) {
        if (hashtag.equals(renderedHashtag)) return;

        mApiAccess.renderHashtag(hashtag, iv, !hashtag.equals("#boom"));

        renderedHashtag = hashtag;
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
