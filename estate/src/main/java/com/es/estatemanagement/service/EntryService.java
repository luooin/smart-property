package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.Entry;
import com.github.pagehelper.Page;

import java.util.Map;

public interface EntryService {


    public Page<Entry> search(Map searchMap);

}
