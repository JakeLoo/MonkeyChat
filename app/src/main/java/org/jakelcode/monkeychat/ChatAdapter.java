package org.jakelcode.monkeychat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Description...
 *
 * @author Pin Khe "Jake" Loo (24 February, 2015)
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
    private List<ChatModel> mChatModel;

    public ChatAdapter(List<ChatModel> model) {
        mChatModel = model;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int i) {
        //Load the layout for each card/entry.
        final View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.view_chat_bubble, parent, false);

        return new ChatHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return mChatModel.size();
    }

    @Override
    public void onBindViewHolder(ChatHolder chatHolder, int i) {
        ChatModel model = mChatModel.get(i);

        chatHolder.mChatView.setText(model.getMessage());
    }
}
