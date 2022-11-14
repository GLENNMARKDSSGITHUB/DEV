/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.repository.roles;

import com.dss.entity.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is an interface RolesRepository which extends the JpaRepository interface
 */

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
}
