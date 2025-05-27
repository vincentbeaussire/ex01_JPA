package org.example;

import org.example.entity.Animal;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercise_zoo");
        EntityManager em = emf.createEntityManager();

        Animal animal = new Animal("Youpi", 3, "simple", "27-05-2025");
        Animal animal1 = new Animal("Oupa", 8, "medium", "15-02-2024");
        Animal animal2 = new Animal("Payou", 5, "hard", "05-10-2023");

        em.getTransaction().begin();
        em.persist(animal);
        em.getTransaction().commit();

        Animal animalId = em.find(Animal.class, 1);
        if (animalId != null) {
            System.out.println(animalId);
        } else {
            System.out.println("Animal Id not found");
        }

        Animal animalName = em.find(Animal.class, "Youpi");
        if (animalName != null) {
            System.out.println(animalName);
        } else {
            System.out.println("Animal Name not found");
        }

        Animal dietBalance = em.find(Animal.class, "simple");
        if (dietBalance != null) {
            System.out.println(dietBalance);
        } else {
            System.out.println("Diet balance not found");
        }

        try {
            Animal animalfindByReference = em.getReference(Animal.class, 4);
            System.out.println(animalfindByReference);
        } catch (EntityNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        TypedQuery<Animal> query = em.createQuery("select a from Animal a", Animal.class);
        List<Animal> animals = query.getResultList();

        animals.forEach(System.out::println);

    }
}