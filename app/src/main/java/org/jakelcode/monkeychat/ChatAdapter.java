package org.jakelcode.monkeychat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Description...
 *
 * @author Pin Khe "Jake" Loo (24 February, 2015)
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {
    private List<ChatModel> mChatModel;
    private Context mContext;

    FrameLayout.LayoutParams rightLayoutParams =
            new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT, Gravity.END);

    public ChatAdapter(Context c, List<ChatModel> model) {
        mContext = c;
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

        if (mChatModel.get(i).getUserId() == 0) { // User itself
            chatHolder.mChatView.setBackgroundColor(mContext.getResources().getColor(R.color.material_deep_teal_500));

            chatHolder.mChatView.setLayoutParams(rightLayoutParams);
        } else {
            chatHolder.mChatView.setBackgroundColor(Color.parseColor("#BBDEFB"));
        }

        chatHolder.mChatView.setText(model.getMessage());
    }

    public void addChat(ChatModel m) {
        mChatModel.add(0, m);

        //Anim
        notifyItemInserted(0);
    }
}
