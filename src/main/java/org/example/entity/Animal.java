package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String dietBalance;
    private String arrivalDate;

    public Animal(String name, int age, String dietBalance, String arrivalDate) {
        this.name = name;
        this.age = age;
        this.dietBalance = dietBalance;
        this.arrivalDate = arrivalDate;
    }
}
