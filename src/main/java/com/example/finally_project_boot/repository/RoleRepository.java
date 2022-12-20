package com.example.finally_project_boot.repository;

import com.example.finally_project_boot.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getByName(String name);

}
