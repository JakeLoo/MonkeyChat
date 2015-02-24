package org.jakelcode.monkeychat;

import android.content.Context;
import android.util.Log;
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

    private final String END_POINT = "http://api.bebo.com/render_2?hashtag_id=boom&self_id=shaanpuri";


    private Context mContext;

    public ApiAccess(Context c ) {
        // since it's a fixed user.
        mContext = c;
    }

    public void renderHashtag(ImageView target, String hashtag) {
        //hashtag is not necessary now...
        Picasso.with(mContext).load(END_POINT).fit().centerCrop().into(target, new Callback() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Succeed.");
            }

            @Override
            public void onError() {
                Log.d(TAG, "Error");
            }
        });
    }
}
