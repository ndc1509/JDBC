package controller.dao;

import controller.dao.IDAO;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import model.SalaryGrade;

public class DAOSalaryGrade extends IDAO<SalaryGrade> {
	public DAOSalaryGrade(Connection conn) {
		this.conn = conn;
		try {
			this.statement = this.conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public SalaryGrade[] selectAll() {
		Vector<SalaryGrade> Sg = new Vector<SalaryGrade>();
		SalaryGrade[] result;
		try {
			String sql = "Select * from salary_grade";

			rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				SalaryGrade s = new SalaryGrade(
						rs.getInt(1), 
						rs.getFloat(2),
						rs.getFloat(3));
				Sg.add(s);

				i++;
			}
			result = new SalaryGrade[i];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return Sg.toArray(result);
	}

	public SalaryGrade[] selectByGrade(int grade) {
		Vector<SalaryGrade> Sg = new Vector<SalaryGrade>();
		SalaryGrade[] result;
		try {
			String sql = "Select * from salary_grade where GRADE='" + grade+"'" ;

			rs = statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				SalaryGrade s = new SalaryGrade(
						rs.getInt(1), 
						rs.getFloat(2),
						rs.getFloat(3));
				Sg.add(s);

				i++;
			}
			result = new SalaryGrade[i];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return Sg.toArray(result);
	}

	@Override
	public int insert(SalaryGrade s) {
		String sql = "INSERT INTO salary_grade ("+
				"GRADE,"+
				"HIGH_SALARY,"+
				"LOW_SALARY)"+
				"VALUES (?,?,?)";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
			this.preStatement.setInt(1, s.getGrade());
			this.preStatement.setFloat(2, s.getHighSalary());
			this.preStatement.setFloat(3, s.getLowSalary());
			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
		
	}

	@Override
	public int update(SalaryGrade s) {
		String sql = "UPDATE salary_grade set "+				
				"HIGH_SALARY = ?,"+
				"LOW_SALARY = ?" +
                                "where GRADE = ?";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
			this.preStatement.setInt(3, s.getGrade());
			this.preStatement.setFloat(1, s.getHighSalary());
			this.preStatement.setFloat(2, s.getLowSalary());
			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
	}
        
        @Override
        public int delete(int grade){
            String sql = "DELETE FROM salary_grade WHERE GRADE = ? ";
            try{
                this.preStatement = this.conn.prepareStatement(sql);
                this.preStatement.setInt(1, grade);
                int rowCount = this.preStatement.executeUpdate();
                return rowCount;
            } catch(SQLException el){
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
    public SalaryGrade[] selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

}
