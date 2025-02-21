package com.scm.backend.scm_backend.repository;

import com.scm.backend.scm_backend.entity.User;
import com.scm.backend.scm_backend.projections.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
//@RepositoryRestResource(exported = false) // it will stop this repo working like an API
//@RepositoryRestResource(excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, String> {

}
