package com.mahageetamusic.network;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.util.Log;

import com.mahageetamusic.utils.Utils;

import org.apache.http.ConnectionClosedException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kishore on 8/27/2015.
 */
public class HttpConnSetting {
    private static HttpConnSetting httpConnSetting;
    private String TAG = HttpConnSetting.class.getSimpleName();
    String line;
    private HttpConnSetting(){}

    public static synchronized HttpConnSetting getInstance(Context mContext){
        if(httpConnSetting==null){
            httpConnSetting = new HttpConnSetting();
        }
        return httpConnSetting;
    }

    public void getHttpResponse(String url,JSONObject jsonObject){}

    public void getHttpResponse(String url,JSONArray jsonArray){}

    public boolean isNetworkAvailable(Context mContext) {

        ConnectivityManager connManage = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connManage.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting() == true
                || connManage.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting() == true) {
            return true;
        } else {
            return false;
        }
    }

    public String getCategories()
    {
        String url = Utils.URL+ "getwbs_categories.php";
        JSONObject json = new JSONObject();
        return getHttpConnection(url, json, true);
    }

    private String getHttpConnection(String url, JSONObject json,
                                     boolean wrapinArray) {

        Log.d(TAG, "getHttpConnection");
        Log.d(TAG, "url............ " + url);
        Log.d(TAG, "Json.toString() :" + json.toString());

        StringBuffer sb2 = new StringBuffer("");
        String output = "";
        try {

            BufferedReader bufferreader = null;

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (!wrapinArray)
            {	nameValuePairs.add(new BasicNameValuePair("json", json
                    .toString()));
            }else
            {	nameValuePairs.add(new BasicNameValuePair("json", "["
                    + json.toString() + "]"));
            }

            for (int i = 0; i < nameValuePairs.size(); i++) {
                Log.i("----------", ""+nameValuePairs.get(i));
            }

            int timeoutConnection = 10000;
            int timeoutSocket = 10000;
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

            DefaultHttpClient client = new DefaultHttpClient(httpParameters);

            HttpPost httppost = new HttpPost(url);

            //String authorization = preferences.getString(AUTHERIZATION, "");
            httppost.addHeader("Authorization", "");
            Log.d(TAG, "Executing By ... : " + "");

            UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(
                    nameValuePairs, HTTP.UTF_8);
            httppost.setEntity(p_entity);

            Log.d(TAG, "httppost : " + httppost.toString());

            HttpResponse response = client.execute(httppost);

            InputStream inputstream = response.getEntity().getContent();

            bufferreader = new BufferedReader(
                    new InputStreamReader(inputstream));

            if (inputstream != null) {

                bufferreader = new BufferedReader(new InputStreamReader(
                        inputstream));

                while ((line = bufferreader.readLine()) != null) {
                    sb2.append(line);

                }
                Log.d(TAG, "sb2.toString()" + sb2.toString());

            } else {
                sb2 = null;
            }
            output = sb2.toString();

        }

        catch (UnknownHostException e) {
            Log.e(TAG, "UnknownHostException ... ");
            e.printStackTrace();
            output = "network_error";
        } catch (ConnectionClosedException e) {
            Log.e(TAG, "ConnectionClosedException ... ");
            output = "network_error";
        } catch (ConnectTimeoutException e) {
            Log.e(TAG, "ConnectTimeoutException ... ");
            e.printStackTrace();
            output = "network_error";
        } catch (SocketTimeoutException e) {
            Log.e(TAG, "SocketTimeoutException ... " + e);
            e.printStackTrace();
            output = "network_error";
        } catch (SocketException e) {
            Log.e(TAG, "SocketException ... " + e);
            e.printStackTrace();
            output = "network_error";
        } catch (ClientProtocolException e) {
            Log.e(TAG, "ClientProtocolException ... " + e);
            output = "network_error";
        } catch (IOException e) {
            Log.e(TAG, "IOException ... " + e);
            output = "network_error";
        } catch (Exception h) {
            Log.e(TAG, "Exception ... " + h.getClass().getSimpleName());
            output = "network_error";
        }
        Log.v("Result: ",output);
        return output;
    }

    public String getAlbums(int category_id)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("category_id", String.valueOf(category_id));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getHttpConnection(Utils.URL+ "getwbs_albums.php", object, true);
    }

    public String getAlbumsongs(int album_id)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("album_id", String.valueOf(album_id));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return getHttpConnection(Utils.URL+ "getwbs_albumsongs.php", object, true);
    }

    public Drawable getImageFromServer(String aImageURL)
    {
        URL url = null;
        URLConnection urlC = null;
        InputStream is = null;
        try {

            url = new URL(aImageURL);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            url = uri.toURL();
            Log.d("image_url",String.valueOf(url));
            Log.i("Image Query", ""+url.getProtocol()+" "+url.getUserInfo()+" "+url.getHost()+" "+url.getPort()+" "+url.getPath()+" "+url.getQuery()+" "+url.getRef());
            urlC = url.openConnection();
            is = urlC.getInputStream();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Drawable.createFromStream(is, aImageURL);

    }

    public String RegisterUser(JSONObject jsonObject)
    {
        return getHttpConnection(Utils.URL+ "getwbs_signupuser.php", jsonObject, true);
    }

    public String signInUser(JSONObject jsonObject)
    {
        return getHttpConnection(Utils.URL+ "getwbs_signinuser.php", jsonObject, true);
    }

    public String PlaceOrder(JSONObject orderJSONOject)
    {
        return getHttpConnection(Utils.URL+"getwbs_placeorders.php", orderJSONOject, true);
    }

    public String updatePaymentStatus(JSONObject jsonObject) {
        // TODO Auto-generated method stub
        return getHttpConnection(Utils.URL+"getwbs_paymentstatus.php", jsonObject, true);
    }

    public String setRingTone(String provider_code, String mobileNumber,
                              int ringtoneID) {

        String output = null;
        StringBuffer sb2 = new StringBuffer("");
        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(
                Utils.DOMAIN_NAME+"ringtone-sms/");

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("frm", "RINGTONE"));
        nameValuePair.add(new BasicNameValuePair("ringtone_id",""+ringtoneID));
        nameValuePair.add(new BasicNameValuePair("service_provider",provider_code));
        nameValuePair.add(new BasicNameValuePair("mobile", mobileNumber));

        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            BufferedReader bufferreader = null;
            InputStream inputstream = response.getEntity().getContent();

            bufferreader = new BufferedReader(
                    new InputStreamReader(inputstream));

            if (inputstream != null) {

                bufferreader = new BufferedReader(new InputStreamReader(
                        inputstream));

                while ((line = bufferreader.readLine()) != null) {
                    sb2.append(line);

                }
                Log.d(TAG, "sb2.toString()" + sb2.toString());

            } else {
                sb2 = null;
            }
            output = sb2.toString();
            // writing response to log
            Log.d("Http Response:", response.toString());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }
        return output;
    }

    public String ServiceRequest(JSONObject jsonObject)
    {
        return getHttpConnection(Utils.URL+"getwbs_servicerequest.php", jsonObject, true);
    }

    public String ContactRequest(JSONObject jsonObject)
    {
        return getHttpConnection(Utils.URL+"getwbs_contactrequest.php", jsonObject, true);
    }

    public String RequestPassword(JSONObject jsonObject)
    {
        return getHttpConnection(Utils.URL+"getwbs_forgotpwd.php", jsonObject, true);
    }

    public String UpdateUserProfile(JSONObject registerUserObject) {
        // TODO Auto-generated method stub
        return getHttpConnection(Utils.URL+ "getwbs_updateuser.php", registerUserObject, true);
    }

    public String SendEmail(JSONObject jsonObject)
    {
        return getHttpConnection(Utils.URL+"getwbs_sendemail.php", jsonObject, true);
    }

}
