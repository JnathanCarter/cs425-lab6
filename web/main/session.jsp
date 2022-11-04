<%@page import="edu.jsu.mcis.lab6.dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DAOFactory daoFactory = null;
    ServletContext context = request.getServletContext();

    if (context.getAttribute("daoFactory") == null) {
        System.err.println("*** Creating new DAOFactory ...");
        daoFactory = new DAOFactory();
        context.setAttribute("daoFactory", daoFactory);
    }
    else {
        daoFactory = (DAOFactory) context.getAttribute("daoFactory");
    }
    
    TrainingSessionsDAO sessionDAO = daoFactory.getTrainingSessionsDAO();
        
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
                <div>
                    <a href="index.html">Home</a>
                </div>
                    
                <form>
                    <legend>View Session</legend>
                    <fieldset>

                        <p>Select Session
                            <%= sessionDAO.getSessionListAsHTML() %>
                        </p>
                        <input type="button" value="Get Session Info" onclick="UtilityScripts.getSessionInfo()">
                    </fieldset>
                    
                    <p>Session Information: </p>
                    
                    <div id="output" name="output">
                        <table id="outputtable" name="outputtable">
    
    
                        </table>
    
                    </div>
                    
                </form>
                    
                <form>
                    <legend>Update Registration</legend>
                    <fieldset>

                        <label for=attendeeid> Attendee ID: </label>
                        <input type="text" id="attendeeid" name="attendeeid"><!-- comment -->
                        <label for=sessionid>Session ID:</label>
                        <input type="text" id="sessionid" name="sessionid"><!-- comment -->
                        
                        <br>
                        <br>
                        
                        <label for=attendeeid_update>New Attendee ID: </label>
                        <input type="text" id="attendeeid_update" name="attendeeid_update"><!-- comment -->
                        <label for=sessionid_update>New Session ID:</label>
                        <input type="text" id="sessionid_update" name="sessionid_update"><!-- comment -->

                        <br>
                        
                        <input type="button" value="Update Registration" onclick="UtilityScripts.updateRegistration();">
                    </fieldset>
                </form>
                  
                <form >
                    <legend>Delete Registration</legend>

                        <fieldset>

                            <label for=attendeeid_delete> Delete Attendee ID: </label>
                            <input type="text" id="attendeeid_delete" name="attendeeid_delete"><!-- comment -->
                            <label for=sessionid_delete>Delete Session ID:</label>
                            <input type="text" id="sessionid_delete" name="sessionid_delete"><!-- comment -->
                            <input type="button" value="Delete Registration" onclick="UtilityScripts.deleteRegistration();">
                        </fieldset>
                </form>
               
            </body>

            </html>