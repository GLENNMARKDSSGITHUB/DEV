package com.dss.entity.permission;

import com.dss.entity.resources.Resources;
import com.dss.entity.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DSS_PERMISSION")
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    @Column(name = "PERMISSION_ID")
    private String permissionId;

    @Column(name = "PERMISSION_NAME")
    private String permissionName;

    @ManyToOne
    private Roles roles;

    @OneToMany(mappedBy = "permission")
    private List<Resources> resources;

    public Permission(String permissionId, String permissionName, Roles roles) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.roles = roles;
    }
}
