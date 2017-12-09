package chart;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.chart.plot.*;

import user.UserDTO;
import user.UserManager;



public class PieChartBean { // stats.jsp (�����ȸ) ���� ����� ���׷������� Ŭ����

	public static void main(String arg[]){ //pieChart ����  JFreeChart�� ��� ��������ؼ� web-inf�� lib�� JFreeChart Ȩ���������� ���� lib�߰��ʿ�

		PieChartBean bcb = new PieChartBean();

		JFreeChart chart = bcb.getPieChart();

		ChartFrame frame1=new ChartFrame("Pie Chart",chart);

		 
		frame1.setVisible(true);
		frame1.setSize(400, 300);
	}

	public JFreeChart getPieChart() { //���ɴ뺰 ȸ���������� �׷����� �ҷ���
		UserManager manager = UserManager.getInstance();

		System.out.print("\ntset\n");
		int[] userList = new int[]{0};
		try {
			userList = manager.countUser();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  DefaultPieDataset dataset = new DefaultPieDataset();
	    int i = 0;
	    String[] ageList = new String[]{"10��","20��","30��","40~50��","60���̻�"};
        for(int count : userList){
            if(count != 0){
            	dataset.setValue(""+ageList[i] + "(" + userList[i] + "��)", count);
            	System.out.print("\n : " + count);
            }
            i++;
        }

		JFreeChart chart = ChartFactory.createPieChart3D("GETTO�� ���ɴ뺰 ȸ�� ��", dataset, true,true, false);
		chart.setBackgroundPaint(Color.white);	
		return chart;

	}
	
	public JFreeChart getPieChartByBmi(){ //���ɴ뺰 ��� Bmi�� ���� �׷����� �ҷ���
		UserManager manager = UserManager.getInstance();
		double[] bmiByAge = null;

		try {
			bmiByAge = manager.getBmiByAge(); //���ɴ뺰 ��� bmi ���� double �迭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    int i = 0;
	    String[] ageList = new String[]{"10��","20��","30��","40~50��","60���̻�"};
        for(double bmi : bmiByAge){
            if(bmi != 0)
            	dataset.setValue(""+ageList[i] + "(" + Math.round(bmi) + "%)", bmi);
            
            i++;
        }

		JFreeChart chart = ChartFactory.createPieChart3D("GETTO�� ���ɴ뺰 BMI", dataset, true,true, false);
		chart.setBackgroundPaint(Color.white);	
		
		return chart;
	}
	
	public void changeFont(JFreeChart chart){ //�ѱ�ó���� ���� �Լ�, ���׷��� ��Ʈ������ ���⿡��
		chart.getTitle().setFont(new Font("���", Font.BOLD, 20));
		chart.getLegend().setItemFont(new Font("���", Font.PLAIN, 10));
		
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("���", Font.PLAIN, 12));

	

 

		
	}

}