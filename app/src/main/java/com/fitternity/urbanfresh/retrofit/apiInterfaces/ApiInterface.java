package com.fitternity.urbanfresh.retrofit.apiInterfaces;
import com.fitternity.urbanfresh.model.Categorydatum;
import com.fitternity.urbanfresh.model.Component;
import com.fitternity.urbanfresh.model.UrbanFreashResponse;
import com.fitternity.urbanfresh.model.UrbanFreshRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
  @POST("home_test_section")
  public Call<UrbanFreashResponse> getUrbanFreshData(@Body UrbanFreshRequest requestDTO);

  @POST("home_test_section")
  public Call<Categorydatum> getUrbanFreshData();
}
