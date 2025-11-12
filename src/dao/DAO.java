
package dao;

import java.util.ArrayList;

public interface DAO<T> {              //T puede ser cualquier cosa
    public boolean insertar(T t);
    public ArrayList<T> seleccionarTodos();
    public ArrayList<T> seleccionarAlgunos(String dato);
    public T seleccionarId(int id);
    public boolean actualizar(int id, T t);
    public boolean borrar(int id);
}
