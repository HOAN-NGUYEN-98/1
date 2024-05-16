package com.test.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;
import com.test.models.LoginBody;
import com.test.models.LoginResp;
import com.test.models.LogoutResp;
import com.test.models.RegisterBody;
import com.test.models.RegisterResp;
import com.test.models.TypeBookRespone;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy//MM//dd")
            .create();

    ApiService apiService=new Retrofit.Builder()
            .baseUrl("https://testdemo-c6ux.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("auth/login")
    Call<LoginResp> login(@Body LoginBody loginBody);

    @POST("auth/register")
    Call<RegisterResp> register(@Body RegisterBody registerBody);

    @POST("auth/logout")
    Call<LogoutResp> logout();

    @POST("type")
    Call<TypeBookRespone> postType(@Body TypeBookRespone typeBookRespone);

    @GET("bill")
    Call<List<Bill>> getBill();

    @GET("detailbill/{IdBill}")
    Call<BillDetailRespone> detailBill(@Path("IdBill") String IdBill);

    @DELETE("detailbill/{IdBillDetail}")
    Call<BillDetailRespone> deleteDetailBill(@Path("IdBillDetail") String IdBillDetail);

    @GET("type")
    Call<List<TypeBookRespone>> getTypeBook();

}
