package project;

import Database.connection;
import Models.employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.*;

public class GUI {
    JFrame welcome;
    JFrame Firstform;
    JRadioButton insert;
    JRadioButton update;
    JRadioButton delete;
    JRadioButton search;
    JRadioButton display;
    JButton okay;
    JFrame employeeForm;
    JTextField id;
    JTextField birthday;
    JTextField firstname;
    JTextField lastname;
    JTextField hiredate;
    JTextField deptId;
    JButton done;
    JFrame managerForm;
    JTextField m_id;
    JTextField m_birthday;
    JTextField m_firstname;
    JTextField m_lastname;
    JTextField m_hiredate;
    JTextField m_deptId;
    JButton m_done;
    JFrame departmentsForm;
    JTextField d_id;
    JTextField d_name;
    JButton d_done;
    public GUI() {
      welcome = new JFrame("Welcome");
     welcome.setLayout(new FlowLayout());
     welcome.add(new JLabel("Hello!" +
             "Do you want to alter in:"));
    welcome.setVisible(true);
   // welcome.pack();
    welcome.setSize(500,400);
    welcome.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     JRadioButton employee;
     JRadioButton manager;
     JRadioButton departments;
     JButton Continue;
     ButtonGroup G1;
     employee = new JRadioButton();

     manager = new JRadioButton();
     departments = new JRadioButton();
     Continue = new JButton("Continue");
     G1 = new ButtonGroup();


     employee.setText("Employees");
     manager.setText("Managers");
     departments.setText("Departments");
     welcome.add(employee);
     welcome.add(manager);
     welcome.add(departments);
     welcome.add(Continue);
     G1.add(employee);
     G1.add(manager);
     G1.add(departments);

     // Listener to continue button
     Continue.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
             String qual = " ";
             if (employee.isSelected()) {
                 welcome.dispose();
                 FirstForm();
                 okay.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         Firstform.dispose();
                         if(insert.isSelected())
                         {
                             employee_form();
                             done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the insert employee
                                    Connection conn= connection.open();
                                    try{
                                        CallableStatement cs=conn.prepareCall("{call insert_operation(?,?,?,?,?,?)}");
                                        cs.setInt(1, Integer.parseInt(id.getText()));
                                        cs.setString(2,birthday.getText());
                                        cs.setString(3,firstname.getText());
                                        cs.setString(4,lastname.getText());
                                        cs.setString(5,hiredate.getText());
                                        cs.setInt(6, Integer.parseInt(deptId.getText()));
                                        cs.execute();
                                        JOptionPane.showMessageDialog(null,"inserted successfully");
                                    }
                                    catch(Exception ex)
                                    {
                                       JOptionPane.showMessageDialog(null,ex.getMessage());
                                    }
                                 }
                             });
                         }
                        else if(update.isSelected())
                         {
                             employee_form();
                             done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the update employee
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call update_operation(?,?,?,?,?,?)}");
                                         cs.setInt(1, Integer.parseInt(id.getText()));
                                         cs.setString(2,birthday.getText());
                                         cs.setString(3,firstname.getText());
                                         cs.setString(4,lastname.getText());
                                         cs.setString(5,hiredate.getText());
                                         cs.setInt(6, Integer.parseInt(deptId.getText()));
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"updated successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }
                                 }
                             });
                         }
                         if(delete.isSelected())
                         {
                             employee_form();
                             JOptionPane.showMessageDialog(null,"Deleting operation is by ID so,make sure it is correct");
                             firstname.setEnabled(false);
                             lastname.setEnabled(false);
                             birthday.setEnabled(false);
                             hiredate.setEnabled(false);
                             deptId.setEnabled(false);
                             done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the delete employee
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call delete_operation(?)}");
                                         cs.setInt(1, Integer.parseInt(id.getText()));
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"Deleted successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }

                                 }
                             });
                         }
                         if(search.isSelected())
                         {
                             Search_form();
                             done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the search employee
                                 }
                             });
                         }
                         if(display.isSelected())
                         {
                             done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the display employee
                                 }
                             });
                         }
                     }
                 });
             } else if (manager.isSelected()) {
                welcome.dispose();
                 FirstForm();
                 okay.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         Firstform.dispose();
                         if(insert.isSelected())
                         {
                             Managers_form();
                             m_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the insert manager
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call insert_managers_operation(?,?,?,?,?,?)}");
                                         cs.setInt(1, Integer.parseInt(m_id.getText()));
                                         cs.setString(2,m_birthday.getText());
                                         cs.setString(3,m_firstname.getText());
                                         cs.setString(4,m_lastname.getText());
                                         cs.setString(5,m_hiredate.getText());
                                         cs.setInt(6, Integer.parseInt(m_deptId.getText()));
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"inserted successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }
                                 }
                             });
                         }
                         else if(update.isSelected())
                         {
                             Managers_form();
                             m_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the update manager
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call update_managers__operation(?,?,?,?,?,?)}");
                                         cs.setInt(1, Integer.parseInt(m_id.getText()));
                                         cs.setString(2,m_birthday.getText());
                                         cs.setString(3,m_firstname.getText());
                                         cs.setString(4,m_lastname.getText());
                                         cs.setString(5,m_hiredate.getText());
                                         cs.setInt(6, Integer.parseInt(m_deptId.getText()));
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"Updated successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }
                                 }
                             });
                         }
                         if(delete.isSelected())
                         {
                             Managers_form();
                             JOptionPane.showMessageDialog(null,"Deleting operation is by ID so,make sure it is correct");
                             m_firstname.setEnabled(false);
                             m_lastname.setEnabled(false);
                             m_birthday.setEnabled(false);
                             m_hiredate.setEnabled(false);
                             m_deptId.setEnabled(false);
                             m_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the delete manager
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call delete_managers_operation(?)}");
                                         cs.setInt(1, Integer.parseInt(m_id.getText()));
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"Deleted successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }
                                 }
                             });
                         }
                         if(search.isSelected())
                         {
                             Search_form();
                             m_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the search manager
                                 }
                             });
                         }
                         if(display.isSelected())
                         {
                             m_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the display manager
                                 }
                             });
                         }
                     }
                 });

             } else if (departments.isSelected()) {
                 welcome.dispose();
                 FirstForm();
                 okay.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         Firstform.dispose();
                         if(insert.isSelected())
                         {
                             departments_form();
                             d_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the insert department
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call insert_departments_operation(?,?)}");
                                         cs.setInt(1, Integer.parseInt(d_id.getText()));
                                         cs.setString(2,d_name.getText());
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"Inserted successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }
                                 }
                             });
                         }
                         else if(update.isSelected())
                         {
                             departments_form();
                             d_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the update department
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call update_departments_operation(?,?)}");
                                         cs.setInt(1, Integer.parseInt(d_id.getText()));
                                         cs.setString(2,d_name.getText());
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"Updated successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }
                                 }
                             });
                         }
                         if(delete.isSelected())
                         {
                             departments_form();
                             JOptionPane.showMessageDialog(null,"Deleting operation is by ID so,make sure it is correct");
                             d_name.setEnabled(false);
                             d_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the delete department
                                     Connection conn= connection.open();
                                     try{
                                         CallableStatement cs=conn.prepareCall("{call delete_departments_operation(?)}");
                                         cs.setInt(1, Integer.parseInt(d_id.getText()));
                                         cs.execute();
                                         JOptionPane.showMessageDialog(null,"Deleted successfully");
                                     }
                                     catch(Exception ex)
                                     {
                                         JOptionPane.showMessageDialog(null,ex.getMessage());
                                     }

                                 }
                             });
                         }
                         if(search.isSelected())
                         {
                             Search_form();
                             d_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the search department
                                 }
                             });
                         }
                         if(display.isSelected())
                         {
                             d_done.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {

                                     //here execute the display department
                                 }
                             });
                         }
                     }
                 });
             } else {

                 qual = "NO Button selected";
                 JOptionPane.showMessageDialog(null, qual);
             }

         }
     });
 }
 public void FirstForm()
 {
      Firstform = new JFrame();
     Firstform.setLayout(new FlowLayout());
     Firstform.add(new JLabel("What do you want to do?"));
     Firstform.setVisible(true);
     Firstform.setSize(500,400);
     JButton back;
     ButtonGroup G2;
     insert = new JRadioButton();
     update = new JRadioButton();
     delete = new JRadioButton();
     search = new JRadioButton();
     display = new JRadioButton();
     okay = new JButton("Continue");
     G2 = new ButtonGroup();
     back=new JButton("Back");
     insert.setText("Insert");
     update.setText("Update");
     delete.setText("Delete");
     search.setText("Search");
     display.setText("Display");
     Firstform.add(insert);
     Firstform.add(update);
     Firstform.add(delete);
     Firstform.add(search);
     Firstform.add(display);
     Firstform.add(okay);
     Firstform.add(back);
     G2.add(insert);
     G2.add(update);
     G2.add(delete);
     G2.add(search);
     G2.add(display);
     back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             Firstform.dispose();
             welcome.show();
         }
     });
 }

 public void employee_form()
 {
     employeeForm =new JFrame("Employee Form");
     employeeForm.setLayout(new FlowLayout());
     employeeForm.setSize(500,500);
     employeeForm.setVisible(true);
     employeeForm.add(new JLabel("Employee's Id:"));
     id=new JTextField(10);
     employeeForm.add(id);
     employeeForm.add(new JLabel("Employee's birthdate:"));
     birthday=new JTextField(10);
     employeeForm.add(birthday);
     employeeForm.add(new JLabel("Employee's firstName:"));
     firstname=new JTextField(10);
     employeeForm.add(firstname);
     employeeForm.add(new JLabel("Employee's lastName:"));
     lastname=new JTextField(10);
     employeeForm.add(lastname);
     employeeForm.add(new JLabel("Employee's hiredate:"));
     hiredate=new JTextField(10);
     employeeForm.add(hiredate);
     employeeForm.add(new JLabel("Employee's department Id:"));
     deptId=new JTextField(10);
     employeeForm.add(deptId);
     done=new JButton("Done");
     employeeForm.add(done);
 }
 public void Search_form()
 {
     JFrame f2=new JFrame("searching");
     f2.setLayout(new FlowLayout());
     f2.setSize(500,200);
     JLabel lbl1=new JLabel("Enter FirstName");
     JTextField searchkey=new JTextField(10);
     JButton okay=new JButton("Okay");
     f2.add(lbl1);
     f2.add(searchkey);
     f2.add(okay);
     f2.setVisible(true);
 }
 public void Managers_form()
 {
     managerForm =new JFrame("Manager Form");
     managerForm.setLayout(new FlowLayout());
     managerForm.setSize(500,500);
     managerForm.setVisible(true);
     managerForm.add(new JLabel("Manager's Id:"));
     m_id=new JTextField(10);
     managerForm.add(m_id);
     managerForm.add(new JLabel("Manager's birthdate:"));
     m_birthday=new JTextField(10);
     managerForm.add(m_birthday);
     managerForm.add(new JLabel("Manager's firstName:"));
     m_firstname=new JTextField(10);
     managerForm.add(m_firstname);
     managerForm.add(new JLabel("Manager's lastName:"));
     m_lastname=new JTextField(10);
     managerForm.add(m_lastname);
     managerForm.add(new JLabel("Manager's hiredate:"));
     m_hiredate=new JTextField(10);
     managerForm.add(m_hiredate);
     managerForm.add(new JLabel("Manager's department Id:"));
     m_deptId=new JTextField(10);
     managerForm.add(m_deptId);
     m_done=new JButton("Done");
     managerForm.add(m_done);
 }
 public void departments_form()
 {
     departmentsForm =new JFrame("Departments Form");
     departmentsForm.setSize(300,300);
     departmentsForm.setLayout(new FlowLayout());
     departmentsForm.setVisible(true);
     departmentsForm.add(new JLabel("Department's id:"));
     d_id=new JTextField(10);
     departmentsForm.add(d_id);
     departmentsForm.add(new JLabel("Department's name:"));
     d_name=new JTextField(10);
     departmentsForm.add(d_name);
     d_done=new JButton("Done");
     departmentsForm.add(d_done);
 }
}
