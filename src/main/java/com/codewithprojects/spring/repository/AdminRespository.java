package com.codewithprojects.spring.repository;

import com.codewithprojects.spring.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRespository extends JpaRepository<Admin, Long> {

}
