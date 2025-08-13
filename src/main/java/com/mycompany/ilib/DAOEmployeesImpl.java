/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ilib;
import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOEmployees;
import com.mycompany.models.Employees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author MANUEL
 */
public class DAOEmployeesImpl extends Database implements DAOEmployees{
    
    @Override
    public Employees getEmployee(String user, String clave) throws Exception{
        Employees empleado = null;
        try{
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM empleados WHERE usuario=? AND clave=? LIMIT 1;");
            st.setString(1, user);
            st.setString(2, clave);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                empleado = new Employees();
                empleado.setId(rs.getInt("id"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setClave(rs.getString("clave"));
                empleado.setRol(rs.getString("rol"));
            }
            rs.close();
            st.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.Cerrar();
        }
        return empleado;
    }
    
    @Override
    public Employees getEmployeeByUser(String user) throws Exception{
        Employees empleado = null;
        try{
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM empleados WHERE usuario=? LIMIT 1;");
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                empleado = new Employees();
                empleado.setId(rs.getInt("id"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setClave(rs.getString("clave"));
                empleado.setRol(rs.getString("rol"));
            }
            rs.close();
            st.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.Cerrar();
        }
        return empleado;
    }
    
    @Override
    public Employees getEmployeeByPassword(String clave) throws Exception{
        Employees empleado = null;
        try{
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM empleados WHERE clave=? LIMIT 1;");
            st.setString(1, clave);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                empleado = new Employees();
                empleado.setId(rs.getInt("id"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setClave(rs.getString("clave"));
                empleado.setRol(rs.getString("rol"));
            }
            rs.close();
            st.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.Cerrar();
        }
        return empleado;
    }
}
