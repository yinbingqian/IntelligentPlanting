package com.lnpdit.woofarm.http;

/**
 * webService请求接口
 * 
 * @author huanyu 类名称：ISoapService 创建时间:2014-11-4 下午7:08:50
 */
public interface ISoapService extends IASoapService {

	/**
	 * 用户登录--手机号 phone|密码 password
	 * 
	 * @param property_va
	 */
	void userLogin(Object[] property_va);

    /**
     * 发送手机号得到短信验证码--手机号 phone
     * 
     * @param property_va
     */
    void getCodeByPhone(Object[] property_va);

    /**
     * 用户注册--手机号 phone|验证码 code|昵称 nickname|密码 password
     * 
     * @param property_va
     */
    void memberReg(Object[] property_va);

    /**
     * 首页-幻灯片数组
     * 
     * @param property_va
     */
    void getIndexAdvertise(Object[] property_va);

    /**
     * 首页-新品商品数组
     * 
     * @param property_va
     */
    void getIndexProduct(Object[] property_va);

    /**
     * 首页-预订商品数组
     * 
     * @param property_va
     */
    void getIndexPreProduct(Object[] property_va);

    /**
     * 首页-分类列表获取
     * 
     * @param property_va
     */
    void getProductClass(Object[] property_va);

    /**
     * 首页-分类内容列表获取--分类id classid|页大小 pagesize|第几页 pageindex
     * 
     * @param property_va
     */
    void getProductByClass(Object[] property_va, boolean isPage);

    /**
     * 首页-新品更多获取、预订更多获取--销售类型(presell:预售,sell:现有商品) selltype|页大小 pagesize|第几页 pageindex
     * 
     * @param property_va
     */
    void getProductBySellType(Object[] property_va, boolean isPage);

    /**
     * 预订月份查询--年 year|月 month
     * 
     * @param property_va
     */
    void getPreProductByMonth(Object[] property_va);
}
