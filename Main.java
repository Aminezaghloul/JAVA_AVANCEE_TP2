import Controller.HolidayController;
import DAO.EmployeeDAOImpl;
import DAO.GenericDAOImpl;
import Model.EmployeeModel;
import Model.HolidayModel;
import Vue.Vue;
import Controller.EmployeeController;

public class Main {
    public static void main(String[] args) {
        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        EmployeeModel model = new EmployeeModel(dao);
        Vue view = new Vue();
        HolidayModel hm = new HolidayModel(new GenericDAOImpl()) ;
        System.out.println(model.getAllEmployees());

        new EmployeeController(model, view);
        new HolidayController(hm ,view ,model);
    }
}
