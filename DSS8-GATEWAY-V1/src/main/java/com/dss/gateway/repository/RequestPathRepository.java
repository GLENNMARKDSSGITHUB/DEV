package com.dss.gateway.repository;

import com.dss.gateway.entity.RequestPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RequestPathRepository extends JpaRepository<RequestPath, Long> {

    @Query(value = "SELECT RESOURCES_CODE FROM DSS_REQUEST_PATH WHERE REQUEST_PATH = :requestPath", nativeQuery = true)
    String getPathCodeByRequestPath(@Param("requestPath") String requestPath);

    @Query(value = "SELECT ACTION_CODE FROM DSS_REQUEST_PATH WHERE REQUEST_PATH = :requestPath", nativeQuery = true)
    String getActionCodeByRequestPath(@Param("requestPath") String requestPath);
}
