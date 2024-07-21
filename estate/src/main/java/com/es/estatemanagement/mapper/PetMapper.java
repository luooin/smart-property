package com.es.estatemanagement.mapper;


import com.es.estatemanagement.domain.Pet;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PetMapper extends Mapper<Pet> {
}
