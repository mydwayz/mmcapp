package com.mahageetamusic.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Kishore on 8/27/2015.
 */
public class Utils {
    public static final int LOGIN_1 = 1;
    public static final int LOGIN_2 = 2;
    public static final String LOGIN_TYPE = "login_type";
    public static final int PAYMENT_STATUS = 3;

    //public static String DOMAIN_NAME = "http://beta.mahageetamusic.com/";
    public static String DOMAIN_NAME = "http://www.mahageetamusic.com/";//http://mahageetamusic.com/
    public static String URL = DOMAIN_NAME + "webservices/";
    public static String CATEGORY_IMAGEPATH = DOMAIN_NAME + "data/category/";
    public static String ALBUM_IMAGEPATH = DOMAIN_NAME + "data/albums/";
    public static String SAMPLE_PATH = DOMAIN_NAME + "data/dump/";
    public static String SAMPLE_PATH1 = DOMAIN_NAME + "songs/";
    /*public static String DOMAIN_NAME = "http://marcinfotech.com";
    public static String URL =  DOMAIN_NAME+"/music/public_html/webservices/";
    public static String CATEGORY_IMAGEPATH = DOMAIN_NAME+"/music/public_html/data/category/";
    public static String ALBUM_IMAGEPATH = DOMAIN_NAME+"/music/public_html/data/albums/";
    public static String SAMPLE_PATH = DOMAIN_NAME+"/music/public_html/data/dump/";*/


    public static final int REQUEST_LOGIN = 0;
    public static final int REQUEST_FORGOTPASSWORD = 1;
    public static final int REQUEST_SIGNUP = 2;
    public static final int REQUEST_CATEOGIES = 3;
    public static final int REQUEST_PER_CATEGORY = 4;
    public static final int REQUEST_ALBUM_SONGS =5;
    public static final int REQUEST_SERVICE_REQUEST =6;
    public enum PAYMENTSTATUS {
        FAIL(0), SUCCESS(1);

        int value;

        private PAYMENTSTATUS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum CART_LIST {
        NONE(0), ALBUM(1), TRACK(2), RINGTONE(3);

        int value;

        private CART_LIST(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    static AlertDialog alertDialog;

    public static void CopyStream(InputStream is, OutputStream os) {
        final int buffer_size = 1024;
        try {
            byte[] bytes = new byte[buffer_size];
            for (; ; ) {
                int count = is.read(bytes, 0, buffer_size);
                if (count == -1)
                    break;
                os.write(bytes, 0, count);
            }
        } catch (Exception ex) {
        }
    }

    public static boolean emailValidation(String emailString) {
        if (emailString != null) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailString)
                    .matches();
        } else {
            return false;
        }
    }

    public static boolean phoneValidation(String phoneString) {
        if (phoneString != null) {
            return android.util.Patterns.PHONE.matcher(phoneString).matches();
        } else {
            return false;
        }
    }

    public static void DialogFactory(Context context, String message, String positiveText, DialogInterface.OnClickListener onClickListener, String negativeText, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        if (positiveText != null) {
            builder.setPositiveButton(positiveText, onClickListener);
        }
        if (negativeText != null) {
            builder.setNegativeButton(negativeText, onClickListener2);
        }
        alertDialog = builder.create();
        alertDialog.show();
    }

    public static void CreateHashKey(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo("com.mahageetamusic", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("MY KEY HASH:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

}
