package org.example.examjee.service;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.example.examjee.dao.EmployeDao;
import org.example.examjee.dao.jpa.EmployeDaoImpl;
import org.example.examjee.model.Employe;
import org.example.examjee.model.Project;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeService {
        EmployeDao employeDao;


        /**
         * Constructor to initialize the EmployeeService with a default EmployeeDAO implementation.
         */
        public EmployeService(){
            //this.employeeDAO = new EmployeeDAOImpl();
            this.employeDao = new EmployeDaoImpl();
        }


        public List<Employe> findAllService(){
            List<Employe> employes = employeDao.findAll();
            return employes;
        }
    public void addService(Employe employee) {
        boolean isUnique = employeDao.isEmailUnique(employee.getEmail());

        if (isUnique) {
            int result = employeDao.save(employee);
            if (result > 0) {
                addSuccessMessage("Employee has been saved successfully");
            } else {
                addErrorMessage("Employee add failed");
            }
        } else {
            addWarningMessage("Email already exists");
        }
    }

    public void deleteService(Employe employe) {
        boolean result = employeDao.delete(employe);

        if (result) {
            addSuccessMessage("The employee has been deleted successfully");
        } else {
            addErrorMessage("Delete employee failed");
        }
    }

    private void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    public void affectService(Employe employe, Project project, double implication) {
        boolean result = employeDao.delete(employe);

        if (result) {
            addSuccessMessage("The employee has been affected deleted successfully");
        } else {
            addErrorMessage("Affectation failed");
        }
    }

    /**
     * Adds a warning message to the FacesContext.
     * @param message The warning message to be added.
     */
    private void addWarningMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    /**
     * Adds a success message to the FacesContext.
     * @param message The success message to be added.
     */
    private void addSuccessMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
