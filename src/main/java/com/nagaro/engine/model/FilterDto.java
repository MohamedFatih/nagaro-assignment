package com.nagaro.engine.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FilterDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
    Date fromdate;
 
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
    Date todate;

    Double higher;

    Double lower;

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getTodate() {
        return todate;
    }

    public void setTodate(Date todate) {
        this.todate = todate;
    }

    public Double getHigher() {
        return higher;
    }

    public void setHigher(Double higher) {
        this.higher = higher;
    }

    public Double getLower() {
        return lower;
    }

    public void setLower(Double lower) {
        this.lower = lower;
    }

    @Override
    public String toString() {
        return "FilterDto [fromdate=" + fromdate + ", higher=" + higher + ", lower=" + lower + ", todate=" + todate
                + "]";
    }

}