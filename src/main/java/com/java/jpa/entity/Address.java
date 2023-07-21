package com.java.jpa.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addr")
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;

    public Address(String street, User user) {
        this.street = street;
        this.user = user;
    }

    public String getStreet() {
        return street;
    }
}
