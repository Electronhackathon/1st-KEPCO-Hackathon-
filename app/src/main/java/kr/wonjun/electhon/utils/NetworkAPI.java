package kr.wonjun.electhon.utils;

import java.util.ArrayList;

import kr.wonjun.electhon.models.History;
import kr.wonjun.electhon.models.Map;
import kr.wonjun.electhon.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkAPI {

    @GET("/auth/fb/authenticate")
    Call<User> loginByFacebook(@Query("access_token") String token);

    @POST("/auth/login")
    @FormUrlEncoded
    Call<User> loginLocal(
            @Field("email") String email,
            @Field("pw") String password);

    @POST("/auth/local/authenticate")
    @FormUrlEncoded
    Call<User> authenticateByToken(
            @Field("token") String token);

    @POST("/auth/register")
    @FormUrlEncoded
    Call<ResponseBody> registerLocal(
            @Field("email") String email,
            @Field("name") String name,
            @Field("pw") String password);

    @POST("/self/updateNickname")
    @FormUrlEncoded
    Call<User> updateNickname(
            @Field("token") String token,
            @Field("nickname") String nickname);

    @GET("/pay/paymentList")
    Call<ArrayList<History>> getHistory(
            @Query("token") String token);

    @POST("/pay/addCard")
    @FormUrlEncoded
    Call<ResponseBody> addCard(
            @Field("token") String token,
            @Field("cardNum") String cardNum,
            @Field("cardPassword") String cardPassword,
            @Field("cardBirthday") String cardBirthday,
            @Field("cardExpiry") String cardExpiry);

    @GET("/location/payphone")
    Call<ArrayList<Map>> getMaps();

}
