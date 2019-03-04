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
     * 大棚温度曲线--pengCode 大棚编号|equipType 02 是温度 03 湿度 07 光照 08 二氧化碳  01土壤温度 02土壤湿度
     * 
     * @param property_va
     */
    void collection(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号|equipType 02 是温度 03 湿度 07 光照 08 二氧化碳  01土壤温度 02土壤湿度
     * 
     * @param property_va
     */
    void collection3(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号|equipType 02 是温度 03 湿度 07 光照 08 二氧化碳  01土壤温度 02土壤湿度
     * 
     * @param property_va
     */
    void collection4(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号|equipType 02 是温度 03 湿度 07 光照 08 二氧化碳  01土壤温度 02土壤湿度
     * 
     * @param property_va
     */
    void collection5(Object[] property_va);

    /**
     * 大棚温度曲线--pengCode 大棚编号|equipType 02 是温度 03 湿度 07 光照 08 二氧化碳  01土壤温度 02土壤湿度
     * 
     * @param property_va
     */
    void collection6(Object[] property_va);
    /**
     * 大棚场景列表--pengCode 大棚编号
     * 
     * @param property_va
     */
    void scenesList(Object[] property_va);

    /**
     * 大棚控制面板控制器列表--pengCode 大棚编号|equipKind
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
    

    /**
     * 我的农场传感器数据--equipCode 设备编号
     * 
     * @param property_va
     */
    void getSensorData(Object[] property_va);
    

    /**
     * 网关列表--pengCode大棚编号
     * 
     * @param property_va
     */
    void getGatewayList(Object[] property_va);

    /**
     * //设备绑定--equipCode 网关号|equipName网关名称|pengCode 大棚编号|sensors 传感器字符串用;分割
     * 
     * @param property_va
     */
    void equipmentBind (Object[] property_va);


    /**
     * 摄像头列表--pengCode大棚编号
     * 
     * @param property_va
     */
    void getCameraList(Object[] property_va);


    /**
     * 病虫害列表--cropCode作物编号
     * 
     * @param property_va
     */
    void getCropDiseaseList(Object[] property_va);
    

    /**
     * 病虫害作物列表
     * 
     * @param property_va
     */
    void getCropList(Object[] property_va);


    /**
     * 溯源列表--userCode
     * 
     * @param property_va
     */
    void getOriginsList(Object[] property_va);


    /**
     * 溯源详情--plantId
     * 
     * @param property_va
     */
    void getOriginsInfo(Object[] property_va);

    /**
     * 溯源详情打分--plantId |client |stars
     * 
     * @param property_va
     */
    void getOriginsScore(Object[] property_va);

    /**
     * 农技服务视频--type=1/2/3s
     * 
     * @param property_va
     */
    void getVideolist(Object[] property_va);

    /**
     * 农事计划--userCode
     * 
     * @param property_va
     */
    void getPlanlist(Object[] property_va);

    /**
     * 农事计划-完成计划 --id
     * 
     * @param property_va
     */
    void finishPlan(Object[] property_va);

    /**
     * 报警详情 --pengCode大棚编号
     * 
     * @param property_va
     */
    void getAlarmInfo(Object[] property_va);

    /**
     * 获取大棚阈值 --pengCode大棚编号
     * 
     * @param property_va
     */
    void getPengLimit(Object[] property_va);

    /**
     * 大棚阈值更新 --pengCode大棚编号|brightnessMini|brightnessMax|airTemperatureMini|airTemperatureMax|airHumidityMini|airHumidityMax
     * |soilTemperatureMini|soilTemperatureMax|soilHumidityMini|soilHumidityMax
     * 
     * @param property_va
     */
    void updatePengLimit(Object[] property_va);

    /**
     * 新增定植--plotId地块id|yield产量|varietyName作物名称|cropCode作物id（作物库获取）|beginTime2019-01-01|cropVariety作物类别0-5
     * 
     * @param property_va
     */
    void savePlantingInfo(Object[] property_va);

    /**
     * 新增定植页基地列表 --userCode
     * 
     * @param property_va
     */
    void getBaseList(Object[] property_va);

    /**
     * 新增定植中获取作物品种列表
     * 
     * @param property_va
     */
    void getZuowukuVarietyList(Object[] property_va);

    /**
     * 生长列表 --plantId
     * 
     * @param property_va
     */
    void getGrowList(Object[] property_va);

    /**
     * 生长详情 --userCode
     * 
     * @param property_va
     */
    void getGrowInfo(Object[] property_va);

    /**
     * 采摘列表 --userCode
     * 
     * @param property_va
     */
    void getPickList(Object[] property_va);
    

    /**
     * 新增定植--plantId采摘作物|userCode采摘人员|pickDate采摘时间|pickUnit采摘规格|traceType|remarks备注|pickQuantity采摘数量
     * 
     * @param property_va
     */
    void savePickInfo(Object[] property_va);
    

    /**
     * 新增采摘中采摘作物列表--farmCode 农场id
     * 
     * @param property_va
     */
    void getPickCropList(Object[] property_va);

    /**
     * 新增采摘中采摘人员列表--userCode
     * 
     * @param property_va
     */
    void getPickUserList(Object[] property_va);
    

    /**
     * 首页种植列表含地块内容--userCode
     * 
     * @param property_va
     */
    void getPlantingLists(Object[] property_va);
}
