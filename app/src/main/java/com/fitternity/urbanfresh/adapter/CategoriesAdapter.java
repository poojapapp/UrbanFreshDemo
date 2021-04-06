package com.fitternity.urbanfresh.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fitternity.urbanfresh.R;
import com.fitternity.urbanfresh.model.Categorydatum;
import com.fitternity.urbanfresh.model.Component;
import com.fitternity.urbanfresh.sharedpreferences.SharedPreferencesManager;
import com.fitternity.urbanfresh.utilities.KEY;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.fitternity.urbanfresh.interfaces.InterfaceConstants.Url.IMG_URL;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>
{
    List<Categorydatum> categorydata = new ArrayList<Categorydatum>(  );
    List<Component> components;
    private Context mContext;
    private SharedPreferencesManager sharedPreferencesManager;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    Categorydatum getCategoriesDatum;
    Component getComponent;

    public CategoriesAdapter(List<Component> components,List<Categorydatum> categorydata, Context mContext) {
        this.categorydata = categorydata;
        this.components = components;
        this.mContext = mContext;
        sharedPreferencesManager= new SharedPreferencesManager();
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate( R.layout.categories_list, parent, false);
        CategoriesAdapter.ViewHolder viewHolder = new CategoriesAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        getComponent = components.get( position );

        for (int i = 0; i < components.size(); i++) {
            if(components.get( i ).getCategorydata()!=null) {
                holder.text_categories.setText( components.get( i ).getCategorydata().iterator().next().getCategoryName() );
                Glide.with( mContext ).load( IMG_URL + components.get( i ).getCategorydata().iterator().next().getCategoryPicture() ).into( holder.img_categories );
                Log.i( "CategoryData", String.valueOf( components.get( i ).getCategorydata().iterator().next().getCategoryDescription() ) );
            }
        }


    }


    @Override
    public int getItemCount() {
        return components.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_categories;
        ImageView img_categories;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            text_categories = itemView.findViewById( R.id.text_categories );
            img_categories = itemView.findViewById( R.id.img_categories );
        }
    }
}
