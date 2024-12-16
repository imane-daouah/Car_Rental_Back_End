package com.codewithprojects.spring.repository;


import com.codewithprojects.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
