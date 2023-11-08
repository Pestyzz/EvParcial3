package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import modelo.Empleado;

import db.Conexion;

public class EmpleadoDAOImpl {
    @Override
    public void create(Empleado t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        Connection cx = Conexion.getConnection();
        PreparedStatement ps = cx.prepareStatement(sql);
        /* Setea los parámetros */
        ps.setString(1, t.getRut());
        ps.setString(2, t.getNombre());
        ps.setString(3, t.getSexo());
        ps.setDate(4, new java.sql.Date(t.getFechaNacimiento().getTime()));
        ps.setInt(5, t.getEdad());
        ps.setFloat(6, t.getPeso());
        ps.setBoolean(7, t.isVip());
        ps.setInt(8, t.getComuna().getId());
        ps.executeUpdate();
        cx.close();
    }

    @Override
    public boolean read(Cliente t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Cliente> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Cliente> al = new ArrayList();
        String sql = "SELECT cli.*, com.nombre AS comuna "
                + " FROM cliente cli"
                + " INNER JOIN comuna com ON cli.id_comuna = com.id;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String rut = rs.getNString("rut");
            String nombre = rs.getString("nombre");
            String sexo = rs.getString("sexo");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            int edad = rs.getInt("edad");
            float peso = rs.getFloat("peso");
            boolean vip = rs.getBoolean("vip");
            int idComuna = rs.getInt("id_comuna");
            String comuna = rs.getNString("comuna");
            
            /* Crea el objeto */
            Cliente cliente = new Cliente();

            cliente.setRut(rut);
            cliente.setNombre(nombre);
            cliente.setSexo(sexo);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setEdad(edad);
            cliente.setPeso(peso);
            cliente.setVip(vip);
            cliente.setComuna(new Comuna(idComuna, comuna));

            al.add(cliente);
        }

        cx.close();
        return al;
    }

    @Override
    public ArrayList<Cliente> readWhere(String whereSQL) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Cliente> al = new ArrayList();
        String sql = "SELECT *, (SELECT nombre FROM comuna WHERE id = cliente.id_comuna) AS comuna "
                + "FROM cliente WHERE " + whereSQL + ";";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String rut = rs.getNString("rut");
            String nombre = rs.getString("nombre");
            String sexo = rs.getString("sexo");
            Date fechaNacimiento = rs.getDate("fecha_nacimiento");
            int edad = rs.getInt("edad");
            float peso = rs.getFloat("peso");
            boolean vip = rs.getBoolean("vip");
            int idComuna = rs.getInt("id_comuna");
            String comuna = rs.getNString("comuna");

            /* Crea el objeto */
            Cliente cliente = new Cliente();

            cliente.setRut(rut);
            cliente.setNombre(nombre);
            cliente.setSexo(sexo);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setEdad(edad);
            cliente.setPeso(peso);
            cliente.setVip(vip);
            cliente.setComuna(new Comuna(idComuna, comuna));

            al.add(cliente);
        }
        cx.close();
        return al;
    }

    @Override
    public void update(Cliente t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "UPDATE cliente SET nombre=?, sexo=?, fecha_nacimiento=?, edad=?, peso=?, vip=?, id_comuna=? WHERE rut=?;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ps.setString(1, t.getNombre());
        ps.setString(2, t.getSexo());
        ps.setDate(3, new java.sql.Date(t.getFechaNacimiento().getTime()));
        ps.setInt(4, t.getEdad());
        ps.setFloat(5, t.getPeso());
        ps.setBoolean(6, t.isVip());
        ps.setInt(7, t.getComuna().getId());
        ps.setString(8, t.getRut());
        ps.executeUpdate();
        cx.close();
    }

    @Override
    public void delete(Cliente t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "DELETE FROM cliente WHERE rut=?;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ps.setString(1, t.getRut());
        ps.executeUpdate();
        cx.close();
    }

    public int getComunaByRut(String rut) throws InstantiationException, IllegalAccessException, Exception {
        Cliente cliente = this.readWhere("rut = '" + rut + "'").get(0);
        return ((cliente == null) ? 0 : cliente.getComuna().getId());
    }
}
