/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.entity.user;

import com.dss.entity.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This is an Entity Class for Users
 */

@Entity
@Table(name = "DSS_USERS")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @Column(name = "DSS_USER_ID", length = 100, nullable = false)
    private String dssUserId;

    @Column(name = "FIRST_NAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "STATUS", length = 15, nullable = false)
    private String status;

    @Column(name = "CELLPHONE_NO", length = 20, nullable = false)
    private String cellphoneNumber;

    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Column(name = "CREATED_BY", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "LAST_MODIFICATION_DATE")
    private Date lastModificationDate;

    @Column(name = "LAST_MODIFIED_BY", length = 100)
    private String lastModifiedBy;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Roles> userRoles;

    public Users(String dssUserId, String firstName, String lastName, String email, String password, String status, String cellphoneNumber, Date creationDate, String createdBy, Date lastModificationDate, String lastModifiedBy) {
        this.dssUserId = dssUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.cellphoneNumber = cellphoneNumber;
        this.creationDate = creationDate;
        this.createdBy = createdBy;
        this.lastModificationDate = lastModificationDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
