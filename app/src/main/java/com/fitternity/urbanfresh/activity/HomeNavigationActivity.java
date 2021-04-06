package com.fitternity.urbanfresh.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fitternity.urbanfresh.R;
import com.fitternity.urbanfresh.adapter.CategoriesAdapter;
import com.fitternity.urbanfresh.model.AdsBanner;
import com.fitternity.urbanfresh.model.Categorydatum;
import com.fitternity.urbanfresh.model.Component;
import com.fitternity.urbanfresh.model.UrbanFreashResponse;
import com.fitternity.urbanfresh.model.UrbanFreshRequest;
import com.fitternity.urbanfresh.retrofit.RetroClientInstance;
import com.fitternity.urbanfresh.retrofit.RetrofitCallback;
import com.fitternity.urbanfresh.retrofit.apiInterfaces.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;

import static com.fitternity.urbanfresh.interfaces.InterfaceConstants.Url.IMG_URL;

public class HomeNavigationActivity extends BaseActivity {

    Toolbar toolbar_actionbar;
    ImageView img_banner_image,img_Adsbanner_image;
    RecyclerView rec_categories;
    private CategoriesAdapter categoriesAdapter;
    List<Component> components;
    List<Categorydatum> categorydata;
    List<AdsBanner> adsBanners;
    String category_id;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home_navigation );
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        init();
        getUrbanData(category_id);
        setUpRecyclerviewCategories();
       // getComponentData();
    }


    private void setUpRecyclerviewCategories() {
        rec_categories = (RecyclerView) findViewById( R.id.rec_categories );
        categorydata = new ArrayList<>(  );
        components = new ArrayList<>();
        adsBanners = new ArrayList<>(  );
        categoriesAdapter= new CategoriesAdapter(components,categorydata,mContext);
        rec_categories.setAdapter(categoriesAdapter);
        rec_categories.setHasFixedSize(true);
        rec_categories.setLayoutManager(new GridLayoutManager(this, 3));
     //   LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
       // rec_categories.setLayoutManager(layoutManager);
    }

    public void init(){
        toolbar_actionbar = (Toolbar) findViewById( R.id.toolbar_actionbar );
        img_banner_image = (ImageView) findViewById( R.id.img_banner_image );
        img_Adsbanner_image = (ImageView) findViewById( R.id.img_Adsbanner_image );
    }
    @SuppressLint("LongLogTag")
    public void getUrbanData(String category_id) {
        ApiInterface apiInterface = RetroClientInstance.getRetrofitInstance(mContext).create(ApiInterface.class);
        Call<UrbanFreashResponse> getUserInformationResponseCall = apiInterface.getUrbanFreshData(getUserGetDetailsByIdRequest(category_id) );
        getUserInformationResponseCall.enqueue( new RetrofitCallback<UrbanFreashResponse>(mActivity,true) {
            @Override
            public void onSuccess(UrbanFreashResponse response) {
                if (response.getSuccess() == true) {
                    Log.i( "Response", response.getComponents().toString() );
                    components.addAll( response.getComponents() );
                    categoriesAdapter.notifyDataSetChanged();
                    categoriesAdapter.notifyItemRangeInserted( 0, response.getComponents().size() );
                    rec_categories.scrollToPosition( 0 );
                    for (int i = 0; i < components.size(); i++) {
                        if(components.get( i ).getAdsBanner()!=null) {
                            Glide.with( mContext ).load( IMG_URL + components.get( i ).getAdsBanner().iterator().next().getBannerImage()).into( img_Adsbanner_image );
                        }
                    }
                    for (int i = 0; i < components.listIterator().next().getStaticBanner().size(); i++) {
                        // Log.i("ResponseUrl",IMG_URL+response.getComponents().get( i ).getStaticBanner().get( i ).getBannerImage());
                        Glide.with( mContext ).load( IMG_URL + response.getComponents().get( i ).getStaticBanner().get( i ).getBannerImage() ).into( img_banner_image );
                    }
                }
            }
        });
    }
    private void updateUserDetails(Component components) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.isMemoryCacheable();
    }
    private UrbanFreshRequest getUserGetDetailsByIdRequest(String category_id) {
        UrbanFreshRequest request = new UrbanFreshRequest();
        request.setCategoryId("2");
        return request;
    }

}