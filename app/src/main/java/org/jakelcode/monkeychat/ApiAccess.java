package org.jakelcode.monkeychat;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Description...
 *
 * @author Pin Khe "Jake" Loo (24 February, 2015)
 */
public class ApiAccess {
    private static final String TAG = ApiAccess.class.getName();

//    private final String USER = "shaanpuri";
//    private final String API_HASHTAG = "hashtag_id";
//    private final String API_USER = "self_id";

    private final String END_POINT = "http://api.bebo.com/render_2?hashtag_id=boom&self_id=shaanpuri&height=48&width=48";
    private final String ERROR_END_POINT = "http://localhost/";

    private Context mContext;

    public ApiAccess(Context c ) {
        // since it's a fixed user.
        mContext = c;
    }

    public void renderHashtag(String hashtag, final ImageView target, boolean forceError) {
        //hashtag is not necessary now...
        Picasso.with(mContext).load(forceError ? Uri.parse(ERROR_END_POINT) : Uri.parse(END_POINT)).fit()
                .into(target, new Callback() {
            @Override
            public void onSuccess() {
                if (target.getVisibility() != View.VISIBLE) target.setVisibility(View.VISIBLE);
                Log.d(TAG, "Succeed.");
            }

            @Override
            public void onError() {
                if (target.getVisibility() != View.INVISIBLE) target.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Error");
            }
        });
    }
}
