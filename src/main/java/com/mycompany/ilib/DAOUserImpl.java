package com.mycompany.ilib;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOUsers;
import com.mycompany.models.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class DAOUserImpl extends Database implements DAOUsers {

    // Facade: método simplificado para registrar usuarios.
    public void registrarUsuario(String name, String lastNameP, String lastNameM, String domicilio, String tel) throws Exception {
        Users user = new Users();
        user.setName(name);
        user.setLast_name_p(lastNameP);
        user.setLast_name_m(lastNameM);
        user.setDomicilio(domicilio);
        user.setTel(tel);
        this.registrar(user);
    }

    // Facade: método simplificado para obtener un usuario por ID.
    public Users buscarUsuarioPorId(int id) throws Exception {
        return this.getUserById(id);
    }

    // Facade: método simplificado para listar usuarios por nombre.
    public List<Users> buscarUsuariosPorNombre(int catIndex, String input) throws Exception {
        return this.listar(catIndex,input);
    }

    // Implementación existente
    @Override
    public void registrar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO users(name, last_name_p, last_name_m, domicilio, tel) VALUES (?,?,?,?,?);");
            st.setString(1, user.getName());
            st.setString(2, user.getLast_name_p());
            st.setString(3, user.getLast_name_m());
            st.setString(4, user.getDomicilio());
            st.setString(5, user.getTel());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE users SET name = ?, last_name_p = ?,last_name_m = ?, domicilio = ?, tel = ? WHERE id = ?;");
            st.setString(1, user.getName());
            st.setString(2, user.getLast_name_p());
            st.setString(3, user.getLast_name_m());
            st.setString(4, user.getDomicilio());
            st.setString(5, user.getTel());
            st.setInt(6, user.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(int userId) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM users WHERE id = ?;");
            st.setInt(1, userId);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Users> listar(int catIndex, String input) throws Exception {
        List<Users> lista = null;
        String cat = "";
        switch(catIndex){
            case 0: //ID
                cat = "id";
                break;
            case 1: //NOMBRE
                cat = "name";
                break;
            case 2: //APELLIDO-PATERNO
                cat = "last_name_p";
                break;
            case 3: //APELLIDO-MATERNO
                cat = "last_name_m";
                break;
            case 4: //DOMICILIO
                cat = "domicilio";
                break;
            case 5: //TELEFONO
                cat = "tel";
                break;
        }
        try {
            this.Conectar();
            String Query = StringUtils.isBlank(input) ? "SELECT * FROM users;" : "SELECT * FROM users WHERE "+cat+" LIKE '" + input + "%';";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name_p(rs.getString("last_name_p"));
                user.setLast_name_m(rs.getString("last_name_m"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
                lista.add(user);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    @Override
    public Users getUserById(int userId) throws Exception {
        Users user = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM users WHERE id = ? LIMIT 1;");
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                user = new Users();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLast_name_p(rs.getString("last_name_p"));
                user.setLast_name_m(rs.getString("last_name_m"));
                user.setDomicilio(rs.getString("domicilio"));
                user.setTel(rs.getString("tel"));
                user.setSanctions(rs.getInt("sanctions"));
                user.setSanc_money(rs.getInt("sanc_money"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return user;
    }

    @Override
    public void sancionar(Users user) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE users SET sanctions = ?, sanc_money = ? WHERE id = ?");
            st.setInt(1, user.getSanctions());
            st.setInt(2, user.getSanc_money());
            st.setInt(3, user.getId());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}

            
