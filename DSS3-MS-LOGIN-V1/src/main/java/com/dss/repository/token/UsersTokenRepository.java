package com.dss.repository.token;

import com.dss.entity.token.UsersToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersTokenRepository extends JpaRepository<UsersToken, Long> {
}
