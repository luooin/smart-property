package com.es.estatemanagement.mapper;

import com.es.estatemanagement.domain.Car;
import com.es.estatemanagement.domain.Parking;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface ParkingMapper extends Mapper<Parking> {
    @Select("select community_name from tb_parking group by community_name")
    public List<Parking> findByCommunityName();

    //根据车牌号查询信息
    @Select("SELECT * FROM tb_parking  WHERE car_code=#{carCode}")
    public Parking parkingCarCode(String carCode);
    @Select("SELECT * FROM tb_car WHERE car_number=#{carNumber}")
    public Car carNumber(String carNumber);

    //动态图显示车位
    @Select("SELECT quantity,region FROM tb_parking WHERE community_name=#{communityName} GROUP BY quantity,region")
    public List<Parking> quantity(String communityName);
}
