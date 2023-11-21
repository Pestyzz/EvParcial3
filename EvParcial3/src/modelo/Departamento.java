package modelo;

import dao.DeptDAOImpl;
import dao.CrudDAOable;
import java.util.ArrayList;

public class Departamento {
    private int id;
    private String nombreDept;

    public Departamento() {
    }

    public Departamento(int id, String nombreDept) {
        this.id = id;
        this.nombreDept = nombreDept;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDept() {
        return nombreDept;
    }

    public void setNombreDept(String nombreDept) {
        this.nombreDept = nombreDept;
    }

    @Override
    public String toString() {
        return nombreDept + "[" + id + "]";
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.id;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    public ArrayList read() throws InstantiationException, IllegalAccessException, Exception
    {
        CrudDAOable<Departamento> cDAO = new DeptDAOImpl();
        return cDAO.read();        
    }   
    
}
