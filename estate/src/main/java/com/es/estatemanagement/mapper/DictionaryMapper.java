package com.es.estatemanagement.mapper;


import com.es.estatemanagement.domain.Dictionary;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DictionaryMapper extends Mapper<Dictionary> {


    @Select("SELECT *from tb_dictionary WHERE parent_id ='1'")
    public List<Dictionary>findDictionary();

    @Select("SELECT * FROM tb_dictionary WHERE dictionary_id =#{dictionaryId}")
    public Dictionary findById(Integer dictionaryId);

    @Update("UPDATE `estatedb`.`tb_dictionary` SET `dictionary_name` = #{dictionaryName}, " +
            "`dictionary_type` = #{dictionaryType}, `parent_id` = #{parentId}, " +
            "`remarks` = #{remarks},  `modify_by` = #{modifyBy}," +
            " `modify_date` = #{modifyDate} WHERE `dictionary_id` = #{dictionaryId}")
    public int update(Dictionary dictionary);

    @Delete("DELETE FROM `estatedb`.`tb_dictionary` WHERE `dictionary_id` = #{dictionaryId}")
    public int del(Long dictionaryId);

    @Select("SELECT * FROM tb_dictionary WHERE parent_id !='1' and dictionary_type=#{dictionaryType}")
    public List<Dictionary> findByParentId(String dictionaryType);

//    @Delete("")
//    public boolean delete();

}
