
import controller.EmployeeControl;
import java.awt.EventQueue;
import view.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cuong
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                View view = new View();
                view.setVisible(true);
                EmployeeControl controller = new EmployeeControl(view);
            }
        });
        
    }
}