package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import modelo.Empleado;

import db.Conexion;

public class EmpleadoDAOImpl implements CrudDAOable<Empleado> {
    @Override
    public void create(Empleado t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, ?);";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        /* Setea los parámetros */
        ps.setInt(1, t.getId());
        ps.setString(2, t.getNombre());
        ps.setString(3, t.getDepartamento());
        ps.setDate(4, new java.sql.Date(t.getFechaContrato().getTime()));
        ps.setInt(5, t.getSueldo());
        ps.setString(6, t.getPosicion());
        ps.executeUpdate();
        cx.close();
    }

    @Override
    public boolean read(Empleado t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Empleado> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Empleado> al = new ArrayList();
        String sql = "SELECT emp.*"
                + " FROM employee emp;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String departamento = rs.getString("departamento");
            Date fechaContrato = rs.getDate("fecha_cont");
            int sueldo = rs.getInt("sueldo");
            String posicion = rs.getNString("posicion");
            
            /* Crea el objeto */
            Empleado empleado = new Empleado();

            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setDepartamento(departamento);
            empleado.setFechaContrato(fechaContrato);
            empleado.setSueldo(sueldo);
            empleado.setPosicion(posicion);

            al.add(empleado);
        }

        cx.close();
        return al;
    }

    @Override
    public ArrayList<Empleado> readWhere(String whereSQL) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Empleado> al = new ArrayList();
        String sql = "SELECT *, (SELECT nombre FROM comuna WHERE id = cliente.id_comuna) AS comuna "
                + "FROM cliente WHERE " + whereSQL + ";";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String departamento = rs.getString("departamento");
            Date fechaContrato = rs.getDate("fecha_cont");
            int sueldo = rs.getInt("sueldo");
            String posicion = rs.getNString("posicion");

            /* Crea el objeto */
            Empleado empleado = new Empleado();

            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setDepartamento(departamento);
            empleado.setFechaContrato(fechaContrato);
            empleado.setSueldo(sueldo);
            empleado.setPosicion(posicion);

            al.add(empleado);
        }
        cx.close();
        return al;
    }

    @Override
    public void update(Empleado t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "UPDATE employee SET nombre=?, departamento=?, fecha_cont=?, sueldo=?, posicion=? WHERE id=?;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ps.setString(1, t.getNombre());
        ps.setString(2, t.getDepartamento());
        ps.setDate(3, new java.sql.Date(t.getFechaContrato().getTime()));
        ps.setInt(4, t.getSueldo());
        ps.setString(5, t.getPosicion());
        ps.setInt(6, t.getId());
        ps.executeUpdate();
        cx.close();
    }

    @Override
    public void delete(Empleado t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "DELETE FROM employee WHERE id=?;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ps.setInt(1, t.getId());
        ps.executeUpdate();
        cx.close();
    }

//    public int getComunaByRut(String rut) throws InstantiationException, IllegalAccessException, Exception {
//        Empleado empleado = this.readWhere("rut = '" + rut + "'").get(0);
//        return ((cliente == null) ? 0 : cliente.getComuna().getId());
//    }
}
