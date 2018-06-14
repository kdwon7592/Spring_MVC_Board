<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.sql.*"%>
<html>
<head>
<body >
<%
        String v1 = null;

        try {
                Context initContext = new InitialContext();
                Context envContext  = (Context) initContext.lookup("java:/comp/env");
                DataSource datasource = (DataSource) envContext.lookup("jdbc/mysql");
                Connection conn = datasource.getConnection();
				System.out.println(conn);
                Statement st=conn.createStatement();
                String sql = "select 'Welcome! Tomcat DB Connection Pool (DBCP)' from dual";
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                v1 = rs.getString(1);
        } catch (Exception e){
                e.printStackTrace(System.out);
        }
%>
        <%=v1 %>

</body> 
</html>