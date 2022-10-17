/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.*;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author johnc
 */
public class TrainingSessionsDAO {
        private final DAOFactory daoFactory;

        private final String QUERY_SELECT_ALL = "SELECT * FROM session";        
        private final String QUERY_SELECT_BY_ID ="SELECT * FROM (registration JOIN attendee ON attendee.id=registration.attendeeid) WHERE registration.sessionid = ?";
        
        TrainingSessionsDAO(DAOFactory dao) {
                this.daoFactory = dao;
        }


        //retrives list of all training sessions
        public String list(){
             String x = " "  ; 
        return x;
                     }

        //retrieves list of all attendess currently registered for the session specified by id
        public String find(int id){
        
        
        JSONArray json = new JSONArray();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, id);

            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();

                while (rs.next()) {
                        Map json_map = new LinkedHashMap();

                        json_map.put("success", hasresults);

                        json_map.put("attendeeid", rs.getInt("attendeeid"));
                        json_map.put("sessionid", rs.getInt("sessionid"));
                        json_map.put("firstname", rs.getString("firstname"));
                        json_map.put("lastname", rs.getString("lastname"));
                        json_map.put("displayname", rs.getString("displayname"));
                        json_map.put("session", rs.getString("description"));

                        json.add(json_map);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return JSONValue.toJSONString(json);
        }


}
