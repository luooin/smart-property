package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Dictionary;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface DictionaryService {

    public List<Dictionary> findDictionaryBiz();

    public Page<Dictionary> search(Map searchMap);

    public Boolean add(Dictionary dictionary);

    public Dictionary findById(Integer id);

    public Boolean update(Dictionary dictionary);

    public Boolean del(List<Long> ids);

    public Boolean updateBiz(Dictionary dictionary);

    public Boolean delBiz(List<Long> dictionaryId);

    public List<Dictionary> findByParentIdBiz(String dictionaryType);


}
