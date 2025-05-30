package com.mycompany.interfaces;

import com.mycompany.models.Users;
import java.util.List;

public interface DAOUsers { //Metodos a utilizar
    public void registrar(Users user)  throws Exception;
    public void modificar(Users user)  throws Exception;
    public void eliminar(int userId)  throws Exception;//OPCIONAL
    public void sancionar(Users user) throws Exception;
    public List<Users> listar(String name) throws Exception;//Se obliga a la creación de una lista
    public Users getUserById(int userId) throws Exception;
}
