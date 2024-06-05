package com.test.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.models.Bill;
import com.test.models.BillDetailRespone;
import com.test.models.Book;
import com.test.models.BookRespone;
import com.test.models.Detail;
import com.test.models.LoginBody;
import com.test.models.LoginResp;
import com.test.models.LogoutResp;
import com.test.models.RegisterBody;
import com.test.models.RegisterResp;
import com.test.models.TopBook;
import com.test.models.TotalMoney;
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
import retrofit2.http.PUT;
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

// api typeBook
    @POST("type")
    Call<TypeBookRespone> postType(@Body TypeBookRespone typeBookRespone);

    @GET("type")
    Call<List<TypeBookRespone>> getTypeBook();

    @DELETE("type/{IdType}")
    Call<TypeBookRespone> deleteType(@Path("IdType") String IdType);

    @PUT("type/{IdType}")
    Call<TypeBookRespone> updateType(@Body TypeBookRespone typeBookRespone,@Path("IdType") String IdType);

// api book
    @GET("book")
    Call<List<Book>> getListBook();

    @POST("book")
    Call<BookRespone> addBook(@Body BookRespone bookRespone);

    @GET("book/{IdBook}")
    Call<BookRespone> detailBookByID(@Path("IdBook") String IdBook);

    @DELETE("book/{IdBook}")
    Call<BookRespone> deleteBookByID(@Path("IdBook") String IdBook);

    @PUT("book/{IdBook}")
    Call<BookRespone> updateBook(@Body BookRespone bookRespone,@Path("IdBook") String IdBook);

//    @GET("book/type/{IdType}")
//    Call<List<BookRespone>> detailBookByIdType(@Path("IdType") String IdType);

// api bill
    @GET("bill/total")
    Call<TotalMoney> totalMoney();
    @GET("bill/top")
    Call<List<TopBook>> topBook();
    @GET("bill")
    Call<List<Bill>> getBill();
    @GET("bill/{id}")
    Call<Bill> getDetailBill(@Path("id") String id);
    @PUT("bill/{IdBill}")
    Call<Bill> updateBill(@Body Bill bill,@Path("IdBill") String IdBill);

    @POST("bill")
    Call<Bill> postBill(@Body Bill bill);

//    @POST("bill")
//    Call<List<Detail>> postDetailBill(@Body List<Detail> detail);

// api detail bill


    @GET("detailbill/{IdBill}")
    Call<BillDetailRespone> detailBill(@Path("IdBill") String IdBill);

    @POST("detailbill")
    Call<BillDetailRespone> postDetailBill(@Body BillDetailRespone billDetailRespone);

    @DELETE("detailbill/{IdBillDetail}")
    Call<BillDetailRespone> deleteDetailBill(@Path("IdBillDetail") String IdBillDetail);

    @GET("detailbill/bill/{IdBill}")
    Call<List<BillDetailRespone>> detailBillByIdBill(@Path("IdBill") String IdBill);

    @GET("detailbill/book/{IdBook}")
    Call<List<BillDetailRespone>> detailBillByIdBook(@Path("IdBook") String IdBook);

    @PUT("detailbill/{IdDetailBill}")
    Call<BillDetailRespone> updateDetailBill(@Body BillDetailRespone billDetailRespone,@Path("IdDetailBill") String IdDetailBill);

}
