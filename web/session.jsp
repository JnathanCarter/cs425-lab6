<%@page import="edu.jsu.mcis.lab6.dao.*"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%
            DAOFactory daoFactory= null;
            ServletContext context= request.getServletContext();

            if (context.getAttribute(" daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory= new DAOFactory();
            context.setAttribute(" daoFactory", daoFactory);
            }
            else {
            daoFactory= (DAOFactory) context.getAttribute(" daoFactory");
            }

            TrainingSessionsDAO sessionDAO= daoFactory.getTrainingSessionsDAO();
            %>

            <!DOCTYPE html>
            <html>
                <head>
                    <meta http-equiv="Content-Type" content="text/html;
                        charset=UTF-8">
                    <title>JSP Page</title>
                    <script type="text/javascript" src="jquery-3.6.0.min.js"></script>
                    <script src="UtilityScripts.js"></script>
                </head>
                <body>
                    <p>Select Session
                        <%= sessionDAO.getSessionListAsHTML() %>
                        </p>

                        <p>
                            <input type="button" value="Get Session Info"
                                onclick="UtilityScripts.getSessionInfo()">
                        </p>

                        <label for=attendeeid> Attendee ID: </label>
                        <input type="text" id="attendeeid" name="attendeeid"><!-- comment -->
                        <label for=sessionid>Session ID:</label>
                        <input type="text" id="sessionid" name="sessionid"><!-- comment -->
                        <P>
                            <input type="button" value="Update Registration"
                                onclick="UtilityScripts.updateRegistration();"><!-- comment -->
                            <input type="button" value="Delete Registration"
                                onclick="UtilityScripts.deleteRegistration();">
                        </P>

                        <p>Session Information: </p>
                        <div id="output" name="output">
                            <table id="outputtable" name="outputtable">
                               

                            </table>

                        </div>
                    </body>
                </html>
