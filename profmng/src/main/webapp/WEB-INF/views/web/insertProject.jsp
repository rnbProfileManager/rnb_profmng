<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page import="java.sql.*" %>
<%
    request.setCharacterEncoding("UTF-8");

    String projectCd = request.getParameter("projectCd");
	String projectNm = request.getParameter("projectNm");
    String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
    String pmId = request.getParameter("pmId");
	String client = request.getParameter("client");
	String contractor = request.getParameter("contractor");
	String manMonth = request.getParameter("manMonth");
	String totAmt = request.getParameter("totAmt");
	String projectType = request.getParameter("projectType");
	
    String url = "jdbc:oracle:thin:@192.168.1.212:1521:xe";
    String user = "C##rnb_001";
    String password = "rnb6707";

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(url, user, password);

        String sql = "INSERT INTO project_table (PROJECT_CD, PROJECT_NM, START_DATE, END_DATE, PM_ID, CLIENT, CONTRACTOR, MAN_MONTH, TOT_AMT, PROJECT_TYPE)" +
		 "VALUES (?,?,TO_DATE(?, 'YYYY-MM-DD'),TO_DATE(?, 'YYYY-MM-DD'),?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, projectCd);
        pstmt.setString(2, projectNm);
        pstmt.setString(3, startDate);
		pstmt.setString(4, endDate);
		pstmt.setString(5, pmId);
		pstmt.setString(6, client);
		pstmt.setString(7, contractor);
		pstmt.setString(8, manMonth);
		pstmt.setString(9, totAmt);
		pstmt.setString(10, projectType);
        pstmt.executeUpdate();

		String successMsg = URLEncoder.encode("데이터가 성공적으로 삽입되었습니다.", "UTF-8");
		<%--response.sendRedirect("project.jsp?insertSuccess=true&projectCd=" + successMsg);--%>
    } catch(Exception e) {
		String errorMsg = URLEncoder.encode("오류 발생: " + e.getMessage(), "UTF-8");
		<%--response.sendRedirect("project.jsp?insertError=true&projectCd=" + errorMsg);--%>
    } finally {
        if (pstmt != null) try { pstmt.close(); } catch(Exception e1) {}
        if (conn != null) try { conn.close(); } catch(Exception e2) {}
    }
%>
