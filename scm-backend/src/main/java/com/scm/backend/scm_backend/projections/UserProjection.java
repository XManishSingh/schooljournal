package com.scm.backend.scm_backend.projections;

import com.scm.backend.scm_backend.entity.User;
import org.springframework.data.rest.core.config.Projection;



@Projection(name = "user-project", types = {User.class})
public interface UserProjection {

    String getName();
    String getEmail();
    String getAbout();
}
