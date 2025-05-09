package com.skandar.gestion.repos;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.skandar.gestion.entities.HistoriqueAction;


@RepositoryRestResource(path = "rest")
public interface HistoriqueActionRepository extends JpaRepository<HistoriqueAction, Long> {
}