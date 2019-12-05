package com.jike.cashocean.net;

public interface Key {
    String VERSION = "version";
    String SIGN = "sign";
    String PHONE = "phone";
    String AUTHETICATION_CODE = "captcha_code";
    String REGISTER_TYPE = "register_type";
    String ANDROID_ID = "android_id";
    String ADVERTISING_ID = "advertising_id";
    String PASSWORD = "password";
    String TOKEN = "token";
    String TYPE = "type";
    String IDENTITY_NAME = "real_name";
    String ID_TYPE = "idnumber_type";
    String ID_NUM = "idnumber";
    String ID_IMAGE = "idnumber_image";
    String FACE_IMAGE = "face_image";
    String IS_AUTHENTICAITON = "is_authenticaiton";//是否认证

    String PATH_ID_CARD_PICTURE = "path_id_card_picture";//id card 图片路径
    String PATH_ID_CARD_PICTURE_SHOW = "path_id_card_picture_show";//id card 展示图片路径
    String PATH_FACE_PHOTO_PICTURE = "identity_photo_face_file_name";//身份认证中人脸照片存储位置
    String PATH_FACE_PHOTO_PICTURE_SHOW = "identity_photo_face_file_name_show";//身份认证中人脸照片预览照片存储位置
    String TEMP_PHOTO_PATH = "temp_photo_path";//照片临时目录

    //多语言
    String LANGUAGE_KEY = "language_key";
    String IMEI = "imei";
    String LOGIN_PHONE = "login_phone";
    String LOCAL_PHONE = "local_phone";
    //Facebook事件检测
    String START_APP = "start_app";//启动
    String LOGIN = "login";//登录
    String REGISTER = "register";//注册
    String SUBMIT_INFO = "submit_info";//提交资料

}
