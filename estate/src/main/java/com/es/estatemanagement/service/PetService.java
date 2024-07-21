package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Pet;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface PetService {

    public List<Pet> findAll();

    public Page<Pet> search(Map searchMap);

    public Boolean add(Pet house);

    public Pet findById(Integer id);

    public Boolean update(Pet house);

    public Boolean del(List<Integer> ids);
}
