package com.betterda.shopping.http;

import com.betterda.shopping.javabean.Comment;
import com.betterda.shopping.javabean.BaseCallModel;
import com.betterda.shopping.javabean.ShopDetail;
import com.betterda.shopping.javabean.UserInfo;
import com.betterda.shopping.javabean.OrderAll;
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
    Observable<BaseCallModel<UserInfo>> getLoginThree(@Field("account") String account, @Field("type") String type);

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
     * 商品类型接口
     *
     * @return
     */
    @GET(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getShopTypeList();


    /**
     * 商品详情接口
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<ShopDetail>> getShopDetail(@Field("productId") String productId);


    /**
     * 添加商品评论
     *
     * @param account
     * @param token
     * @param dataJson
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> addComment(@Field("account") String account,
                                                 @Field("token") String token,
                                                 @Field("dataJson") String dataJson
                                              );

    /**
     * 获取商品评论列表
     *
     * @param productId 商品id
     * @param pageNo    页数
     * @param pageSize  个数
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<Comment>>> getComment(@Field("productId") String productId,
                                                  @Field("pageNo") String pageNo,
                                                  @Field("pageSize") String pageSize
    );

    /**
     * 生成订单
     *
     * @param account
     * @param token
     * @param dataJson
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> addOrder(@Field("account") String account,
                                               @Field("token") String token,
                                               @Field("dataJson") String dataJson
    );

    /**
     * 获取订单列表
     *
     * @param account
     * @param token
     * @param orderStatus 订单状态
     * @param pageNo
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<OrderAll>> getOrder(@Field("account") String account,
                                                 @Field("token") String token,
                                                 @Field("orderStatus") String orderStatus,
                                                 @Field("pageNo") String pageNo,
                                                 @Field("pageSize") String pageSize
    );

    /**
     * 获取订单详情
     *
     * @param account
     * @param token
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<OrderAll>> getOrderDetail(@Field("account") String account,
                                                       @Field("token") String token,
                                                       @Field("orderId") String orderId
    );

    /**
     * 取消订单
     *
     * @param account
     * @param token
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> cancelOrder(@Field("account") String account,
                                                  @Field("token") String token,
                                                  @Field("orderId") String orderId
    );

    /**
     * 立即收货
     *
     * @param account
     * @param token
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getGoods(@Field("account") String account,
                                               @Field("token") String token,
                                               @Field("orderId") String orderId
    );

    /**
     * 立即付款
     *
     * @param account
     * @param token
     * @param orderId
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> pay(@Field("account") String account,
                                          @Field("token") String token,
                                          @Field("orderId") String orderId
    );

    /**
     * 获取钱包
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getWallet(@Field("account") String account,
                                                @Field("token") String token
    );

    /**
     * 获取钱包明细
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getWalletMingXi(@Field("account") String account,
                                                      @Field("token") String token,
                                                      @Field("wateType") String wateType
    );



    /**
     * 获取钱包明细
     *
     * @param account
     * @param token
     * @param money   提现金额
     * @param bankId  银行卡id
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<String>> getCash(@Field("account") String account,
                                              @Field("token") String token,
                                              @Field("money") String money,
                                              @Field("bankId") String bankId
    );


    /**
     * 银行卡删除
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_DEL_BANK)
    Observable<BaseCallModel<String>> getBandDelete(@Field("account") String account,
                                                    @Field("token") String token,
                                                    @Field("jsonData") String id);


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
    @POST(Constants.Url.URL_ADD_BANK)
    Observable<BaseCallModel<String>> getBandAdd(@Field("account") String account,
                                                 @Field("token") String token,
                                                 @Field("cardName") String truename,
                                                 @Field("identityCard") String identitycard,
                                                 @Field("bank") String bank,
                                                 @Field("cardNum") String cardnum,
                                                 @Field("number") String number
    );

    /**
     * 银行卡列表
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_GET_BANK)
    Observable<BaseCallModel<List<BankCard>>> getBandGet(@Field("account") String account,
                                                         @Field("token") String token
    );

    /**
     * 获取二维码
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getErweima(@Field("account") String account,
                                                         @Field("token") String token
    );

    /**
     * 获取消息列表
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getMeassage(@Field("account") String account,
                                                          @Field("token") String token,
                                                          @Field("pageNo") String pageNo,

                                                          @Field("pageSize") String pageSize
    );

    /**
     * 搜索
     *
     * @param account
     * @param token
     * @param searchKey 搜索关键字
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getSearch(@Field("account") String account,
                                                        @Field("token") String token,
                                                        @Field("searchKey") String searchKey
    );

    /**
     * 热门搜索
     *
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getRemenSearchList();


    /**
     * 添加搜索
     *
     * @param account
     * @param token
     * @param key     搜索关键字
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> addSearch(@Field("account") String account,
                                                        @Field("token") String token,
                                                        @Field("key") String key
    );

    /**
     * 删除搜索记录
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> deleteSearch(@Field("account") String account,
                                                           @Field("token") String token
    );

    /**
     * 删除搜索记录
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getSearchList(@Field("account") String account,
                                                            @Field("token") String token
    );

    /**
     * 添加位置记录
     *
     * @param account
     * @param token
     * @param longitude     经度
     * @param latitude      纬度
     * @param address       省市区
     * @param detailAddress 详细地址
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> addLocation(@Field("account") String account,
                                                          @Field("token") String token,
                                                          @Field("longitude") String longitude,
                                                          @Field("latitude") String latitude,
                                                          @Field("address") String address,
                                                          @Field("detailAddress") String detailAddress
    );

    /**
     * 获取附近商家
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getShop(@Field("longitude") String longitude,
                                                      @Field("latitude") String latitude
    );

    /**
     * 添加配送地址
     *
     * @param account
     * @param token
     * @param consigneeName 收货人姓名
     * @param mobilePhone   收货人手机号码
     * @param address       省 市 区
     * @param detailAddress 详细地址
     * @param isDefault     是否默认
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_ADD_ADDRESS)
    Observable<BaseCallModel<String>> addAddress(@Field("account") String account,
                                                         @Field("token") String token,
                                                         @Field("consigneeName") String consigneeName,
                                                         @Field("mobilePhone") String mobilePhone,
                                                         @Field("address") String address,
                                                         @Field("detailAddress") String detailAddress,
                                                         @Field("isDefault") String isDefault

    );

    /**
     * 修改配送地址
     *
     * @param account
     * @param token
     * @param consigneeName 收货人姓名
     * @param mobilePhone   收货人手机号码
     * @param address       省 市 区
     * @param detailAddress 详细地址
     * @param isDefault     是否默认
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_UPDATE_ADDRESS)
    Observable<BaseCallModel<String>> editAddress(@Field("account") String account,
                                                          @Field("token") String token,
                                                          @Field("consigneeName") String consigneeName,
                                                          @Field("mobilePhone") String mobilePhone,
                                                          @Field("address") String address,
                                                          @Field("detailAddress") String detailAddress,
                                                          @Field("isDefault") String isDefault,
                                                            @Field("addressId") String addressId

    );


    /**
     * 修改配送地址
     *
     * @param account
     * @param token
     * @param addressId 地址 id
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> deleteAddress(@Field("account") String account,
                                                            @Field("token") String token,
                                                            @Field("addressId") String addressId
    );

    /**
     * 获取配送地址
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getAddress(@Field("account") String account,
                                                         @Field("token") String token
    );

    /**
     * 获取推荐的人数和返现金额
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getTuijianmenber(@Field("account") String account,
                                                               @Field("token") String token
    );

    /**
     * 获取推荐返现的交易明细
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getTuijianMingxi(@Field("account") String account,
                                                               @Field("token") String token
    );

    /**
     * 添加购物车
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> addBus(@Field("account") String account,
                                                               @Field("token") String token,
                                                               @Field("productId") String productId,
                                                               @Field("totalCount") String totalCount
    );
    /**
     * 更新购物车
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> updateBus(@Field("account") String account,
                                                               @Field("token") String token,
                                                               @Field("productId") String productId,
                                                               @Field("totalCount") String totalCount
    );

    /**
     * 获取购物车
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> getBus(@Field("account") String account,
                                                               @Field("token") String token
    );
    /**
     * 删除购物车
     *
     * @param account
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.Url.URL_LUNBO)
    Observable<BaseCallModel<List<BankCard>>> deleteBus(@Field("account") String account,
                                                               @Field("token") String token,
                                                               @Field("shopId") String shopId
    );


}
