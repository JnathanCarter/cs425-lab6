<%-- 
    Document   : index
    Created on : Nov 4, 2022, 1:23:52 PM
    Author     : johnc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <p>
            <a href="main/registration.jsp">Registration Page</a>

    </p>
    

    <p>
            <a href="main/attendees.jsp">Attendee Page</a>

    </p>
    

        <%if ( request.isUserInRole("administrator") ) {
        %>
            <p>
    <a href="main/session.jsp">Training Sessions Page</a>
</p>
        <%
            }
        else if ( request.isUserInRole("user") ) {
        %>
            <p>You are logged in as a USER.</p>
        <%
            }
        %>
    </body>
</html>
