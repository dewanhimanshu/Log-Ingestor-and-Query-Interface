package com.example.logIngestor.repository;

import com.example.logIngestor.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role,String> {

  public Role findByUserId(String userId);
}
