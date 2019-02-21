package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyAstronomyPictureDetail {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("explanation")
    @Expose
    private String explanation;
    @SerializedName("hdurl")
    @Expose
    private String hdurl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    public DailyAstronomyPictureDetail(){}

    public DailyAstronomyPictureDetail(String date, String explanation, String hdurl, String title, String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.title = title;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
