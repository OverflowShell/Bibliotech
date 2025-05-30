package com.mycompany.ilib;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOLendings;
import com.mycompany.models.Books;
import com.mycompany.models.Lendings;
import com.mycompany.models.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOLendingsImpl extends Database implements DAOLendings {

    // Facade: método simplificado para registrar un préstamo
    public void registrarPrestamo(int userId, int bookId, String dateOut) throws Exception {
        Lendings lending = new Lendings();
        lending.setUser_id(userId);
        lending.setBook_id(bookId);
        lending.setDate_out(dateOut);
        this.registrar(lending);
    }

    // Facade: método simplificado para devolver un libro
    public void devolverLibro(int lendingId, String dateReturn) throws Exception {
        Lendings lending = getLendingById(lendingId);
        if (lending != null) {
            lending.setDate_return(dateReturn);
            this.modificar(lending);
        }
    }

    // Facade: obtener préstamo por usuario y libro
    public Lendings obtenerPrestamo(Users user, Books book) throws Exception {
        return this.getLending(user, book);
    }

    // Implementación existente: registrar préstamo
    @Override
    public void registrar(Lendings lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO lendings(user_id, book_id, date_out) VALUES(?,?,?);");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    // Implementación existente: modificar préstamo
    @Override
    public void modificar(Lendings lending) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE lendings SET user_id = ?, book_id = ?, date_out = ?, date_return = ? WHERE id = ?");
            st.setInt(1, lending.getUser_id());
            st.setInt(2, lending.getBook_id());
            st.setString(3, lending.getDate_out());
            st.setString(4, lending.getDate_return());
            st.setInt(5, lending.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    // Implementación existente: obtener préstamo por usuario y libro
    @Override
    public Lendings getLending(Users user, Books book) throws Exception {
        Lendings lending = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings WHERE user_id = ? AND book_id = ? AND date_return IS NULL ORDER BY id DESC LIMIT 1");
            st.setInt(1, user.getId());
            st.setInt(2, book.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lending = new Lendings();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lending;
    }

    // Implementación existente: listar todos los préstamos
    @Override
    public List<Lendings> listar() throws Exception {
        List<Lendings> lista = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings ORDER BY id DESC");
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Lendings lending = new Lendings();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
                lista.add(lending);
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lista;
    }

    // Implementación existente: obtener préstamo por ID
    private Lendings getLendingById(int lendingId) throws Exception {
        Lendings lending = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM lendings WHERE id=? LIMIT 1;");
            st.setInt(1, lendingId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lending = new Lendings();
                lending.setId(rs.getInt("id"));
                lending.setUser_id(rs.getInt("user_id"));
                lending.setBook_id(rs.getInt("book_id"));
                lending.setDate_out(rs.getString("date_out"));
                lending.setDate_return(rs.getString("date_return"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return lending;
    }
}

