package com.es.estatemanagement.service;



import com.es.estatemanagement.domain.Owner;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface OwnerService {

    public List<Owner> findAll(String communityName);

    public Page<Owner> search(Map searchMap);

    public Integer findByName(String name);

    public Boolean add(Owner house);

    public Owner findById(Integer id);

    public Boolean update(Owner house);

    public Boolean del(List<Integer> ids);

    public Integer findCount(String communityName);

    public Integer findCounts(String communityName);

}
