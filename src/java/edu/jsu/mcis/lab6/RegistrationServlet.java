package edu.jsu.mcis.lab6;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.*;
import edu.jsu.mcis.lab6.dao.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.TimeUnit;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        response.setContentType("application/json; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            int sessionid = Integer.parseInt(request.getParameter("sessionid"));
            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.find(sessionid, attendeeid));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param request
     * @param response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // INSERT YOUR CODE HERE
        DAOFactory daoFactory = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }

        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            // check if id parameter
            if (request.getParameterMap().containsKey("sessionid")
                    && request.getParameterMap().containsKey("sessionid")) {

                int sessionid = Integer.parseInt(request.getParameter("sessionid"));
                int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));

                out.println(dao.create(sessionid, attendeeid));
            } else if (request.getParameterMap().containsKey("firstname")
                    && request.getParameterMap().containsKey("lastname")
                    && request.getParameterMap().containsKey("displayname")) {

                // get parameters

                int sessionid = Integer.parseInt(request.getParameter("sessionmenu"));
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");

                // get attendee id

                AttendeesDAO attendeeDAO = daoFactory.getAttendeesDAO();
                int attendeeid = attendeeDAO.findID(firstname, lastname);

                // call create fuction
                System.err.println("attendee id============"+attendeeid);
                out.println(dao.create(sessionid, attendeeid));
                response.sendRedirect("registration.jsp");

            } else {

                Exception notvalideException = new Exception("Args not valid");
                throw notvalideException;

            }
        response.sendRedirect("registration.jsp");
        } catch (Exception e) {
            
            
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        // get parameters from the request
        BufferedReader br = null;
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            HashMap<String, String> parameters = new HashMap<>();
            String[] pairs = p.trim().split("&");

            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }

            System.err.println(parameters);
            // get id parameter from request
            int attendeeid_old = Integer.parseInt(parameters.get("attendeeid_old"));
            int sessionid_old = Integer.parseInt(parameters.get("sessionid_old"));
            int attendeeid_updated = Integer.parseInt(parameters.get("attendeeid_updated"));
            int sessionid_updated = Integer.parseInt(parameters.get("sessionid_updated"));
            // rest of servlet code goes here

            DAOFactory daoFactory = null;

            ServletContext context = request.getServletContext();

            if (context.getAttribute("daoFactory") == null) {
                System.err.println("*** Creating new DAOFactory ...");
                daoFactory = new DAOFactory();
                context.setAttribute("daoFactory", daoFactory);
            } else {
                daoFactory = (DAOFactory) context.getAttribute("daoFactory");
            }

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.update(sessionid_old, attendeeid_old, sessionid_updated, attendeeid_updated));

        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

        // get parameters from the request
        BufferedReader br = null;
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            HashMap<String, String> parameters = new HashMap<>();
            String[] pairs = p.trim().split("&");

            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }

            // get id parameter from request
            int attendeeid = Integer.parseInt(parameters.get("attendeeid"));
            int sessionid = Integer.parseInt(parameters.get("sessionid"));
            // rest of servlet code goes here

            DAOFactory daoFactory = null;

            ServletContext context = request.getServletContext();

            if (context.getAttribute("daoFactory") == null) {
                System.err.println("*** Creating new DAOFactory ...");
                daoFactory = new DAOFactory();
                context.setAttribute("daoFactory", daoFactory);
            } else {
                daoFactory = (DAOFactory) context.getAttribute("daoFactory");
            }

            RegistrationDAO dao = daoFactory.getRegistrationDAO();

            out.println(dao.delete(attendeeid, sessionid));

        }

        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Registration Servlet";
    }

}