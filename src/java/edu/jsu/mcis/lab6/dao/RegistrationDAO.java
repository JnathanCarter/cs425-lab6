package edu.jsu.mcis.lab6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.*;

public class RegistrationDAO {

    private final DAOFactory daoFactory;

    private final String QUERY_SELECT_BY_ID = "SELECT * FROM "
            + "((registration JOIN attendee ON registration.attendeeid = attendee.id) "
            + "JOIN `session` ON registration.sessionid = `session`.id) "
            + "WHERE `session`.id = ? AND attendee.id = ?";

    private final String QUERY_CREATE = "INSERT INTO registration (attendeeid, sessionid) VALUES (?,?)";

    RegistrationDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }

    /**
     * Retrieves the registration information for an attendee
     */
    public String find(int sessionid, int attendeeid) {

        JSONObject json = new JSONObject();
        json.put("success", false);

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, sessionid);
            ps.setInt(2, attendeeid);

            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();

                if (rs.next()) {

                    json.put("success", hasresults);

                    json.put("attendeeid", rs.getInt("attendeeid"));
                    json.put("sessionid", rs.getInt("sessionid"));
                    json.put("firstname", rs.getString("firstname"));
                    json.put("lastname", rs.getString("lastname"));
                    json.put("displayname", rs.getString("displayname"));
                    json.put("session", rs.getString("description"));

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

/**
*Add a registration for a new attendee (a person not already registered for a session)
*POST REQUEST
*/
public String create(int sessionid, int attendeeid){
    JSONObject json = new JSONObject();
    
    json.put("success", false);
    PreparedStatement ps = null;
    ResultSet rs = null;

    try{
        Connection conn = daoFactory.getConnection();

        ps = conn.prepareStatement(QUERY_CREATE);
        ps.setInt(1,attendeeid);
        ps.setInt(2,sessionid);

        boolean hasresults = ps.execute();

        if(hasresults){
            rs = ps.getResultSet();

            if(rs.next()){
                json.put("success",hasresults);
            }
        }

    }
    catch (Exception e){e.printStackTrace();}
     
     finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            
        }
    return JSONValue.toJSONString(json);
}

    // Update a registration for a previously-registered attendee (to change their
    // assigned session)

    // Cancel a registration for a previously-registered attendee.

}
