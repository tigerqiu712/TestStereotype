/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package durbodax.daos;

import durbodax.customers.Enums.Sex;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SimmonsD
 */
public class DerbyCustomerCompareDAO implements CustomerCompareDAO {
    private StringBuilder mquery = new StringBuilder("SELECT COUNT(*) FROM MAIN WHERE SEX=1");
    private StringBuilder fquery = new StringBuilder("SELECT COUNT(*) FROM MAIN WHERE SEX=2");

    public void addCondition(String condition)
    {
        mquery.append(" AND ").append(condition);
        fquery.append(" AND ").append(condition);
    }

    public Map<Sex, Integer> runQuery(){
        Map<Sex, Integer> map = new HashMap<Sex,Integer>();
        map.put(Sex.Male, runQuery(mquery.toString(), Sex.Male));
        map.put(Sex.Female, runQuery(fquery.toString(), Sex.Female));
        return map;
    }

    private Integer runQuery(String query, Sex gender)
    {
        ResultSet rs = null;
        Statement s = null;
        Connection conn = DerbyDAOFactory.getConnection();
        float total = 0;
        int percentage=0;

        try{
            s = conn.createStatement();
            rs = s.executeQuery("SELECT COUNT(*) FROM MAIN WHERE SEX = "+gender.getCode());
            if(rs.next())
                total = (float)rs.getInt(1);

            rs = s.executeQuery(query);

            if (rs.next() && total > 0) {
               percentage = Math.round((rs.getInt(1)/total)*100);
            }
        }
        catch(SQLException e){
            System.err.println("Select Failed: " + e.toString());
        }
        finally {
            try {
            if(rs!= null)rs.close();
            if(s != null)s.close();
            }catch(SQLException ex) {}
        }
        return percentage;
    }

}
