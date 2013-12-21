package iReport;
/**
 *Conn.java		萬用SQL連結
 *@author Eric Chen.,CPY
 *Updated on 2011-10-19 	可連結多個資料庫功能
 *Updated on 2011-11-07		不回傳資料區塊
 *Updated on 2012-06-20		關閉連結(要在各程式中關閉否則不會傳值)
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
			JOptionPane.showMessageDialog(Frame,"找不到連線類別檔案\n"+cnfe.toString(),"訊息視窗",JOptionPane.WARNING_MESSAGE);
		}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(Frame,"系統設定錯誤\n"+sqle.toString(),"訊息視窗",JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(Frame,"找不到連線類別檔案\n"+cnfe.toString(),"訊息視窗",JOptionPane.WARNING_MESSAGE);
			}
		catch(SQLException sqle){
			JOptionPane.showMessageDialog(Frame,"系統設定錯誤\n"+sqle.toString(),"訊息視窗",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	//完整使用完資料庫後,記得要關閉所有Object 
	//否則在等待Timeout時,可能會有Connection poor的狀況
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

