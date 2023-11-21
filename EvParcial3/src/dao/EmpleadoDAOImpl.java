package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import modelo.Empleado;
import modelo.Departamento;

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
        ps.setDate(3, new java.sql.Date(t.getFechaContrato().getTime()));
        ps.setInt(4, t.getSueldo());
        ps.setString(5, t.getPosicion());
        ps.setInt(6, t.getDepartamento().getId());

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
        String sql = "SELECT emp.*, dept.nombre_dept AS departamento "
                + " FROM employee emp"
                + " INNER JOIN department dept ON emp.id_dept = dept.id_dept;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            Date fechaContrato = rs.getDate("fecha_cont");
            int sueldo = rs.getInt("sueldo");
            String posicion = rs.getNString("posicion");
            int idDept = rs.getInt("id_dept");
            String departamento = rs.getString("departamento");

            
            /* Crea el objeto */
            Empleado empleado = new Empleado();

            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setFechaContrato(fechaContrato);
            empleado.setSueldo(sueldo);
            empleado.setPosicion(posicion);
            empleado.setDepartamento(new Departamento(idDept, departamento));


            al.add(empleado);
        }

        cx.close();
        return al;
    }

    @Override
    public ArrayList<Empleado> readWhere(String whereSQL) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Empleado> al = new ArrayList();
        String sql = "SELECT *, (SELECT dept.nombre_dept FROM department dept WHERE id_dept = emp.id_dept) AS departamento "
                + "FROM employee emp WHERE " + whereSQL + ";";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            Date fechaContrato = rs.getDate("fecha_cont");
            int sueldo = rs.getInt("sueldo");
            String posicion = rs.getNString("posicion");
            int idDept = rs.getInt("id_dept");
            String departamento = rs.getString("departamento");

            /* Crea el objeto */
            Empleado empleado = new Empleado();

            empleado.setId(id);
            empleado.setNombre(nombre);
            empleado.setFechaContrato(fechaContrato);
            empleado.setSueldo(sueldo);
            empleado.setPosicion(posicion);
            empleado.setDepartamento(new Departamento(idDept, departamento));

            al.add(empleado);
        }
        cx.close();
        return al;
    }

    @Override
    public void update(Empleado t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        String sql = "UPDATE employee emp SET emp.nombre=?, emp.sueldo=?, emp.posicion=?, emp.id_dept=? WHERE emp.id=?;";
        Connection cx = Conexion.open();
        PreparedStatement ps = cx.prepareStatement(sql);
        ps.setString(1, t.getNombre());
        //ps.setDate(2, new java.sql.Date(t.getFechaContrato().getTime()));
        ps.setInt(2, t.getSueldo());
        ps.setString(3, t.getPosicion());
        ps.setInt(4, t.getDepartamento().getId());
        ps.setInt(5, t.getId());
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

   public int getDeptById(int id) throws InstantiationException, IllegalAccessException, Exception {
       Empleado empleado = this.readWhere("id = '" + id + "'").get(0);
       return ((empleado == null) ? 0 : empleado.getDepartamento().getId());
   }
}
