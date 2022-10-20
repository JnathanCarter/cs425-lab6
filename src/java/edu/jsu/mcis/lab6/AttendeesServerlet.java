/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6;

import edu.jsu.mcis.lab6.dao.*;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johnc
 */
public class AttendeesServerlet extends HttpServlet {
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

            int attendeeid = Integer.parseInt(request.getParameter("attendeeid"));

            AttendeesDAO dao = daoFactory.getAttendeesDAO();

            out.println(dao.find(attendeeid));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String displayname = request.getParameter("displayname");

            AttendeesDAO dao = daoFactory.getAttendeesDAO();

            out.println(dao.create(firstname,lastname, displayname));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
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

            AttendeesDAO dao = daoFactory.getAttendeesDAO();

            out.println(dao.update(attendeeid, sessionid));

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
*/
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
