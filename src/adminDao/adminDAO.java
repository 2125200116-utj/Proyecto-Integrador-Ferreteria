
package adminDao;

import java.util.ArrayList;

public interface adminDAO<T> {              //T puede ser cualquier cosa
    public boolean insertar(T t) throws Exception;
    public ArrayList<T> seleccionarTodos() throws Exception;
    public ArrayList<T> seleccionarAlgunos(String dato) throws Exception;
    public T seleccionarId(int id) throws Exception;
    public boolean actualizar(int id, T t) throws Exception;
    public boolean borrar(int id) throws Exception;
}
