/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Admin
 */
@Path("main")
public class GenericResource {

    @Context
    private UriInfo context;

    public GenericResource() throws SQLException {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "mad304p4", "mad303p4pw");
        stm = con.createStatement();
    }
    Statement stm;
    ResultSet rs;
    int number;
    Connection con;

    @GET
    @Path("login&{username}&{password}&{type}")
    @Produces("application/json")
    public String getJson(@PathParam("username") String uname, @PathParam("password") String pass,@PathParam("type") String type) throws SQLException {
        JSONObject login = new JSONObject();
        try {

            ResultSet rs = stm.executeQuery("select * from person WHERE username=" + uname + "and password=" + pass+ "and type=" + type);

            System.out.println();

            if (rs.next()) {

                login.accumulate("STATUS", "OK");
                login.accumulate("Timestamp", 1234567890);
                login.accumulate("Active", true);
                login.accumulate("Message", "Successfully Login");

            } else {
                login.accumulate("STATUS", "wrong");
                login.accumulate("Timestamp", 1234567890);
                login.accumulate("Active", true);
                login.accumulate("Message", "user doesnot exist");

            }

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return login.toString();
    }
    
    
    
    @GET
    @Path("getcategories")
    @Produces("application/json")
    public String getCate() throws SQLException {
      
        JSONArray arr = new JSONArray();
        try {

            ResultSet rs = stm.executeQuery("select * from category ");
            
            while(rs.next()){
               JSONObject ob = new JSONObject();
               ob.put("image", rs.getString("IMAGE"));
               ob.put("description", rs.getString("DESCRIPTION"));
               ob.put("ID", rs.getInt("CATEGORY_ID"));
               ob.put("category", rs.getString("CATEGORY_NAME"));
               arr.add(ob);
            }

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr.toString();
    }
    
    @GET
    @Path("getcategoriesres")
    @Produces("application/json")
    public String getCate1() throws SQLException {
      
        JSONArray arr = new JSONArray();
        try {

            ResultSet rs = stm.executeQuery("select * from category where category_name = 'Restaurant'");
            
            while(rs.next()){
               JSONObject ob = new JSONObject();
               ob.put("image", rs.getString("IMAGE"));
               ob.put("description", rs.getString("DESCRIPTION"));
               ob.put("ID", rs.getInt("CATEGORY_ID"));
               ob.put("category", rs.getString("CATEGORY_NAME"));
               arr.add(ob);
            }

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr.toString();
    }
    
    @GET
    @Path("getcategoriestra")
    @Produces("application/json")
    public String getCate2() throws SQLException {
      
        JSONArray arr = new JSONArray();
        try {

            ResultSet rs = stm.executeQuery("select * from category where category_name = 'Car'");
            
            while(rs.next()){
               JSONObject ob = new JSONObject();
               ob.put("image", rs.getString("IMAGE"));
               ob.put("description", rs.getString("DESCRIPTION"));
               ob.put("ID", rs.getInt("CATEGORY_ID"));
               ob.put("category", rs.getString("CATEGORY_NAME"));
               arr.add(ob);
            }

            rs.close();
            stm.close();

        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr.toString();
    }
    
    @GET
    @Path("registration&{type}&{username}&{password}&{firstName}&{lastName}&{email}")
    @Produces("application/json")
    public String getJsonregister(@PathParam("type") String theType, @PathParam("username") String uname, @PathParam("password") String pass,
            @PathParam("firstName") String fn, @PathParam("lastName") String ln, @PathParam("email") String email) throws SQLException {

        try {

            number = stm.executeUpdate("INSERT INTO PERSON VALUES(persons_id.nextval,'" + theType + "','" + uname + "','" + pass + "','" + fn + "','" + ln + "','" + email + "')");
            
           stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
    @GET
    @Path("delete&{category_id}")
    @Produces("application/json")
    public String getJsondelete(@PathParam("category_id") int delid) {

        try {
            number = stm.executeUpdate("DELETE FROM CATEGORY WHERE category_id= " + delid);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(delid + " Subject has deleted");
        try {
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
   @GET
    @Path("update&{category_id}&{category_name}&{description}")
    @Produces("application/json")
    public String getJsonupdate(@PathParam("category_id") int cid, @PathParam("category_name") String cname,@PathParam("description") String cdes) {

        try {
            number = stm.executeUpdate("UPDATE category SET category_name='" + cname +"', description='"+cdes +"' where category_id=" + cid);
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Question has Updated" + number);
        try {
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "[]";
    }
}