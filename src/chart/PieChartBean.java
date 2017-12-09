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



public class PieChartBean { // stats.jsp (통계조회) 에서 사용할 원그래프관련 클래스

	public static void main(String arg[]){ //pieChart 생성  JFreeChart를 사용 사용을위해선 web-inf에 lib에 JFreeChart 홈페이지에서 받은 lib추가필요

		PieChartBean bcb = new PieChartBean();

		JFreeChart chart = bcb.getPieChart();

		ChartFrame frame1=new ChartFrame("Pie Chart",chart);

		 
		frame1.setVisible(true);
		frame1.setSize(400, 300);
	}

	public JFreeChart getPieChart() { //연령대별 회원수에대한 그래프를 불러옴
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
	    String[] ageList = new String[]{"10대","20대","30대","40~50대","60대이상"};
        for(int count : userList){
            if(count != 0){
            	dataset.setValue(""+ageList[i] + "(" + userList[i] + "명)", count);
            	System.out.print("\n : " + count);
            }
            i++;
        }

		JFreeChart chart = ChartFactory.createPieChart3D("GETTO의 연령대별 회원 수", dataset, true,true, false);
		chart.setBackgroundPaint(Color.white);	
		return chart;

	}
	
	public JFreeChart getPieChartByBmi(){ //연령대별 평균 Bmi에 대한 그래프를 불러옴
		UserManager manager = UserManager.getInstance();
		double[] bmiByAge = null;

		try {
			bmiByAge = manager.getBmiByAge(); //연령대별 평균 bmi 담은 double 배열
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    int i = 0;
	    String[] ageList = new String[]{"10대","20대","30대","40~50대","60대이상"};
        for(double bmi : bmiByAge){
            if(bmi != 0)
            	dataset.setValue(""+ageList[i] + "(" + Math.round(bmi) + "%)", bmi);
            
            i++;
        }

		JFreeChart chart = ChartFactory.createPieChart3D("GETTO의 연령대별 BMI", dataset, true,true, false);
		chart.setBackgroundPaint(Color.white);	
		
		return chart;
	}
	
	public void changeFont(JFreeChart chart){ //한글처리를 위한 함수, 원그래프 폰트변경은 여기에서
		chart.getTitle().setFont(new Font("고딕", Font.BOLD, 20));
		chart.getLegend().setItemFont(new Font("고딕", Font.PLAIN, 10));
		
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("고딕", Font.PLAIN, 12));

	

 

		
	}

}