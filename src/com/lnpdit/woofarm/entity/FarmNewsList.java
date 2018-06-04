package com.lnpdit.woofarm.entity;

import java.io.Serializable;

public class FarmNewsList implements Serializable {
    private String newsId;
    private String newsName;
    private String url;
    private String newsCount;
    private String newsTime;
    private String imgUrl;
    private String createDate;
    private String source;
    private String type;
    private String content;
    private String viewCount;
    private String hoursAgo;
    
    
    public String getNewsId() {
        return newsId;
    }
    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
    public String getNewsName() {
        return newsName;
    }
    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getNewsCount() {
        return newsCount;
    }
    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }
    public String getNewsTime() {
        return newsTime;
    }
    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getViewCount() {
        return viewCount;
    }
    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
    public String getHoursAgo() {
        return hoursAgo;
    }
    public void setHoursAgo(String hoursAgo) {
        this.hoursAgo = hoursAgo;
    }
   
    
    
}
