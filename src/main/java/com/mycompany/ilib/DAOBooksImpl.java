package com.mycompany.ilib;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOBooks;
import com.mycompany.models.Books;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOBooksImpl extends Database implements DAOBooks {
public void registrarConLog(Books book) throws Exception {
    Logger logger = LoggerFactory.getLogger(DAOBooksImpl.class);
    
    try {
        this.Conectar();
        PreparedStatement st = this.conexion.prepareStatement(
            "INSERT INTO books(title, date, author, category, edit, lang, pages, description, ejemplares, stock, available) VALUES(?,?,?,?,?,?,?,?,?,?,?);"
        );
        st.setString(1, book.getTitle());
        st.setString(2, book.getDate());
        st.setString(3, book.getAuthor());
        st.setString(4, book.getCategory());
        st.setString(5, book.getEdit());
        st.setString(6, book.getLang());
        st.setString(7, book.getPages());
        st.setString(8, book.getDescription());
        st.setString(9, book.getEjemplares());
        st.setInt(10, book.getStock());
        st.setInt(11, book.getAvailable());
        st.executeUpdate();
        st.close();
        logger.info("Libro registrado correctamente: {}", book.getTitle());
    } catch(Exception e) {
        logger.error("Error al registrar el libro: {}", book.getTitle(), e);
        throw e;
    } finally {
        this.Cerrar();
    }
    
}
    // Facade: método simplificado para registrar un libro
    public void registrarLibro(String title, String date, String author, String category, String edit, 
                               String lang, String pages, String description, String ejemplares, 
                               int stock, int available) throws Exception {
        Books book = new Books();
        book.setTitle(title);
        book.setDate(date);
        book.setAuthor(author);
        book.setCategory(category);
        book.setEdit(edit);
        book.setLang(lang);
        book.setPages(pages);
        book.setDescription(description);
        book.setEjemplares(ejemplares);
        book.setStock(stock);
        book.setAvailable(available);
        this.registrar(book);
    }

    // Facade: método simplificado para modificar un libro
    public void modificarLibro(int id, String title, String date, String author, String category, String edit, 
                               String lang, String pages, String description, String ejemplares, 
                               int stock, int available) throws Exception {
        Books book = this.getBookById(id);
        if (book != null) {
            book.setTitle(title);
            book.setDate(date);
            book.setAuthor(author);
            book.setCategory(category);
            book.setEdit(edit);
            book.setLang(lang);
            book.setPages(pages);
            book.setDescription(description);
            book.setEjemplares(ejemplares);
            book.setStock(stock);
            book.setAvailable(available);
            this.modificar(book);
        }
    }

    // Facade: eliminar un libro por ID
    public void eliminarLibro(int bookId) throws Exception {
        this.eliminar(bookId);
    }

    // Facade: listar libros por título
    public List<Books> listarLibros(String title) throws Exception {
        return this.listar(title);
    }

    // Implementación existente: registrar un libro
    @Override
    public void registrar(Books book) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO books(title, date, author, category, edit, lang, pages, description, ejemplares, stock, available) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
         logger.debug("Intentando registrar libro: {}", book.getTitle());
         try {
            // Operaciones de BD...
            logger.info("Libro registrado exitosamente: {}", book.getTitle());
        } catch (Exception e) {
            logger.error("Error al registrar libro", e);
            throw e;
        }
    }

    // Implementación existente: modificar un libro
    @Override
    public void modificar(Books book) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE books SET title = ?, date = ?, author = ?, category = ?, edit = ?, lang = ?, pages = ?, description = ?, ejemplares = ?, stock = ?, available = ? WHERE id = ?");
            st.setString(1, book.getTitle());
            st.setString(2, book.getDate());
            st.setString(3, book.getAuthor());
            st.setString(4, book.getCategory());
            st.setString(5, book.getEdit());
            st.setString(6, book.getLang());
            st.setString(7, book.getPages());
            st.setString(8, book.getDescription());
            st.setString(9, book.getEjemplares());
            st.setInt(10, book.getStock());
            st.setInt(11, book.getAvailable());
            st.setInt(12, book.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    // Implementación existente: eliminar un libro por ID
    @Override
    public void eliminar(int bookId) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM books WHERE id = ?;");
            st.setInt(1, bookId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    // Implementación existente: listar libros
    @Override
    public List<Books> listar(String title) throws Exception {
        List<Books> lista = null;
        try {
            this.Conectar();
            String Query = title.isEmpty() ? "SELECT * FROM books;" : "SELECT * FROM books WHERE title LIKE '%" + title + "%';";
            PreparedStatement st = this.conexion.prepareStatement(Query);
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("date"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
                lista.add(book);
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

    // Implementación existente: obtener un libro por ID
    @Override
    public Books getBookById(int bookId) throws Exception {
        Books book = null;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM books WHERE id = ? LIMIT 1;");
            st.setInt(1, bookId);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                book = new Books();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setDate(rs.getString("date"));
                book.setAuthor(rs.getString("author"));
                book.setCategory(rs.getString("category"));
                book.setEdit(rs.getString("edit"));
                book.setLang(rs.getString("lang"));
                book.setPages(rs.getString("pages"));
                book.setDescription(rs.getString("description"));
                book.setEjemplares(rs.getString("ejemplares"));
                book.setStock(rs.getInt("stock"));
                book.setAvailable(rs.getInt("available"));
            }
            rs.close();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return book;
    }
    private static final Logger logger = LoggerFactory.getLogger(DAOBooksImpl.class);

}


