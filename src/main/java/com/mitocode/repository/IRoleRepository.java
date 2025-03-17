package com.mitocode.repository;


import com.mitocode.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends IGenericRepository<Role, Integer> {
}
