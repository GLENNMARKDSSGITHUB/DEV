package com.dss.gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DSS_REQUEST_PATH")
@NoArgsConstructor
@AllArgsConstructor
public class RequestPath {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionId;

    @Column(name = "RESOURCES_CODE")
    private String resourcesCode;

    @Column(name = "ACTION_CODE")
    private String actionCode;

    @Column(name = "REQUEST_PATH")
    private String requestPath;

    public RequestPath(String resourcesCode, String actionCode, String requestPath) {
        this.resourcesCode = resourcesCode;
        this.actionCode = actionCode;
        this.requestPath = requestPath;
    }
}
