package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import com.es.estatemanagement.domain.ParkingApplication;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface ParkingApplicationMapper  extends Mapper<ParkingApplication> {

    //审核
    @Select("SELECT * FROM tb_parking_application WHERE id=#{id}")
    public ParkingApplication myParkingApplication(Integer id);

    //查询车位状态
    @Select("SELECT `status` FROM tb_parking WHERE id=#{id}")
    public Parking parkingStarus(Integer id);
    //修改车位状态
    @Update("UPDATE `estatedb`.`tb_parking` SET `status` = #{status} WHERE `id` = #{id}")
    public int updateStatus(Parking parking);
    //业主添加车位
    @Insert("UPDATE `estatedb`.`tb_car` SET `ps_name` = #{psName} WHERE `id` = #{id}")
    public int addCarPsName(Car car);
    //业主名称查询id
    @Select("SELECT id FROM tb_car where car_number =#{carNumber}")
    public Car carOwnerId(String carNumber);
    //车位名称查询id
    @Select("SELECT id FROM tb_parking  WHERE `name`=#{name}")
    public Parking parkingName(String name);

}
