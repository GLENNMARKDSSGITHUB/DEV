/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.repository.user;

import com.dss.entity.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is an interface UsersRepository which extends the JpaRepository interface
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    @Query(value = "SELECT * FROM DSS_USERS WHERE EMAIL = :email", nativeQuery = true)
    List<Users> findUserByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM DSS_USERS WHERE EMAIL = :email", nativeQuery = true)
    Users findUserByEmailAddress(@Param("email") String email);

    @Query(value = "SELECT * FROM DSS_USERS WHERE CELLPHONE_NO = :cellphoneNumber", nativeQuery = true)
    List<Users> findUserByCellphoneNumber(@Param("cellphoneNumber") String cellphoneNumber);
}
