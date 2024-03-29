package org.example.examjee.dao.jpa;

import jakarta.persistence.*;
import org.example.examjee.dao.EmployeDao;
import org.example.examjee.model.Employe;
import org.example.examjee.model.Project;

import java.util.List;

public class EmployeDaoImpl implements EmployeDao {
    @Override
    public List<Employe> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        List<Employe> employees = null;

        try {
            employees = em.createQuery("SELECT e FROM Employe e", Employe.class).getResultList();
            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public int save(Employe employe) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(employe);
            tx.commit();
            return 1; // Indicates successful persistence
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return 0; // Indicates failure in persistence
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean delete(Employe employe) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            if (employe != null) {
                Employe managedEmploye = em.merge(employe); // Merge the detached entity into the current persistence context
                em.remove(managedEmploye);
                tx.commit();
                return true; // Indicates successful deletion
            } else {
                return false; // Indicates employee not found
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false; // Indicates failure in deletion
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public boolean isEmailUnique(String email) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();
        boolean unique;

        try {
            Query query = em.createQuery("SELECT COUNT(e) FROM Employe e WHERE e.email LIKE :email");
            query.setParameter("email", email);
            long count = (Long) query.getSingleResult();
            unique = (count == 0);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
            emf.close();
        }

        return unique;
    }


    public void affect(Employe employe, Project project, double implication) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Eclipselink");
        EntityManager em = emf.createEntityManager();

        Query query = em.createNativeQuery("INSERT INTO employe_project (employe_id, project_id, charge) VALUES(:employe, :project, :charge)");
        query.setParameter("employe", employe.getId());
        em.close();
        emf.close();
    }
}

