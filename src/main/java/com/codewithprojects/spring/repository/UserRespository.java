package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRespository extends JpaRepository<User,Long> {

}
