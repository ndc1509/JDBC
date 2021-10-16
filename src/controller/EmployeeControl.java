package controller;

import java.sql.SQLException;

import controller.dao.DAOEmployee;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import model.Employee;
import view.View;

public class EmployeeControl {
	View view;
	DAOEmployee dao;
	Employee[] emlopyees;
        
	public EmployeeControl(View view) {
		try {
			dao = new DAOEmployee(ConnectionUtils.getMyConnection());
			this.view = view;     
                        displayAll();
			view.addEmpListener(new AddEmpListener());
                        view.editEmpListener(new EditEmpListener());
                        view.updateEmpListener(new UpdateEmpListener());
                        view.deleteEmpListener(new DeleteEmpListener());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.dao.closeConnection();
			System.exit(0);
		}
	}
      
        class AddEmpListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                Employee emp = view.getEmployee();
                int rowCout = dao.insert(emp);
                view.showMessage("Them thanh cong");
                displayAll();
            }            
        }
        
        class UpdateEmpListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                Employee emp = view.getUpdatedEmployee();
               
                int rowCout = dao.update(emp);
                view.showMessage("Cap nhat thanh cong");
                displayAll();
            }            
        }
        
        class EditEmpListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {               
                view.showEditEmployee();
            }            
        }

        class DeleteEmpListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {               
                int emp_id = view.delEmployee();
                dao.delete(emp_id);
                view.showMessage("Xoa thanh cong");
                displayAll();
            }            
        }
         
	public void displayAll(){
            List<Employee> empList =  Arrays.asList(dao.selectAll());
            view.showListEmployee(empList);
	}
	private Employee[] displayByName(String name){
		return this.dao.selectByName(name);
	}
	public void exit(){
		this.dao.closeConnection();
	//	this.view.exit();
	}
}
