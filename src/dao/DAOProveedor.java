package dao;

import bd.ConectorBaseDeDatos;
import dto.Proveedor;
import java.util.ArrayList;
import java.sql.*;

public class DAOProveedor implements DAO<Proveedor>{

    @Override
    public boolean insertar(Proveedor prov) {
        Connection con = null;
        PreparedStatement query = null;
        String sql=" insert into proveedor (nombre, apellido, telefono, "
                + "imagen, empresa, correo, ciudad, colonia, codigoPostal, "
                + "calle, numero, estado)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, prov.getNombre());
            query.setString(2, prov.getApellido());
            query.setString(3, prov.getTelefono());
            query.setBytes(4, prov.getImagen());
            query.setString(5, prov.getEmpresa());
            query.setString(6, prov.getCorreo());
            query.setString(7, prov.getCiudad());
            query.setString(8, prov.getColonia());
            query.setString(9, prov.getCodigoPostal());
            query.setString(10, prov.getCalle());
            query.setString(11, prov.getNumero());
            query.setString(12, prov.getEstado());
            int res=query.executeUpdate();
            return(res>0);
        } catch(Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public ArrayList<Proveedor> seleccionarTodos() {
        ArrayList<Proveedor> registros=new ArrayList<>();
        String sql = "select * from proveedor";
        Connection con = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
                Proveedor registro = new Proveedor();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEmpresa(respuestaSQL.getString("empresa"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setCiudad(respuestaSQL.getString("ciudad"));
                registro.setColonia(respuestaSQL.getString("colonia"));
                registro.setCodigoPostal(respuestaSQL.getString("codigoPostal"));
                registro.setCalle(respuestaSQL.getString("calle"));
                registro.setNumero(respuestaSQL.getString("numero"));
                registro.setEstado(respuestaSQL.getString("estado"));                
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;          
    }

    @Override
    public ArrayList<Proveedor> seleccionarAlgunos(String empresa) {
        ArrayList<Proveedor> registros=new ArrayList<>();
        String sql = "select * from proveedor where empresa=?";
        Connection con = null;
        Proveedor registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, empresa);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
            registro = new Proveedor();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEmpresa(respuestaSQL.getString("empresa"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setCiudad(respuestaSQL.getString("ciudad"));
                registro.setColonia(respuestaSQL.getString("colonia"));
                registro.setCodigoPostal(respuestaSQL.getString("codigoPostal"));
                registro.setCalle(respuestaSQL.getString("calle"));
                registro.setNumero(respuestaSQL.getString("numero"));
                registro.setEstado(respuestaSQL.getString("estado")); 
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registros;          
    }

    @Override
    public Proveedor seleccionarId(int id) {
        ArrayList<Proveedor> registros=new ArrayList<>();
        String sql = "select * from proveedor where id=?";
        Connection con = null;
        Proveedor registro = null;
        try {
            con = ConectorBaseDeDatos.conectar();
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet respuestaSQL = query.executeQuery();
            while(respuestaSQL.next()){
            registro = new Proveedor();
                registro.setId(respuestaSQL.getInt("id"));
                registro.setNombre(respuestaSQL.getString("nombre"));
                registro.setApellido(respuestaSQL.getString("apellido"));
                registro.setTelefono(respuestaSQL.getString("telefono"));
                registro.setImagen(respuestaSQL.getBytes("imagen"));
                registro.setEmpresa(respuestaSQL.getString("empresa"));
                registro.setCorreo(respuestaSQL.getString("correo"));
                registro.setCiudad(respuestaSQL.getString("ciudad"));
                registro.setColonia(respuestaSQL.getString("colonia"));
                registro.setCodigoPostal(respuestaSQL.getString("codigoPostal"));
                registro.setCalle(respuestaSQL.getString("calle"));
                registro.setNumero(respuestaSQL.getString("numero"));
                registro.setEstado(respuestaSQL.getString("estado")); 
                registros.add(registro);
            }
        } catch (Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return registro;
    }

    @Override
    public boolean actualizar(int id, Proveedor prov) {
        Connection con = null;
        PreparedStatement query = null;
        String sql=" update proveedor set nombre=?, apellido=?, telefono=?, "
                + "imagen=?, empresa=?, correo=?, ciudad=?, colonia=?, "
                + "codigoPostal=?, calle=?, numero=? where id=?";
        try{
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, prov.getNombre());
            query.setString(2, prov.getApellido());
            query.setString(3, prov.getTelefono());
            query.setBytes(4, prov.getImagen());
            query.setString(5, prov.getEmpresa());
            query.setString(6, prov.getCorreo());
            query.setString(7, prov.getCiudad());
            query.setString(8, prov.getColonia());
            query.setString(9, prov.getCodigoPostal());
            query.setString(10, prov.getCalle());
            query.setString(11, prov.getNumero());
            query.setInt(12, prov.getId());
            int res=query.executeUpdate();
            return(res>0);
        } catch(Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }

    @Override
    public boolean borrar(int id) {
        Connection con = null;
        PreparedStatement query = null;
        String sql=" update proveedor set estado=? where id=?";
        try{
            con=ConectorBaseDeDatos.conectar();
            query=con.prepareStatement(sql);
            query.setString(1, "INACTIVO");
            query.setInt(2, id);
            int res=query.executeUpdate();
            return(res>0);
        } catch(Exception e) {
            System.out.println("Error de SQL: " + e.getMessage());
        } finally {
            ConectorBaseDeDatos.desconectar(con);
        }
        return false;
    }
}
