package org.jakelcode.monkeychat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Holder of single chat bubble
 *
 * @author Pin Khe "Jake" Loo (24 February, 2015)
 */
public class ChatHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.view_chat_text) TextView mChatView;

    public ChatHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
