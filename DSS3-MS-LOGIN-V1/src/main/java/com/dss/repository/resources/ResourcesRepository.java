package com.dss.repository.resources;

import com.dss.entity.resources.Resources;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesRepository extends JpaRepository<Resources, String> {
}
