package controller;

import java.sql.SQLException;

import controller.dao.DAOSalaryGrade;
import controller.utils.ConnectionUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import model.SalaryGrade;
import view.View;

public class SalaryGradeControl {
	View view;
	DAOSalaryGrade dao;
	SalaryGrade[] salaryGrades;
        
	public SalaryGradeControl(View view) {
		try {
			dao = new DAOSalaryGrade(ConnectionUtils.getMyConnection());
			this.view = view;     
                        displayAll();
                        view.addSgListener(new AddSgListener());
                        view.editSgListener(new EditSgListener());
                        view.updateSgListener(new UpdateSgListener());
                        view.deleteSgListener(new DeleteSgListener());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.dao.closeConnection();
			System.exit(0);
		}
	}
      
        class AddSgListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                SalaryGrade s = view.getSalaryGrade();
                int rowCout = dao.insert(s);
                view.showMessage("Them thanh cong");
                displayAll();
            }            
        }
        
        class UpdateSgListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
                SalaryGrade emp = view.getUpdatedSalaryGrade();
                int rowCout = dao.update(emp);
                view.showMessage("Cap nhat thanh cong");
                displayAll();
            }            
        }
        
        class EditSgListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {               
                view.showEditSalaryGrade();
            }            
        }

        class DeleteSgListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {               
                int grade = view.delSalaryGrade();
                dao.delete(grade);
                view.showMessage("Xoa thanh cong");
                displayAll();
            }            
        }
         
	public void displayAll(){
            List<SalaryGrade> empList =  Arrays.asList(dao.selectAll());
            view.showListSalaryGrade(empList);
	}
	private SalaryGrade[] displayByGrade(int grade){
		return this.dao.selectByGrade(grade);
	}
	public void exit(){
		this.dao.closeConnection();
	//	this.view.exit();
	}
}
