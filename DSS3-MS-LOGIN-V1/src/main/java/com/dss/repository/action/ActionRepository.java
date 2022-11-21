package com.dss.repository.action;

import com.dss.entity.action.Action;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Action, String> {
}
