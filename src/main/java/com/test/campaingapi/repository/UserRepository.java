package com.test.campaingapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.campaingapi.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
