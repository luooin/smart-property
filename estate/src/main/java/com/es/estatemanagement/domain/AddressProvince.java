package com.es.estatemanagement.domain;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 省份表
 */
@Table(name="tb_address_province")
public class AddressProvince implements Serializable {
    @Id
    private Integer id;   //主键
    private String code;  //省份编码
    private String name;  //省份名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
