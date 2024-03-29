package org.example.examjee.dao;

import org.example.examjee.model.Employe;

import java.util.List;

public interface EmployeDao {
    List<Employe> findAll();

    int save(Employe employe);

    boolean delete(Employe employee);

    boolean isEmailUnique(String email);
}
