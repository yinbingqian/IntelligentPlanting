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
     * 用户登出--__sid
     * 
     * @param property_va
     */
    void logout(Object[] property_va);

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
     * 农场列表-- userCode
     * 
     * @param property_va
     */
    void farmListData(Object[] property_va);

    /**
     * 农场列表获取第一条-- userCode
     * 
     * @param property_va
     */
    void farmListDataFirst(Object[] property_va);
    
    /**
     * 大棚列表-- farmCode
     * 
     * @param property_va
     */
    void greenhouseListData(Object[] property_va);

    /**
     * 大棚信息-- farmCode
     * 
     * @param property_va
     */
    void planting(Object[] property_va);

    /**
     * 作物库列表
     * 
     * @param property_va
     */
    void getZuowukuList(Object[] property_va);

    /**
     * 获取天气--key|city当前城市
     * 
     * @param property_va
     */
    void getWeather(Object[] property_va);

    /**
     * 作物库列表--farmCode 农场id
     * 
     * @param property_va
     */
    void getPlantingList(Object[] property_va);

    /**
     * 大棚报警--pengCode 大棚
     * 
     * @param property_va
     */
    void getPengAlarm(Object[] property_va);

    /**
     * 结束种植--id
     * 
     * @param property_va
     */
    void finishplant(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号|equipType 02 是温度 03 湿度 07 光照 08 二氧化碳
     * 
     * @param property_va
     */
    void collection(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号
     * 
     * @param property_va
     */
    void scenesList(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号|equipKind
     * 
     * @param property_va
     */
    void equipmentList(Object[] property_va);

    /**
     * 大棚控制器开关--switchId|equipStatus|equipCode=12282
     * 
     * @param property_va
     */
    void equipmentState(Object[] property_va);

    /**
     * 作物库详情--id
     * 
     * @param property_va
     */
    void getCropInfo(Object[] property_va);

    /**
     * 农情资讯-头条新闻--type 新闻四种类型 1\2\3\4 |pageNo页码|pageSize 每页数量 
     * 
     * @param property_va
     */
    void getNewsList1(Object[] property_va, boolean isPage);

    /**
     * 农情资讯-蔬菜菌类--type 新闻四种类型 1\2\3\4 |pageNo页码|pageSize 每页数量 
     * 
     * @param property_va
     */
    void getNewsList2(Object[] property_va, boolean isPage);

    /**
     * 农情资讯-生鲜瓜果--type 新闻四种类型 1\2\3\4 |pageNo页码|pageSize 每页数量 
     * 
     * @param property_va
     */
    void getNewsList3(Object[] property_va, boolean isPage);
    /**
     * 农情资讯-粮油饲料--type 新闻四种类型 1\2\3\4 |pageNo页码|pageSize 每页数量 
     * 
     * @param property_va
     */
    void getNewsList4(Object[] property_va, boolean isPage);

    /**
     * 农情资讯增加点击次数
     * 
     * @param property_va
     */
    void getNewsUpdatecount(Object[] property_va);

    /**
     * 修改密码-验证旧密码--userCode手机号|oldPassword旧密码|__ajax=json|sid 登录保存的sessionid
     * 
     * @param property_va
     */
    void validatePassword(Object[] property_va);
    
    /**
     * 修改密码--userCode手机号|password新密码|__ajax=json|sid 登录保存的sessionid
     * 
     * @param property_va
     */
    void updatePassword(Object[] property_va);

    /**
     * 设备列表--userCode手机号
     * 
     * @param property_va
     */
    void getDeviceList(Object[] property_va);
}
