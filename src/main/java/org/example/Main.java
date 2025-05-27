package org.example;

import org.example.entity.Animal;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercise_zoo");
        EntityManager em = emf.createEntityManager();

        Animal animal = new Animal(1, "Youpi", 3, "simple", LocalDate.of(2025, 05, 27));
        Animal animal1 = new Animal(2, "Oupa", 8, "medium", LocalDate.of(2025, 02, 14));
        Animal animal2 = new Animal(3, "Payou", 5, "hard", LocalDate.of(2025, 03, 05));

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
            Animal animalfindByReference = em.getReference(Animal.class, 1);
            System.out.println(animalfindByReference);
        } catch (EntityNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        TypedQuery<Animal> query = em.createQuery("select a from Animal a", Animal.class);
        List<Animal> animals = query.getResultList();

        Animal animalFound = em.find(Animal.class, animal.getId());

        TypedQuery<Animal> query1 = em.createQuery("select a from Animal a where a.name = :name", Animal.class);
        query1.setParameter("name", "Youpi");
        List<Animal> animals2 = query1.getResultList();

        TypedQuery<Animal> query2 = em.createQuery("select a from Animal a where a.diet = :diet", Animal.class);
        query2.setParameter("diet", "simple");
        List<Animal> animals3 = query2.getResultList();

        animals.forEach(System.out::println);

    }
}