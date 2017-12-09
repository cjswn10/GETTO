
<%@ page import = "org.jfree.chart.*" %>
<%@ page import ="chart.*"%>
<%@ page import ="java.awt.Font"%>
<%
	out.clear(); 
	
	out=pageContext.pushBody();


    ServletOutputStream sos = response.getOutputStream();

    PieChartBean bcb = new PieChartBean(); 

	JFreeChart chart = bcb.getPieChartByBmi();

	bcb.changeFont(chart);
	ChartUtilities.writeChartAsPNG(sos, chart, 400, 300);
	
	  
%>