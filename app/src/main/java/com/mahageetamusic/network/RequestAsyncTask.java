package com.mahageetamusic.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mahageetamusic.beans.AlbumBean;
import com.mahageetamusic.beans.CategoryBean;
import com.mahageetamusic.beans.ResponseObject;
import com.mahageetamusic.beans.SongsAndRingtones;
import com.mahageetamusic.utils.UserSessionManager;
import com.mahageetamusic.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kishore on 8/27/2015.
 */
public class RequestAsyncTask extends AsyncTask<Object,Object,Object> {

    private ResponseListener responseListener;
    private Context mContext;
    private int requestService;
    HttpConnSetting httpConnSetting;
    JSONObject jsonObject=null;
    private Object response=null;
    String notFound = "{\"Result\":[]}";
    String failure = "{\"Message\":\"An error has occurred.\"}";
    public RequestAsyncTask(Context context,ResponseListener listener,int requestService){
        this.mContext = context;
        this.responseListener = listener;
        this.requestService=requestService;
        httpConnSetting = HttpConnSetting.getInstance(mContext);
        jsonObject = null;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        switch (requestService){
            case Utils.REQUEST_LOGIN:
                try{
                    jsonObject = new JSONObject();
                    jsonObject.put("emailid",objects[0].toString());
                    jsonObject.put("password",objects[1].toString());
                    response=parseLogin(httpConnSetting.signInUser(jsonObject));}catch (Exception e){}
                break;
            case Utils.REQUEST_SIGNUP:
                try {
                    jsonObject = (JSONObject) objects[0];
                    response = parseSugnUp(httpConnSetting.RegisterUser(jsonObject));
                } catch (Exception e){}
                break;
            case Utils.REQUEST_FORGOTPASSWORD:
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("emailid",objects[0].toString());
                    response = parseSugnUp(httpConnSetting.RequestPassword(jsonObject));
                } catch (Exception e){}
                break;
            case Utils.REQUEST_CATEOGIES:
                response = parseCategories(httpConnSetting.getCategories());
                break;
            case Utils.REQUEST_PER_CATEGORY:
                response = parseAlbums(httpConnSetting.getAlbums((int) objects[0]));
                break;
            case Utils.REQUEST_SERVICE_REQUEST:
                try {
                    jsonObject = (JSONObject) objects[0];
                    response = parseSugnUp(httpConnSetting.ServiceRequest(jsonObject));
                } catch (Exception e){}
                break;
            case Utils.REQUEST_ALBUM_SONGS:
                response = parseAlbumsView(httpConnSetting.getAlbumsongs((int) objects[0]));
                break;
        }
        return response;
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        responseListener.onResponseListener(requestService, result);
    }

    private Object parseLogin(String jsonObj){
        Log.v("Login Response", jsonObj);
        if(jsonObj!=null){
            try {
                JSONObject loginuserJson = new JSONObject(jsonObj);
                if(loginuserJson.getString("result").equals("Successful"))
                {
                    UserSessionManager userSessionManager = new UserSessionManager(mContext);
                    userSessionManager.createLoginSession(
                            loginuserJson
                                    .getString(UserSessionManager.KEY_USERID),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_FIRSTNAME),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_LASTNAME),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_EMAIL),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_ADDRESS),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_MOBILENUMBER),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_CITY),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_FADDRESS),
                            loginuserJson
                                    .getString(UserSessionManager.KEY_ZIPCODE));
                    return new ResponseObject("Success",userSessionManager.getUserDetails());
                }
                else if(loginuserJson.getString("result").equals("Unsuccessful"))
                {
                    return new ResponseObject("Failure",loginuserJson.getString("message"));
                }
                else if(jsonObj.equals("network_error"))
                {
                    return new ResponseObject("Failure","network_error");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                if(jsonObj.equals("network_error"))
                {
                    return new ResponseObject("Failure","network_error");
                }
            }
        }
        return null;
    }

    private Object parseSugnUp(String signUp) {
        if(signUp!=null){
            try {
                JSONObject loginuserJson = new JSONObject(signUp);
                if(loginuserJson.getString("result").equals("Successful"))
                {
                    return new ResponseObject("Success",loginuserJson.getString("message"));
                } else if(loginuserJson.getString("result").equals("Unsuccessful")) {
                    return new ResponseObject("Failure",loginuserJson.getString("message"));
                } else if(signUp.equals("network_error")) {
                    return new ResponseObject("Failure","network_error");
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                if(signUp.equals("network_error"))
                {
                    return new ResponseObject("Failure","network_error");
                }
            }
        }
        return null;
    }

    private Object parseAlbums(String albums) {
        Log.v("Albums",albums);
        try {
            JSONObject res = new JSONObject(albums);
            if(res.getJSONObject("albums").getString("message").equals("Successful")){
                ArrayList<AlbumBean> data = new ArrayList<AlbumBean>();
                AlbumBean albumBean = null;
                JSONArray jsonArray = res.getJSONObject("albums").optJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.v("Album Number",""+i);
                    albumBean=null;
                    albumBean = new AlbumBean();
                    try {
                        albumBean.setCategoryID(jsonArray.getJSONObject(i).optInt("category_id"));
                        albumBean.setAlbumID(jsonArray.getJSONObject(i).getInt("album_id"));
                        albumBean.setAlbumName(jsonArray.getJSONObject(i).getString("album_name"));
                        albumBean.setDescription(jsonArray.getJSONObject(i).getString("description"));
                        albumBean.setSimage(jsonArray.getJSONObject(i).getString("simage"));
                        //bean.setSicon(httpConn.getImageFromServer(Utils.ALBUM_IMAGEPATH+""+jsonArray.getJSONObject(i).getString("simage")));
                        //bean.setLicon(httpConn.getImageFromServer(Utils.ALBUM_IMAGEPATH+""+jsonArray.getJSONObject(i).getString("limage")));
                        albumBean.setLimage(jsonArray.getJSONObject(i).getString("limage"));
                        albumBean.setRelease_date(jsonArray.getJSONObject(i).getString("release_date"));
                        albumBean.setTracks(jsonArray.getJSONObject(i).getInt("tracks"));
                        albumBean.setNIP(jsonArray.getJSONObject(i).getString("non_india_price"));
                        albumBean.setIP(jsonArray.getJSONObject(i).getString("india_price"));
                        albumBean.setICDP(jsonArray.getJSONObject(i).getString("india_cd_price"));
                        albumBean.setShipping_Charges(jsonArray.getJSONObject(i).getString("shipping_price"));
                        albumBean.setTopAlbum(jsonArray.getJSONObject(i).getInt("top_album"));
                        data.add(albumBean);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return new ResponseObject("Success",data);
            }else{
                return new ResponseObject("Failure","Data Parsing Failed.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseObject("Failure","Network Error!!!");
        }
    }

    private Object parseCategories(String categoryResponse)
    {
        Log.v("Categories",categoryResponse);
        try {
            JSONObject res = new JSONObject(categoryResponse);
            if(res.getJSONObject("categories").getString("message").equals("Successful")){
                ArrayList<CategoryBean> data = new ArrayList<CategoryBean>();
                CategoryBean categoryBean=null;
                JSONArray jsonArray = res.getJSONObject("categories").getJSONArray("results");
                UserSessionManager userSessionManager = new UserSessionManager(mContext);
                userSessionManager.saveShippingCost(res.getJSONObject("categories").getJSONArray("shipping").toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    categoryBean = new CategoryBean();
                    try {
                        categoryBean.setCategoryID(jsonArray.getJSONObject(i).getInt("category_id"));
                        categoryBean.setCategoryName(jsonArray.getJSONObject(i).getString("category_name"));
                        categoryBean.setImage(jsonArray.getJSONObject(i).getString("image"));
                        categoryBean.setDescription(jsonArray.getJSONObject(i).getString("description"));
                        Log.v("", categoryBean.getCategoryName());
                        data.add(categoryBean);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return new ResponseObject("Success",data);
            }else{
                return new ResponseObject("Failure","Data Parsing Failed.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseObject("Failure","Network Error!!!");
        }
    }

    private Object parseAlbumsView(String albumsongs) {
        Log.v("Songs", albumsongs);
        try {
            JSONObject object = new JSONObject(albumsongs);
            if(object.getJSONObject("album_songs").opt("message").equals("Successful")){
                SongsAndRingtones songsAndRingtones = new Gson().fromJson(albumsongs, SongsAndRingtones.class);
                return new ResponseObject("Success",songsAndRingtones);
            }else{
                return new ResponseObject("Failure","Data Parsing Failed.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseObject("Failure","Network Error!!!");
        }
    }
}
