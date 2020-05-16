package com.atlanticyu.loganalysisback.model.entity;


public class NetTimeAnalysisEntity {
    private String time_value;
    private Integer visit_count;

    public String getTime_value() {
        return time_value;
    }

    public void setTime_value(String time_value) {
        this.time_value = time_value;
    }

    public Integer getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(Integer visit_count) {
        this.visit_count = visit_count;
    }

    @Override
    public String toString() {
        return "NetTimeAnalysisEntity{" +
                "time_value='" + time_value + '\'' +
                ", visit_count=" + visit_count +
                '}';
    }
}
