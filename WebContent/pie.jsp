
<%@ page import = "org.jfree.chart.*" %>
<%@ page import ="chart.*"%>
<%@ page import ="java.awt.Font"%>
<%
//OutputStream 사용시 생기는 Thread 오류를 방지하기위해 out사용
	out.clear(); 
	out=pageContext.pushBody();
	

    ServletOutputStream sos = response.getOutputStream();

    PieChartBean bcb = new PieChartBean(); 

	JFreeChart chart = bcb.getPieChart();

	bcb.changeFont(chart);
	
	ChartUtilities.writeChartAsPNG(sos, chart, 400, 300);
	
	  
%>