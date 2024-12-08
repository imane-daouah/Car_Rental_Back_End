package com.codewithprojects.spring.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin")


public class Admin extends Personne{
}
