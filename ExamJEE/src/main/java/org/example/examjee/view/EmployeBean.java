package org.example.examjee.view;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import jakarta.faces.context.FacesContext;
import org.example.examjee.model.Employe;
import org.example.examjee.model.Project;
import org.example.examjee.service.EmployeService;
import java.util.List;
import java.util.ResourceBundle;


@ManagedBean(name="employeBean")
@SessionScoped
public class EmployeBean {

    // Declaration of class attributes
    private List<Employe> employes ;
    private EmployeService employeeService;
    //private HtmlCommandButton deletebutton;


    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public EmployeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeService employeeService) {
        this.employeeService = employeeService;
    }

    // Constructor of the class
    public EmployeBean(){
        employeeService = new EmployeService();
        employes = employeeService.findAllService();
        //deletebutton = new HtmlCommandButton();

        //loadEmployees();
    }



    public void add(Employe employe) {
        employeeService.addService(employe);
        employes.add(employe);
    }

    public void delete(Employe employe){
        employeeService.deleteService(employe);
        employes.remove(employe);
    }

    public void affectEmploye(Employe employe, Project project, double implication) {
        employeeService.affectService(employe, project, implication);
    }


}
