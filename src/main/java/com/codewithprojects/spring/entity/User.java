package com.codewithprojects.spring.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@DiscriminatorValue("Client")
public class User extends Personne{




}
