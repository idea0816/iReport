package iReport;
/**
 *Conn.java		�U��SQL�s��
 *@author Eric Chen.,CPY
 *Updated on 2011-10-19 	�i�s���h�Ӹ�Ʈw�\��
 *Updated on 2011-11-07		���^�Ǹ�ư϶�
 *Updated on 2012-06-20		�����s��(�n�b�U�{���������_�h���|�ǭ�)
 */

import java.sql.*;
import javax.swing.*;

public class Conn {
	JFrame Frame = new JFrame();
	String strDriver = "net.sourceforge.jtds.jdbc.Driver";
	public String dataBase;
	public String databaseName;
	String user = "sa";
	public String password;
	Connection MyConn;
	
	Statement statement;
	public String rs_title;
	public ResultSet rs;
	ResultSetMetaData rsmd;	
	
	public void Conn_SQL(){
		try{
			String strDB = "jdbc:jtds:sqlserver://"+dataBase+"/"+databaseName;
			Class.forName(strDriver);
			MyConn = DriverManager.getConnection(strDB,user,password);
			statement = MyConn.createStatement();			
			rs = statement.executeQuery(rs_title);
		}
		catch(ClassNotFoundException cnfe){
			JOptionPane.showMessageDialog(Frame,"�䤣��s�u���O�ɮ�\n"+cnfe.toString(),"�T������",JOptionPane.WARNING_MESSAGE);
		}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(Frame,"�t�γ]�w���~\n"+sqle.toString(),"�T������",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void Conn_Update(){
		try{
			String strDB = "jdbc:jtds:sqlserver://"+dataBase+"/"+databaseName;
			Class.forName(strDriver);
			MyConn = DriverManager.getConnection(strDB,user,password);
			statement = MyConn.createStatement();			
			statement.executeUpdate(rs_title);	
		}
		catch(ClassNotFoundException cnfe){
				JOptionPane.showMessageDialog(Frame,"�䤣��s�u���O�ɮ�\n"+cnfe.toString(),"�T������",JOptionPane.WARNING_MESSAGE);
			}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(Frame,"�t�γ]�w���~\n"+sqle.toString(),"�T������",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
	//�_�h�b����Timeout��,�i��|��Connection poor�����p
	/*
	private void Close(){
		try{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(statement != null){
				statement.close();
				statement = null;
			}
			
		}catch(Exception e){}
	}*/

}

