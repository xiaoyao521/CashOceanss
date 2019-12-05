package com.jike.cashocean.net;

public interface URL {
    //基础
    String BASEURL = "http://api.cashmall24.com/"; //正式环境
    //    String BASEURL = "http://api.cashmarktonline.com/"; //正式环境
    //首页
    String APP_BANNER = "?service=App.Index.Banner";//首页广告图
    String APP_HOME_LIST = "?service=App.Index.Lists";//首页列表接口
    String APP_MESSAGE = "?service=App.Ad.Adwheel";//首页跑马灯效果消息
    //认证 保存用户认证信息
    String SAVE_IDENTITY_INFO = "?service=App.User.SaveUserAuthInfo";//保存用户认证信息
    String GET_IDENTITY_INFO = "?service=App.User.GetUserInfo";//保存用户认证信息
    String GET_OSS_TOKEN = "?service=App.Token.Get_ali_oss";//保存用户认证信息
    String GET_ID_TYPE = "?service=App.IdType.GetList";//获取


    String REGISTER = "?service=App.Login.Register";//用户注册
    String CHECK_USER_EXIST = "?service=App.Login.Check_user_register";//检测用户是否存在
    String LOGIN_USER = "?service=App.Login.Login";//用户登录接口
    String FORGET_SEND_CODE = "?service=App.Msg.Send_forget_password_code";//发送短信获取忘记密码验证码
    String RESET_PASSWORD = "?service=App.Login.Forget_password";//重新设置密码
    String REGISTER_SMS_CODE = "?service=App.Msg.Send_register_code";//发送短信注册验证码
    //My
    String MY_APP_LIST = "?service=App.User.AppList";//我的app列表
    String MY_APP_CLICK = "?service=App.Apps.Clickapp";//点击APP列表
    //上传用户app安装列表
    String UPLOAD_USER_APP_LIST = "?service=App.User.SaveUserPackageList";

    String CHECK_PROTOCL = "https://yndc.oss-ap-southeast-5.aliyuncs.com/docs/privacyprotocol" +
            ".html";//注册协议
}

