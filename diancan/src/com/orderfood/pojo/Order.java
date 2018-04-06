package com.orderfood.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private String zhuohao;

    private Date createDate;

    private Float zongjiage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZhuohao() {
        return zhuohao;
    }

    public void setZhuohao(String zhuohao) {
        this.zhuohao = zhuohao == null ? null : zhuohao.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Float getZongjiage() {
        return zongjiage;
    }

    public void setZongjiage(Float zongjiage) {
        this.zongjiage = zongjiage;
    }
}