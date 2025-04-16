package com.skandar.gestion.entities;

import org.springframework.data.rest.core.config.Projection;


/*GET http://localhost:8082/api/roles?projection=nomRole */


@Projection(name = "nomRole", types = { Role.class })
public interface RoleProjection {
   public String getNomRole();
}
