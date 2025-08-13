/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.interfaces;
import com.mycompany.models.Employees;
/**
 *
 * @author MANUEL
 */
public interface DAOEmployees {
    public Employees getEmployee(String user, String clave) throws Exception;
    public Employees getEmployeeByUser(String user) throws Exception;
    public Employees getEmployeeByPassword(String clave) throws Exception;
}
