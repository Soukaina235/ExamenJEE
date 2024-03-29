package org.example.examjee.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> skills =  new ArrayList<>();
        skills.add("java");
        skills.add("jee");
        Employe employe = new Employe(1, "emp1", "emp1@gmail.com", skills, Post.DEVOPS);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(employe);
        tx.commit();

        em.close();
        emf.close();
    }
}
