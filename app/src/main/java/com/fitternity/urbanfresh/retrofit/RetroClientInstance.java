package com.fitternity.urbanfresh.retrofit;

import android.content.Context;

import com.fitternity.urbanfresh.interfaces.InterfaceConstants;
import com.fitternity.urbanfresh.sharedpreferences.SharedPreferencesManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = InterfaceConstants.Url.ApiBaseUrlLocal;

//    static OkHttpClient httpClient = new OkHttpClient().newBuilder().cache(null).build();
//    static Gson gson = new GsonBuilder()
//            .registerTypeAdapter(Object.class, new JsonSerializer<Object>() {
//
//                @Override
//                public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
//                    return context.serialize(src, Object.class);
//                }
//            })
//            .setLenient()
//            .create();


    public static Retrofit getRetrofitInstance(final Context context) {
        final SharedPreferencesManager sharedPreferencesManager=new SharedPreferencesManager();
        if (retrofit == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .method(original.method(),original.body())
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
      //  Log.e("Authorization","Bearer "+sharedPreferencesManager.getStringValueWithContext(context,KEY.Authorization));
        return retrofit;
    }
}

