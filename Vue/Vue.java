package Vue;
import DAO.*;
import Model.Employee;
import Model.Holiday;

import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Vue extends JFrame {
    JTabbedPane tabbedPane = new JTabbedPane();
    public JPanel p0 ;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    public JPanel getP3(){
        return p3 ;
    }
    public Object[][] data ;
    public JTable congeTable ;
    private JPanel p4;
    private JComboBox<String> postesComboBox ;
    private JComboBox<String> roleComboBox ;
    private JButton ajouter ;
    private JButton modifier;
    private JButton supprimer ;
    private JButton afficher ;
    public JButton ajouterButton = new JButton("Ajouter");
    public JButton modifierButton = new JButton("Modifier");
    public JButton supprimerButton = new JButton("Supprimer");
    public JSpinner startDateSpinner ;
    public JSpinner endDateSpinner ;
    public  JComboBox<String> employeeComboBox ;
    private JTextField tel;
    private JTextField sal  ;
    private JTextField nom ;
    private JTextField prenom ;
    private JTextField email  ;
    private  JList<String> employeeList ;
    public  JList<String> getEmployeeList(){
        return employeeList ;
    };
    public  void setEmployeeList(JList<String> employeeList){
        this.employeeList =employeeList   ;
    } ;
    public JComboBox<String> typeCongeComboBox ;
    public Vue() {
        setTitle("App");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EmployeeDAOImpl eImp = new EmployeeDAOImpl();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p0 = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();





        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Nom de l'Employé:"));
        List<String> employeeNames = new ArrayList<>();
        for (Employee employee : eImp.findAll()) {
            employeeNames.add(employee.getNom() + ' ' +employee.getPrenom());
        }
        employeeComboBox = new JComboBox<>(employeeNames.toArray(new String[0]));
        formPanel.add(employeeComboBox);
        formPanel.add(new JLabel("Type de Congé:"));
        List<Holiday.typeHoliday> conge = GenericDAOImpl.findAllTypes();
        String[] typesConge = conge.stream()
                .map(Enum::name)
                .toArray(String[]::new);
        typeCongeComboBox = new JComboBox<>(typesConge);
        formPanel.add(typeCongeComboBox);
        formPanel.add(new JLabel("Date de Début:"));
         startDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor startDateEditor = new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd");
        startDateSpinner.setEditor(startDateEditor);
        formPanel.add(startDateSpinner);
        formPanel.add(new JLabel("Date de Fin:"));
         endDateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor endDateEditor = new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd");
        endDateSpinner.setEditor(endDateEditor);
        formPanel.add(endDateSpinner);
        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(ajouterButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"ID", "Employé", "Date de Début", "Date de Fin", "Type"};
        Object[][] data = {
                {"1", "Employé 1", "2024-04-01", "2024-04-05", "Congé payé"},
                {"2", "Employé 2", "2024-04-05", "2024-04-10", "Congé maladie"},
                {"3", "Employé 3", "2024-04-10", "2024-04-15", "Congé sans solde"}
        };
        congeTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(congeTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panel2.add(formPanel, BorderLayout.NORTH);
        panel2.add(tablePanel, BorderLayout.CENTER);
        panel2.add(buttonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Congés", panel2);





        setLayout(new FlowLayout());

        // Add tabs to the JTabbedPane
        tabbedPane.addTab("Tab 1", p1);
        tabbedPane.addTab("Tab 2", panel2);





        p1.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(7,2,10,10));

        p3.setLayout(new GridLayout());
        p4.setLayout(new GridLayout());
        add(tabbedPane);
        p1.add(p2 , BorderLayout.NORTH);
        p1.add(p3 , BorderLayout.CENTER);
        p1.add(p4 , BorderLayout.SOUTH);

        p2.add(new JLabel("Nom:"));

        nom =  new JTextField();p2.add(nom);
        p2.add(new JLabel("Prenom:"));
        prenom =  new JTextField();p2.add(prenom);
        p2.add(new JLabel("Email:"));
        email =  new JTextField();p2.add(email);
        p2.add(new JLabel("Telephone:"));
        tel = new JTextField();p2.add(tel);
        p2.add(new JLabel("Salaire:"));
        sal =  new JTextField();p2.add(sal);

        p2.add(new JLabel("Role:"));
        List<Employee.Role> roles = eImp.findAllRoles() ;
        String[] roleStrings = roles.stream()
                .map(Enum::name)
                .toArray(String[]::new);
        roleComboBox = new JComboBox<String>(roleStrings);
        p2.add(roleComboBox);

        p2.add(new JLabel("Poste:"));
        List<Employee.Poste> postes = eImp.findAllPostes() ;
        String[] postesStrings = postes.stream()
                .map(Enum::name)
                .toArray(String[]::new);
        postesComboBox = new JComboBox<String>(postesStrings);
        p2.add(postesComboBox);



        //P3 Container
        List<Employee> all_e = eImp.findAll();
        List<String> allString = new ArrayList<String>();
        allString.add(String.format(
                "| %-5s | %-15s | %-15s | %-15s | %-25s | %-10s | %-20s | %-10s |",
                "ID", "Nom", "Prenom", "Tel", "Email", "Salaire", "Poste", "Role"
        ));
        for (Employee e : all_e){
            allString.add(e.toString());
        }
        String[] allStringArray = allString.toArray(new String[0]);

        employeeList = new JList<String>(allStringArray);
        p3.add(employeeList);


        //p4 Container

        p4.setLayout(new FlowLayout());
        this.ajouter = new JButton("Ajouter");
        this.modifier = new JButton("Modifier");
        this.supprimer = new JButton("Supprimer");
        this.afficher = new JButton("Afficher");
        p4.add(this.ajouter);p4.add(this.modifier);p4.add(this.supprimer);p4.add(this.afficher);
        setVisible(true);


    }

    public JComboBox<String> getPostesComboBox() {
        return postesComboBox;
    }

    public JComboBox<String> getRoleComboBox() {
        return roleComboBox;
    }

    public JButton getAjouter() {
        return ajouter;
    }

    public JButton getModifier() {
        return modifier;
    }

    public JButton getSupprimer() {
        return supprimer;
    }

    public JButton getAfficher() {
        return afficher;
    }

    public JTextField getTel() {
        return tel;
    }

    public JTextField getSal() {
        return sal;
    }

    public JTextField getNom() {
        return nom;
    }

    public JTextField getPrenom() {
        return prenom;
    }

    public JTextField getEmail() {
        return email;
    }

    public JPanel getP1() {
        return p1;
    }

    JToggleButton btnEmployes = new JToggleButton("Employés");
    JToggleButton btnConges = new JToggleButton("Congés");
    ButtonGroup group = new ButtonGroup();


}
