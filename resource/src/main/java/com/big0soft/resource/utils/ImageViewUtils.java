package com.big0soft.resource.utils;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.big0soft.resource.helper.TAGs;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageViewUtils {
    private static final String TAG = "BindingAdapters";
//    @BindingAdapter({"app:goneVisibility"})
//    public static void goneVisibility(View view, Boolean visible) {
//        view.setVisibility(visible ? View.VISIBLE : View.GONE);
//    }
//
//    @BindingAdapter({"app:invisibleVisibility"})
//    public static void invisibleVisibility(View view, Boolean visible) {
//        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
//    }

    @BindingAdapter({"app:enableView"})
    public static void setEnable(View view, Boolean isEnable) {
        view.setEnabled(isEnable);
    }

    @BindingAdapter({"app:src"})
    public static void setSrc(ImageView view, int src) {
        view.setImageResource(src);
//        view.setImageDrawable(src);
    }

    @BindingAdapter("android:goneIfNoSrc")
    public static void goneIfNoSrc(ImageView imageView, boolean ignore) {
        Log.d(TAGs.TAG, "goneIfNoSrc: " + imageView.getDrawable());
        ViewUtils.goneVisibility(imageView, imageView.getDrawable() != null);
    }

    /**
     *
     */
//    @BindingAdapter(value = {"android:iconURL"})
//    @Deprecated
//    public static void iconURL(MaterialButton button, String URL) {
//        if (TextUtils.isEmpty(URL)) return;
//        new Thread(() -> {
//            try {
//                Bitmap bitmap = Picasso.get().load(URL).get();
//                Drawable icon = new BitmapDrawable(button.getResources(), bitmap);
//                new Handler(Looper.getMainLooper()).post(() -> {
//                    button.setIcon(icon);
//                });
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//
//    }
    static class PicassoCallbackImpl implements Callback {

        private final ImageView imageView;

        PicassoCallbackImpl(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onSuccess() {
            Log.i(TAGs.TAG(getClass()), "onSuccess load image: ");
            imageView.animate().setDuration(300).alpha(1f).start();
        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
        }
    }

//    @BindingAdapter(value = {"android:imageURL", "android:imageCallback"}, requireAll = false)
//    public static void setImageURL(ImageView imageURL, String URL, Callback callback) {
//        if (callback == null) {
//            try {
//                setImageCallback(imageURL, URL, new PicassoCallbackImpl());
//            } catch (Exception e) {
//                Log.e(TAGs.TAG, "setImageURL: ", e);
//            }
//            return;
//        }
//        if (TextUtils.isEmpty(URL)) {
//            imageURL.setVisibility(View.GONE);
//            return;
//        }
//        try {
//            setImageCallback(imageURL, URL, callback);
//        } catch (Exception e) {
//            Log.e(TAGs.TAG, "setImageURL: ", e);
//        }
//    }

    @BindingAdapter(value = {"android:imageURL"})
    public static void setImageURL(ImageView imageURL, String URL) {
//        if (TextUtils.isEmpty(URL)) {
//            imageURL.setVisibility(View.GONE);
//            return;
//        }
        try {
            setImageCallback(imageURL, URL, new PicassoCallbackImpl(imageURL));
        } catch (Exception e) {
            Log.e(TAGs.TAG, "setImageURL: ", e);
        }

    }

    private static void setImageCallback(ImageView imageView, String URL, Callback callback) throws Exception {
        imageView.setAlpha(0f);
        Picasso.get().load(URL)
                .fit()
                .noFade().into(imageView, callback);
    }

    //    @BindingAdapter(value = {"android:imageURL", "android:imageCallback"}, requireAll = false)
//    public static void setImageURL(ImageView imageURL, String URL, Callback callback) {
//        if (callback == null) {
//            setImageURL(imageURL, URL);
//            return;
//        }
//        if (TextUtils.isEmpty(URL)) {
//            imageURL.setVisibility(View.GONE);
//            return;
//        }
//        try {
//            imageURL.setAlpha(0f);
//            Picasso.get().load(URL)
//                    .fit()
//                    .noFade().into(imageURL, callback);
//        } catch (Exception e) {
////            imageURL.setImageResource(R.mipmap.ic_icon_app);
//            e.printStackTrace();
//        }
//    }
}
