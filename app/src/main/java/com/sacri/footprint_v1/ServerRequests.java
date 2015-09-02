package com.sacri.footprint_v1;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.sacri.footprint_v1.Entities.ImageDetails;
import com.sacri.footprint_v1.Entities.UserDetails;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 *
 */

public class ServerRequests {
    ProgressDialog progressDialog;
    String imgPath, fileName;
    Bitmap bitmap;
    String encodedString;
    private static int RESULT_LOAD_IMG = 1;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://www.footprint.comuv.com/";

    public ServerRequests(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait..");
    }

    public void storeUserDataInBackground(UserDetails userDetails, GetUserCallback getUserCallback){
        progressDialog.show();
        new StoreUserDataAsyncTask(userDetails,getUserCallback).execute();

    }

    public void fetchUserDataInBackground(UserDetails userDetails, GetUserCallback getUserCallback){
        progressDialog.show();
        new FetchUserDataAsyncTask(userDetails,getUserCallback).execute();
    }
//
//    public void storePostDataInBackground(PostDetails postDetails, GetUserCallback getUserCallback){
//        progressDialog.show();
//        new StorePostDataAsyncTask(postDetails,getUserCallback).execute();
//
//    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        UserDetails userDetails;
        GetUserCallback getUserCallback;

        StoreUserDataAsyncTask(UserDetails userDetails, GetUserCallback getUserCallback){
            this.userDetails = userDetails;
            this.getUserCallback = getUserCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i("com.sacri.footprint_v1","doInBackground begins");
            ArrayList<NameValuePair> dataToSend = new ArrayList<NameValuePair>();
            dataToSend.add(new BasicNameValuePair("user_name", userDetails.getName()));
            dataToSend.add(new BasicNameValuePair("user_mobile_number", userDetails.getMobileNumber()));
            dataToSend.add(new BasicNameValuePair("user_email", userDetails.getEmail()));
            dataToSend.add(new BasicNameValuePair("user_gender", userDetails.getGender()));
            dataToSend.add(new BasicNameValuePair("user_city", userDetails.getCity()));
            dataToSend.add(new BasicNameValuePair("user_nationality", userDetails.getNationality()));
            dataToSend.add(new BasicNameValuePair("user_username", userDetails.getUserName()));
            dataToSend.add(new BasicNameValuePair("user_password", userDetails.getPassword()));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(SERVER_ADDRESS + "Register.php");
            try{
                httpPost.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(httpPost);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            getUserCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, UserDetails> {

        UserDetails userDetails;
        GetUserCallback getUserCallback;

        FetchUserDataAsyncTask(UserDetails userDetails, GetUserCallback getUserCallback) {
            this.userDetails = userDetails;
            this.getUserCallback = getUserCallback;
        }

        @Override
        protected UserDetails doInBackground(Void... params) {
            Log.i("com.sacri.footprint_v1","doInBackground begins");
            ArrayList<NameValuePair> dataToSend = new ArrayList<NameValuePair>();
            dataToSend.add(new BasicNameValuePair("user_username", userDetails.getUserName()));
            dataToSend.add(new BasicNameValuePair("user_password", userDetails.getPassword()));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            UserDetails returnedUserDetails = null;
            try{
                httpPost.setEntity(new UrlEncodedFormEntity(dataToSend));

                Log.i("com.sacri.footprint_v1", "httpPost Entity : " + httpPost.getEntity().toString());
                HttpResponse httpResponse = client.execute(httpPost);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                Log.i("com.sacri.footprint_v1", "Result : " + result);
                JSONObject jsonObject = new JSONObject(result);

                if(jsonObject.length() == 0){
                    returnedUserDetails = null;
                    Log.i("com.sacri.footprint_v1", "JSON Object is Null : ");
                }else {
                    String name = jsonObject.getString("user_name");
                    String mobileNumber = jsonObject.getString("user_mobile_number");
                    String email = jsonObject.getString("user_email");
                    String gender = jsonObject.getString("user_gender");
                    String city = jsonObject.getString("user_city");
                    String nationality = jsonObject.getString("user_nationality");
                    String username = jsonObject.getString("user_username");
                    String password = jsonObject.getString("user_password");

                    returnedUserDetails = new UserDetails(name,mobileNumber,email,gender,city,nationality,username,password);


                }


            }catch(Exception e){
                e.printStackTrace();
            }

            return returnedUserDetails;
        }

        @Override
        protected void onPostExecute(UserDetails returnedUserDetails) {
            progressDialog.dismiss();
            getUserCallback.done(returnedUserDetails);
            super.onPostExecute(returnedUserDetails);
        }
    }
//
//    public class StorePostDataAsyncTask extends AsyncTask<Void, Void, Void> {
//        PostDetails postDetails;
//        GetPostCallBack getPostCallBack;
//
//        StorePostDataAsyncTask(PostDetails postDetails, GetPostCallBack getPostCallBack){
//            this.postDetails = postDetails;
//            this.getPostCallBack = getPostCallBack;
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            Log.i("com.sacri.footprint_v1", "doInBackground begins");
//            ArrayList<NameValuePair> dataToSend = new ArrayList<NameValuePair>();
//            dataToSend.add(new BasicNameValuePair("post_text", postDetails.getPostText()));
//            dataToSend.add(new BasicNameValuePair("post_privacy", postDetails.getPrivacy()));
//            dataToSend.add(new BasicNameValuePair("post_image", postDetails.getImage()));
//
//
//        }
//
//        //covert image to String
//        public void encodeImagetoString(final ImageDetails imageDetails) {
//            new AsyncTask<Void, Void, String>() {
//
//                protected void onPreExecute() {
//
//                };
//
//                @Override
//                protected String doInBackground(Void... params) {
//                    BitmapFactory.Options options = null;
//                    options = new BitmapFactory.Options();
//                    options.inSampleSize = 3;
//
//                    bitmap = BitmapFactory.decodeFile(imgPath,
//                            options);
//                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    // Must compress the Image to reduce image size to make upload easy
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
//                    byte[] byte_arr = stream.toByteArray();
//                    // Encode Image to String
//                    encodedString = Base64.encodeToString(byte_arr, 0);
//                    return "";
//                }
//
//                @Override
//                protected void onPostExecute(String msg) {
//                    prgDialog.setMessage("Calling Upload");
//                    // Put converted Image string into Async Http Post param
//                    params.put("image", encodedString);
//                    // Trigger Image upload
//                    triggerImageUpload();
//                }
//            }.execute(null, null, null);
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            progressDialog.dismiss();
//            getUserCallback.done(null);
//            super.onPostExecute(aVoid);
//        }
//    }
}
