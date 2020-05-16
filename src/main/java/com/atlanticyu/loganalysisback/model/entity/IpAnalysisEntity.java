package com.atlanticyu.loganalysisback.model.entity;

public class IpAnalysisEntity {
    private String ip_value;
    private Integer visit_count;

    public String getIp_value() {
        return ip_value;
    }

    public void setIp_value(String ip_value) {
        this.ip_value = ip_value;
    }

    public Integer getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(Integer visit_count) {
        this.visit_count = visit_count;
    }

    @Override
    public String toString() {
        return "IpAnalysisEntity{" +
                "ip_value='" + ip_value + '\'' +
                ", visit_count=" + visit_count +
                '}';
    }
}
