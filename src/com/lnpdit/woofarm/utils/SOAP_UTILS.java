package com.lnpdit.woofarm.utils;

public class SOAP_UTILS {
    public class METHOD {
        public static final String LOGIN = "a/login";//登录
        public static final String LOGOUT = "a/logout";//登出
        public static final String GETCODEBYPHONE = "Member.svc/GetCodeByPhone";//获取验证码
        public static final String MEMBERREG = "app/api/register";//注册
        public static final String FARMLISTDATA = "app/api/farm/listData";//农场列表
        public static final String FARMLISTDATAFIRST = "app/api/farm/list";//农场列表调用第一个
        public static final String GREENHOUSELISTDATA = "app/api/peng/listData";//大棚列表
        public static final String PLANTING = "app/api/peng/planting";//大棚信息
        public static final String GETWEATHER = "weatherInfo";//获取天气
        public static final String PLANTINGLIST = "v2/app/api/plant/list";//种植管理里
        public static final String PENGALARM = "app/api/peng/alarm";//大棚报警
        public static final String FINISHPLANT = "app/api/plant/end";//结束种植
        public static final String COLLECTIONLIST = "app/api/collection/listData";//大棚温度曲线 03空气温度
        public static final String COLLECTIONLIST3 = "app/api/collection/./listData";//大棚温度曲线 04空气湿度
        public static final String COLLECTIONLIST4 = "app/api/collection/././listData";//大棚温度曲线 光照强度
        public static final String COLLECTIONLIST5 = "app/api/collection/./././listData";//大棚温度曲线 01土壤温度
        public static final String COLLECTIONLIST6 = "app/api/collection/././././listData";//大棚温度曲线 02土壤湿度
        public static final String SCENESLIST = "app/api/scenes/listData";//大棚场景列表
        public static final String CROPINFO = "v2/app/api/crop";//作物库详情
        public static final String NEWSLIST1 = "app/api/news/listData";//农情资讯
        public static final String NEWSLIST2 = "app/api/news/./listData";//农情资讯
        public static final String NEWSLIST3 = "app/api/news/././listData";//农情资讯
        public static final String NEWSLIST4 = "app/api/news/./././listData";//农情资讯
        public static final String NEWSLISTUPDATE = "app/api/news";//农情资讯增加点击次数
        public static final String VALIDATEPWD = "a/app/api/validatePassword";//修改密码-验证旧密码
        public static final String UPDATEPWD = "a/app/api/updatePassword";//修改密码
        public static final String DEVICELIST = "app/api/equipment/user/listData";//设备列表
        public static final String SENSORDATALIST = "v1/app/api/collection/sensorData";//我的农场传感器数据
        public static final String GATEWATLIST = "v1/app/api/gateway/list";//网关列表
        public static final String EQUIPMENTBIND = "v1/app/api/equipment/bind";//设备绑定
        public static final String CAMERALIST = "v1/app/api/camera/list";//摄像头列表
        public static final String CROPDISEASE = "v1/app/api/cropDisease/list";//病虫害列表
        public static final String CROPLIST = "app/api/crop/./listData";//病虫害作物列表
        public static final String ORIGINSLIST = "v1/app/api/plant/source/list";//溯源列表
        public static final String ORIGINSINFO = "v1/app/api/traceToTheSource";//溯源详情
        public static final String FARMSERVICEVIDEOLIST = "v1/app/api/video/list";//农技服务视频
        public static final String ORIGINSSCORE = "v1/app/api/score";//溯源详情打分
        public static final String PLANLIST = "v1/app/api/plan/list";//农事计划
        public static final String PLANFINISH = "v1/app/api/plan/update";//农事计划-完成计划
        public static final String ALARMINFO = "app/api/alarm/list";//农事计划-完成计划
        public static final String GETPENGLIMIT = "v1/app/api/peng/limit";//获取大棚阈值
        public static final String UPDATEPENGLIMIT = "v1/app/api/peng/limit/update";//大棚阈值更新
        public static final String GETBASELIST = "v2/app/api/plot/list";//新增定植获取基地列表
        public static final String SAVEPLANTINGINFO = "v2/app/api/plant/save";//新增定植
        public static final String ZUOWUKU = "v2/app/api/crop/listData";//作物库列表
        public static final String ZUOWUKU2 = "v2/app/api/./crop/listData";//新增定植获取作物品种列表
        public static final String GETGROWLIST = "v2/app/api/plant/list";//生长列表
        public static final String GETGROWINFO = "v2/app/api/plantLog/list";//生长详情
        public static final String GETPICKLIST = "v2/app/api/picking/list";//采摘列表
        public static final String SAVEPICKINFO = "v2/app/api/picking/save";//新增采摘
        public static final String PICKCROPLIST = "v2/app/api/./plant/list";//新增采摘中采摘作物列表
        public static final String PICKCROPUSERLIST = "v2/app/api/worker/list";//新增采摘中采摘人员
        public static final String PLANTINGLISTS = "v2/app/api/././plant/list";//首页列表
        public static final String EQUIPMENTLIST = "v2/app/api/control/list";//大棚控制面板控制器列表
        public static final String EQUIPMENTSTATE = "v2/app/api/control/commands";//大棚控制面板控制器开关
        
        
    
    }

    public class ACTION {
        // 首页初始化
        public static final String LIVECIRCLE = "livecircle";
    }
    public class ERROR {
        public static final String ERR0000 = "ERR 000";
        public static final String ERR0001 = "ERR 001";
        public static final String ERR0002 = "ERR 002";
        public static final String ERR0003 = "ERR 003";
        public static final String ERR0004 = "ERR 004";
        public static final String ERR0005 = "ERR 005 XML解析错误";
        public static final String ERR0006 = "ERR 006 本地错误";

    }
    public static final String NAMESPACE = "WoFarm";
    public static final String IP_SIMPLE = "123.56.88.189";
//    public static final String IP = "http://woshi.tsti.cn/WSIWcfService/";
    public static final String IP = "http://49.4.79.9/zhzz/";//服务器
//    public static final String IP = "http://192.168.1.122:8980/zhzz/";//本地
    public static final String WEATHER_IP = "http://restapi.amap.com/v3/weather/";//获取天气ip
    public static final String FOLDER_PATH = "https://visiblefarm.com:4431/";
    public static final String URL = IP;
    public static final String URL_WITHOUT_WSDL = IP;
    public static final String PIC_FILE = FOLDER_PATH + "image/";
    public static final String PIC_PATH = "http://49.4.79.9"; 
    // login type
    public static final int POLICE = 0;// 警察
    public static final int CITIZEN = 1;// 市民
    
}
