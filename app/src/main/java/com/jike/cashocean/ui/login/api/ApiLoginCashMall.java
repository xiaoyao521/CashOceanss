package com.jike.cashocean.ui.login.api;



import com.jike.cashocean.model.CheckUserExist;
import com.jike.cashocean.model.ForgetPWCodeEntity;
import com.jike.cashocean.model.LoginEntity;
import com.jike.cashocean.model.RegisterUserEntity;
import com.jike.cashocean.model.ResetPasswordEntity;
import com.jike.cashocean.model.RigesterCodeEntity;
import com.jike.cashocean.net.URL;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Yey on 2018/4/24.
 */

public interface ApiLoginCashMall {
    /**
     * 检查用户是否存在
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(URL.CHECK_USER_EXIST)
    Observable<CheckUserExist> checkUserExist(@FieldMap Map<String, String> map);

    /**
     * 注册
     *
     * @param map
     * @return
     */

    @FormUrlEncoded
    @POST(URL.REGISTER)
    Observable<RegisterUserEntity> registerUser(@FieldMap Map<String, String> map);


    /**
     * 登录
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(URL.LOGIN_USER)
    Observable<LoginEntity> loginUser(@FieldMap Map<String, String> map);

    /**
     * @return 发送短信获取忘记密码的验证码
     */
    @FormUrlEncoded
    @POST(URL.FORGET_SEND_CODE)
    Observable<ForgetPWCodeEntity> getForgetSMSCode(@FieldMap Map<String, String> map);


    /**
     * 重置密码
     * @param ap
     * @return
     */
    @FormUrlEncoded
    @POST(URL.RESET_PASSWORD)
    Observable<ResetPasswordEntity> resetPassword(@FieldMap Map<String, String> map);


    /**
     * 短信注册验证码
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(URL.REGISTER_SMS_CODE)
    Observable<RigesterCodeEntity> registerSmsCode(@FieldMap Map<String, String> map);

}
