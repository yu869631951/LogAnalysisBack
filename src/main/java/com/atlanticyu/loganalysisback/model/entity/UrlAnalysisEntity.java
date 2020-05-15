package com.atlanticyu.loganalysisback.model.entity;

public class UrlAnalysisEntity {
    private String url_value;
    private Integer visit_count;

    public String getUrl_value() {
        return url_value;
    }

    public void setUrl_value(String url_value) {
        this.url_value = url_value;
    }

    public Integer getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(Integer visit_count) {
        this.visit_count = visit_count;
    }

    @Override
    public String toString() {
        return "UrlAnalysisEntity{" +
                "url_value='" + url_value + '\'' +
                ", visit_count=" + visit_count +
                '}';
    }
}
