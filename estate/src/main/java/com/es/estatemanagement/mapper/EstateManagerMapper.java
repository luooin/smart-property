package com.es.estatemanagement.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.es.estatemanagement.domain.EstateManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateManagerMapper extends BaseMapper<EstateManager> {

    @Select("select * from tb_estate_manager where login_name=#{loginName}")
    public EstateManager findByLoginNameAndPassword(String loginName);


    @Select("select m.*,r.role_name from tb_estate_manager m \n" +
            "inner join tb_estate_user_role ru on ru.user_id=m.id \n" +
            "inner join tb_estate_role r on r.role_id=ru.role_id\n" +
            "where m.login_name=#{loginName} and ru.is_deleted=0")
    public EstateManager findByLoginName(String loginName);


    IPage<EstateManager> selectPosts(Page page, @Param(Constants.WRAPPER) QueryWrapper ew);

}
