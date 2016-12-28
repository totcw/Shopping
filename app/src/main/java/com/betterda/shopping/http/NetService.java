package com.betterda.shopping.http;

import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.UserInfo;
import com.betterda.shopping.shouye.model.LunBoTu;
import com.betterda.shopping.sort.model.Shopping;
import com.betterda.shopping.utils.Constants;
import com.betterda.shopping.wallet.model.BankCard;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 封装retrofit请求需要的接口
 * Created by Administrator on 2016/7/29.
 */
public interface NetService {
    /**
     * 注册
     *
     * @param account
     * @param password
     * @param number
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_REGISTER)
    Observable<BaseCallModel<String>> getRegister(@Field("account") String account,
                                                  @Field("password") String password,
                                                  @Field("number") String number);

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LOGIN)
    Observable<BaseCallModel<UserInfo>> getLogin(@Field("account") String account,
                                                 @Field("password") String password);

    /**
     * 三方登录
     *
     * @param account
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LOGIN)
    Observable<BaseCallModel<UserInfo>> getLoginThree(@Field("account") String account,@Field("type") String type);

    /**
     * 忘记密码
     *
     * @param account
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_PWD_UPDATE)
    Observable<BaseCallModel<String>> getPwdUpdate(@Field("account") String account,
                                                   @Field("password") String password);

    /**
     * 获取广告栏
     *
     * @return
     */

    @GET(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<LunBoTu>> getAdvertisement();


    /**
     * 商品列表接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<Shopping>> getShopList(@Field("productType") String productType,
                                                    @Field("sort") String sort,
                                                    @Field("filter") String filter,
                                                    @Field("pageNo") String pageNo,
                                                    @Field("pageSize") String pageSize);

    /**
     * 银行卡删除
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getBandDelete(@Field("account") String account,
                                                    @Field("token") String token,
                                                    @Field("id") String id);


    /**
     * 银行卡添加
     *
     * @param account
     * @param token
     * @param truename     持卡人姓名
     * @param identitycard 身份证
     * @param bank         所属银行
     * @param cardnum      卡号
     * @param number       预留手机号
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getBandAdd(@Field("account") String account,
                                                 @Field("token") String token,
                                                 @Field("cardName") String truename,
                                                 @Field("identityCard") String identitycard,
                                                 @Field("bank") String bank,
                                                 @Field("cardNum") String cardnum,
                                                 @Field("number") String number
    );

    /**
     * 银行卡查询
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getBandGet(@Field("account") String account,
                                                         @Field("token") String token
    );

}
