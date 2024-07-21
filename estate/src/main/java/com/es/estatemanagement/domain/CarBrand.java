package com.es.estatemanagement.domain;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_car_brand")
public class CarBrand implements Serializable {


    @Id
    private Integer id;        //车辆品牌Id
    private String cbrandName;    //车辆品牌名称


    @Override
    public String toString() {
        return "CarBrand{" +
                "id=" + id +
                ", cbrandName='" + cbrandName + '\'' +
                '}';
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCbrandName() {
        return cbrandName;
    }

    public void setCbrandName(String cbrandName) {
        this.cbrandName = cbrandName;
    }
}
