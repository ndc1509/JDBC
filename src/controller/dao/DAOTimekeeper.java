/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import model.Employee;
import model.Timekeeper;

/**
 *
 * @author Cuong
 */
public class DAOTimekeeper extends IDAO<Timekeeper>{
    public DAOTimekeeper(Connection conn) {
		this.conn = conn;
		try {
			this.statement = this.conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Timekeeper[] selectAll() {
		Vector<Timekeeper> t = new Vector<Timekeeper>();
		Timekeeper[] result;
		try {
			String sql = "Select * from Timekeeper";

			rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				Timekeeper e = new Timekeeper(
						rs.getString(1), 
						rs.getDate(2),
						rs.getString(3),
						rs.getInt(4)
						);
				t.add(e);

				i++;
			}
			result = new Timekeeper[i];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return t.toArray(result);
	}

	
	@Override
	public int insert(Timekeeper t) {
		String sql = "INSERT INTO Timekeeper ("+
				"Timekeeper_id,"+
				"Date_Time,"+
				"In_Out,"+
				"EMP_ID)"+				
				"VALUES (?,?,?,?)";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
					
			this.preStatement.setString(1, t.getTimekeeper_Id());
			this.preStatement.setDate(2, new java.sql.Date(t.getDate_Time().getTime()));
			this.preStatement.setString(3, t.getIn_Out());
			this.preStatement.setInt(4, t.getEmpId());
			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
		
	}	
        
    @Override
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Timekeeper[] selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Timekeeper object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
