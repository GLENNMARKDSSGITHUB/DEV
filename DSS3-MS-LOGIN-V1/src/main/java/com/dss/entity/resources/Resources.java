package com.dss.entity.resources;

import com.dss.entity.action.Action;
import com.dss.entity.permission.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DSS_RESOURCES")
@NoArgsConstructor
@AllArgsConstructor
public class Resources {

    @Id
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    @ManyToOne
    private Permission permission;

    @OneToMany(mappedBy = "resources")
    private List<Action> action;

    public Resources(String resourceId, String resourceName, Permission permission) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.permission = permission;
    }
}
