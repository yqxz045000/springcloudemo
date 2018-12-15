package com.cfyj.demo.springcloud.zuul.domain;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum CodeDict {
    SUCCESS("0", "成功"),
    FAILED("1", "失败"),
    EXCEPT("2", "异常"),
	
	
	/*LOGIN_NEED("9","未登录"),
	NOAUTH("10","无权限"),
	NOT_PERMISSION_REQUEST("-1","不允许的操作");*/

    //通用异常
    SYSTEM_ERROR("500", "系统异常"),
    ARGUMENT_VANISH("501", "缺少参数"),
    ARGUMENT_TYPE_MISMATCH("502", "类型错误"),
    ARGUMENT_CONSTRAINT_VIOLATION("503", "参数校验错误"),
    UNKNOWN_ERROR("9999", "未知异常"),

    /***********************************用户异常 4位 1开头 1001-1999****************************************/
    USER_PHONE_REGISTERED("1001", "手机号已注册"),
    USER_PHONE_NO_REGISTER("1002", "手机号未注册"),
    USER_PHONE_ERROR("1003", "手机号错误"),
    USER_RAND_ERROR("1004", "图形验证码错误"),
    USER_RAND_INVALID_ERROR("1005", "图形验证码已失效"),

    IMGYZM_EMPTY_ERROR("1006", "请输入图形验证码"),
    USER_MSG_ERROR("1007", "短信验证码错误"),
    USER_MSG_INVALID_ERROR("1008", "短信验证码失效"),
    USER_FORMAT_PWD_ERROR("1009", "密码格式错误"),
    USER_PASSWORD_ERROR("1010", "手机号或密码错误"),

    USERNAME_EMPTY("1011", "手机号为空"),
    USER_PASSWORD_EMPTY("1012", "密码为空"),
    USER_PASSWORD_NOT_EXIST("1013", "用户密码状态异常"),
    USER_INFO_UPDATE_ERROR("1014", "用户信息更新错误"),
    USER_STATE_FREEZE("1015", "提示：平台监测到您存在违规行为,当前账号已被冻结。\n" +
            "如有异议，可关注微信公众号【趣走】，进行申诉。"),

    IMG_NOT_EXIST("1016", "图片不存在"),
    IMG_COUNT_LIMIT_ERROR("1017", "图片数量限制"),
    IMG_SIZE_LIMIT_ERROR("1018", "图片大小限制"),
    IMG_TYPE_LIMIT_ERROR("1019", "图片格式限制"),
    USER_DSFACCOUNT_NOTCREATE("1020", "您的区块链账户正在创建中，请稍等..."),

    /********************************** app1.5 登录改版 ************************************/
    MOBILE_BIND_OTHER_WECHAT("1030", "该手机号已经绑定其他微信"),
    MOBILE_NO_BIND("1031", "请绑定手机号"),
    WECHAT_NO_BIND("1032", "请绑定微信"),

    WECHAT_BINDED_OTHER_LOGIN("1041", "此微信已经绑定其他账户,您将以微信快登方式进入"),
    SELECT_ACCOUNT_MERGE("1050", "请选择账号合并"),

    /********************************** token异常 4位 2开头 2001-2999 ************************************/
    LOGIN_TOKEN_LOGOUT("2001", "已注销"), //token已注销
    LOGIN_TOKEN_OUTOFDATE("2002", "登录过期，请重新登录"), //token登录过期
    LOGIN_TOKEN_PWD_MODIF("2003", "token密码已修改"),
    LOGIN_TOKEN_ACCOUNT_FORBIDDEN("2004", "账户禁用"),
//    LOGIN_TOKEN_NOT_FOUND("2005", "未查到相关token记录"),
//    LOGIN_TOKEN_ERROR("2006", "查询token信息出错"),
    LOGIN_TOKEN_NOT_IDENTIFY("2007", "验证不通过"),
    LOGIN_UN_LOGIN("2008", "未登录"),
    LOGIN_TOKEN_NEED_REGET("2009", "token需要重新获取"),
    USER_UPDATE_LIMIT_ERROR("2010", "用户信息更新时间限制"),


    /********************************** 绑定相关 4位 3开头 3001-3999 ************************************/
    THIRD_LOGIN_FAIL("3001", "绑定第三方账号失败"),
    PHONE_UNBIND_FAIL("3002", "第三方账号绑定成功，请绑定手机号"),
    BIND_THIRD_TYPE_FAIL("3003", "第三方绑定类型错误"),
    THIRD_HAD_BIND_FAIL("3004", "第三方账号已经被绑定"),

    /********************************** 账户相关 4位 4开头 4001-4999 ************************************/
    BALANCE_NOT_ENOUGH("4001", "账户余额不足"),
    To_BE_MEMBER_ERROR("4002", "开通会员失败"),

    QB_NOT_ENOUGH("4003", "趣币余额不足"),
    REBATE_NOT_ENOUGH("4004", "购物返利不足"),

    /********************************** 账户相关 4位 5开头 5001-5999 ************************************/
    INVITATION_CODE_ERROR("5001", "邀请码错误"),
    INVITATION_MOBILE_INVITED_ERROR("5002", "该手机号已经被邀请"),
    INVITATION_MOBILE_REGISTERED_ERROR("5003", "该手机号已经注册"),  
    
    
	
	/****************步行数据相关状态码  6001-6999*******************/
	WALK_STATISTICS_NOHIS("6001","日周月步行统计无历史数据"),
    WALK_ACCEPT_UPPER("6002", "当日接收步数达到上限"),

	/******************活动相关的*************************************/
	/**对对碰 7001-7100*/
	ACT_WALKCOMPARE_GETWALK("7001","当前无对拼步数，请前往首页同步步数"),
	ACT_WALKCOMPARE_TOMAXWALK("7002","老铁今日步数遥遥领先，恳请大佬高抬贵手，今日先放过我们这些小萌新吧~"),
	ACT_NEWBIE_REDBAG("7101","该用户已领取过新手红包"),


    /****************小程序相关状态码  9001-9999*******************/
    REQUIRE_BIND_MOBILE("9001","需要绑定手机号"),
    UNGET_UNIONID("9002","未取得unionid"),
	MINI_GET_AUTHINFO("9003","获取用户的个人授权信息"),

    BIND_MOBILE_OVER("9004","该手机号已经被绑定"),

	
	/****************远程授权服务状态码  10000-10100*******************/
	AUTHSERVICE_REQUESTFAIL("10000","授权服务远程调用失败"),
	AUTHSERVICE_PARAMERROR("10001","授权服务远程调用成功,参数错误"),
	AUTHSERVICE_NEEDAUTHORIZE_FAIL("10002","授权服务远程调用成功,资源需授权，不满足授权条件"),
	AUTHSERVICE_NEEDAUTHORIZE_SUCCESS("10003","授权服务远程调用成功,资源需授权，满足授权条件"),
	AUTHSERVICE_NEEDAUTHORIZE_NO("10004","授权服务远程调用成功,资源不需授权");
	
	
    private String code;
    private String msg;

    CodeDict(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
