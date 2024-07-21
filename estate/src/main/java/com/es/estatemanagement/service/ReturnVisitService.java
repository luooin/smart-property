package com.es.estatemanagement.service;

import com.es.estatemanagement.domain.ReturnVisit;

import java.util.List;

public interface ReturnVisitService {
    public List<ReturnVisit> findByVisitId();
}
