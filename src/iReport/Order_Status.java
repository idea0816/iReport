package iReport;

/**
 *Order_Status.java		訂單產能狀況表
 *@author Eric Chen.,CPY
 *Updated on 2013-01-04		訂單數量加入條件如下:
 *							DDZL.DDLB = 'N'		訂單類別為正式訂單
 *							DDZL.DDZT = 'Y'		訂單狀態為正常
 *							XXZL.lbdh = 'O'		加入判斷模具類別
 */

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;
			
@SuppressWarnings("unused")
public class Order_Status {
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
    	DecimalFormat df = new DecimalFormat("###,###,###,###.##");//數字百分位設定
    	
    	//iReport Setting
    	String fullPath = "Order_Status.jasper";
    	Map m = new HashMap();
    	
    	//DataBase Setting
    	Conn conn = new Conn();
    	conn.databaseName = "LIY_DDS";
    	conn.password = "shinimex25620299";
    	
    	//Count
    	int i;
    	double sum,sum1 = 0,sum2 = 0;
    	    	
        try {
        	//Shinimex I
        	conn.dataBase = "192.168.2.3";
        	
        	//1-1
        	conn.rs_title = "SELECT SUM(DDZL.Pairs) FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '2014' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh = 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
        	System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	while(conn.rs.next()){
        		String para = "1-1-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum1 = sum1 + conn.rs.getDouble(1);
        		i++;
			}
        	conn.rs.close();
        	
			//1-2
			conn.rs_title = "SELECT SUM(Pairs) FROM DDZL WHERE DATEPART(YEAR, DDJQ) = '2014' GROUP BY DATENAME(MONTH, DDJQ),DATEPART(MONTH, DDJQ) ORDER BY DATEPART(MONTH, DDJQ)";
			System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	sum = 0;
        	while(conn.rs.next()){
        		String para = "1-2-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum = sum + conn.rs.getDouble(1);
        		i++;
			}
        	String sum_2 = String.valueOf(df.format(sum));
        	m.put("1-2-sum", sum_2);
			conn.rs.close();
			
			//1-3
			conn.rs_title = "SELECT SUM(Qty) FROM DDS WHERE DATEPART(YEAR, CHRQ) = '2014' GROUP BY DATENAME(MONTH, CHRQ),DATEPART(MONTH, CHRQ) ORDER BY DATEPART(MONTH, CHRQ)";
			System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	sum = 0;
        	while(conn.rs.next()){
        		String para = "1-3-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum = sum + conn.rs.getDouble(1);
        		i++;
			}
        	String sum_3 = String.valueOf(df.format(sum));
        	m.put("1-3-sum", sum_3);
			conn.rs.close();
			
			//1-4
        	conn.rs_title = "SELECT SUM(DDZL.Pairs) FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '2014' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh != 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
        	System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	//sum = 0;
        	while(conn.rs.next()){
        		String para = "1-4-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum1 = sum1 + conn.rs.getDouble(1);
        		i++;
			}
        	String sum_1 = String.valueOf(df.format(sum1));
        	m.put("1-1-sum", sum_1);
			conn.rs.close();
        	
			//Shinimex II
			conn.password = "shinimex25620299";
        	conn.dataBase = "192.168.3.3";
        	//2-1
        	conn.rs_title = "SELECT SUM(DDZL.Pairs) FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '2014' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh = 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
        	System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	while(conn.rs.next()){
        		String para = "2-1-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum2 = sum2 + conn.rs.getDouble(1);
        		i++;
			}
        	conn.rs.close();
        	
			//2-2
			conn.rs_title = "SELECT SUM(Pairs) FROM DDZL WHERE DATEPART(YEAR, DDJQ) = '2014' GROUP BY DATENAME(MONTH, DDJQ),DATEPART(MONTH, DDJQ) ORDER BY DATEPART(MONTH, DDJQ)";
			System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	sum = 0;
        	while(conn.rs.next()){
        		String para = "2-2-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum = sum + conn.rs.getDouble(1);
        		i++;
			}
        	String sum_5 = String.valueOf(df.format(sum));
        	m.put("2-2-sum", sum_5);
			conn.rs.close();
			
			//2-3
			conn.rs_title = "SELECT SUM(Qty) FROM DDS WHERE DATEPART(YEAR, CHRQ) = '2014' GROUP BY DATENAME(MONTH, CHRQ),DATEPART(MONTH, CHRQ) ORDER BY DATEPART(MONTH, CHRQ)";
			System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	sum = 0;
        	while(conn.rs.next()){
        		String para = "2-3-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum = sum + conn.rs.getDouble(1);
        		i++;
			}
        	String sum_6 = String.valueOf(df.format(sum));
        	m.put("2-3-sum", sum_6);
			conn.rs.close();
			
			//2-4
        	conn.rs_title = "SELECT SUM(DDZL.Pairs) FROM DDZL DDZL,XXZL XXZL WHERE DATEPART(YEAR, DDZL.DDRQ) = '2014' AND DDZL.DDLB = 'N' AND DDZL.DDZT = 'Y' AND XXZL.lbdh != 'O' AND DDZL.XieXing = XXZL.XieXing AND DDZL.SheHao = XXZL.SheHao GROUP BY DATENAME(MONTH, DDZL.DDRQ),DATEPART(MONTH, DDZL.DDRQ) ORDER BY DATEPART(MONTH, DDZL.DDRQ)";
        	System.out.println(conn.rs_title);
        	conn.Conn_SQL();
        	i = 1;
        	//sum = 0;
        	while(conn.rs.next()){
        		String para = "2-4-"+i;
        		m.put(para, conn.rs.getString(1));
        		sum2 = sum2 + conn.rs.getDouble(1);
        		i++;
			}
        	String sum_4 = String.valueOf(df.format(sum2));
        	m.put("2-1-sum", sum_4);
			conn.rs.close();
			
			//iReport
        	JasperPrint jasperPrint = JasperFillManager.fillReport(fullPath, m, new JREmptyDataSource());
			//JasperPrint jasperPrint = JasperFillManager.fillReport(fullPath, m);
        	//JasperExportManager.exportReportToPdfFile(jasperPrint,"d:/iReport/test.pdf");
        	JasperViewer.viewReport(jasperPrint);
            
        } catch (JRException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}