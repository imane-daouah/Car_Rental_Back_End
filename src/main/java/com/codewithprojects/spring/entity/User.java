package com.codewithprojects.spring.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Client")
public class User extends Personne{

}
