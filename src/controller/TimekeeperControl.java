/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.dao.DAOEmployee;
import controller.dao.DAOTimekeeper;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import model.Employee;
import model.Timekeeper;
import view.View;

/**
 *
 * @author Cuong
 */
public class TimekeeperControl {
        View view;
	DAOTimekeeper dao;
        DAOEmployee daoEmp;
	Timekeeper[] timekeepers;
        
	public TimekeeperControl(View view) {
		try {
			dao = new DAOTimekeeper(ConnectionUtils.getMyConnection());
                        daoEmp = new DAOEmployee(ConnectionUtils.getMyConnection());
			this.view = view;     
                        displayAll();
			view.addTKListener(new CheckInOutListener());
                        
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.dao.closeConnection();
			System.exit(0);
		}
	}
      
        class CheckInOutListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                Timekeeper tk = view.getTimekeeper();
                int rowCout = dao.insert(tk);
                view.showMessage("Check in/out thanh cong");
                displayAll();
            }            
        }
        
      
         
	public void displayAll(){
            List<Employee> empList = Arrays.asList(daoEmp.selectAll());
            List<Timekeeper> tkList =  Arrays.asList(dao.selectAll());
            view.showListTimekeeper(tkList);
            view.showListEmpID(empList);
	}
	
	public void exit(){
		this.dao.closeConnection();
	//	this.view.exit();
	}
}
