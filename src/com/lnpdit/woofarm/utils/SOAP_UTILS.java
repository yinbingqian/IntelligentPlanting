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
        public static final String ZUOWUKU = "app/api/crop/listData";//作物库列表
        public static final String GETWEATHER = "weatherInfo";//获取天气
        public static final String PLANTINGLIST = "app/api/plant/listData";//种植管理里
        public static final String PENGALARM = "app/api/peng/alarm";//大棚报警
        public static final String FINISHPLANT = "app/api/plant/end";//结束种植
        public static final String COLLECTIONLIST = "app/api/collection/listData";//大棚温度曲线
        public static final String SCENESLIST = "app/api/scenes/listData";//大棚场景列表
        public static final String EQUIPMENTLIST = "app/api/equipment/listData";//大棚控制面板控制器列表
        public static final String EQUIPMENTSTATE = "app/api/equipment/updateState";//大棚控制面板控制器开关
        public static final String CROPINFO = "app/api/crop";//作物库详情
        public static final String NEWSLIST1 = "app/api/news/listData";//农情资讯
        public static final String NEWSLIST2 = "app/api/news/./listData";//农情资讯
        public static final String NEWSLIST3 = "app/api/news/././listData";//农情资讯
        public static final String NEWSLIST4 = "app/api/news/./././listData";//农情资讯
        public static final String NEWSLISTUPDATE = "app/api/news";//农情资讯增加点击次数
        public static final String VALIDATEPWD = "a/app/api/validatePassword";//修改密码-验证旧密码
        public static final String UPDATEPWD = "a/app/api/updatePassword";//修改密码
        public static final String DEVICELIST = "app/api/equipment/user/listData";//设备列表
        
        
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
    public static final String IP = "http://47.93.172.242:8980/zhzz/";//服务器
//    public static final String IP = "http://192.168.1.122:8980/zhzz/";//本地
    public static final String WEATHER_IP = "http://restapi.amap.com/v3/weather/";//获取天气ip
    public static final String FOLDER_PATH = "https://visiblefarm.com:4431/";
    public static final String URL = IP;
    public static final String URL_WITHOUT_WSDL = IP;
    public static final String PIC_FILE = FOLDER_PATH + "image/";
    // login type
    public static final int POLICE = 0;// 警察
    public static final int CITIZEN = 1;// 市民
    
}
