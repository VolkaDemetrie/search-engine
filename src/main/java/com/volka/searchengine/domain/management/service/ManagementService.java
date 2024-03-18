package com.volka.searchengine.domain.management.service;

import com.volka.searchengine.core.constant.ResponseCode;
import com.volka.searchengine.dto.ManagementRequest;

public interface ManagementService {
    ResponseCode initAcit(ManagementRequest.Acit param);

    ResponseCode addAcit(ManagementRequest.Acit param);

    ResponseCode delAcit(ManagementRequest.Acit param);
}
