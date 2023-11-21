package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import java.util.ArrayList;
import modelo.Departamento;

import db.Conexion;

public class DeptDAOImpl implements CrudDAOable<Departamento>{
    @Override
    public void create(Departamento t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    @Override
    public ArrayList<Departamento> read() throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        ArrayList<Departamento> al = new ArrayList();
        String sql = "select * from department;";
        Connection cx = Conexion.open();
        PreparedStatement ps =  cx.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            int id = rs.getInt("id_dept");
            String nombre = rs.getString("nombre_dept");
            
            Departamento comuna = new Departamento(id, nombre);
            al.add(comuna);
        }        
        cx.close();
        return al;
    }

    @Override
    public ArrayList<Departamento> readWhere(String whereSQL) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean read(Departamento t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Departamento t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Departamento t) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
