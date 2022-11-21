/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.repository.roles;

import com.dss.entity.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is an interface RolesRepository which extends the JpaRepository interface
 */

@Repository
public interface RolesRepository extends JpaRepository<Roles, String> {

    @Query(value = "SELECT * FROM DSS_ROLES WHERE USER_DSS_USER_ID = :dssUserId", nativeQuery = true)
    List<Roles> findRolesByDssUserId(@Param("dssUserId") String dssUserId);
}
