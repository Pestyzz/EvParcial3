package modelo;

import dao.EmpleadoDAOImpl;
import dao.CrudDAOable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public final class Empleado {
    private String id;
    private String nombre;
    private String departamento;
    private Date fechaContrato;
    private int sueldo;
    private String posicion;

    public Empleado() {
    }

    public Empleado(String id, String nombre, String departamento, Date fechaContrato, int sueldo, String posicion) {
        this.setId(id);
        this.setNombre(nombre);
        this.setDepartamento(departamento);
        this.setFechaContrato(fechaContrato);
        this.setSueldo(sueldo);
        this.setPosicion(posicion);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", fechaContrato=" + fechaContrato + ", sueldo=" + sueldo + ", posicion=" + posicion + '}';
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
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public void create(Empleado e) throws InstantiationException, IllegalAccessException, Exception
    {
        CrudDAOable<Empleado> cDAO = new EmpleadoDAOImpl();
        cDAO.create(e);           
    }
  
    public ArrayList<Cliente> read() throws InstantiationException, IllegalAccessException, Exception
    {        
        CrudDAOable<Cliente> cDAO = new ClienteDAOImpl();
        return cDAO.read();   
    }
    
    public ArrayList<Cliente> readConWhere(String whereSQL) throws InstantiationException, IllegalAccessException, Exception
    {        
        CrudDAOable<Cliente> cDAO = new ClienteDAOImpl();
        return cDAO.readWhere(whereSQL);
    }
    
    public void update(Cliente c) throws InstantiationException, IllegalAccessException, Exception
    {
        CrudDAOable<Cliente> cDAO = new ClienteDAOImpl();
        cDAO.update(c);           
    }    


    public void delete(Cliente c) throws InstantiationException, IllegalAccessException, Exception
    {
        CrudDAOable<Cliente> cDAO = new ClienteDAOImpl();
        cDAO.delete(c);           
    }        
    
    public int getComunaByRut(String rut) throws IllegalAccessException, Exception
    {
        ClienteDAOImpl cDAO = new ClienteDAOImpl();
        return cDAO.getComunaByRut(rut);
    }
    
    public Cliente getCliente(Cliente cli) throws InstantiationException, IllegalAccessException, Exception
    {
        ClienteDAOImpl cDAO = new ClienteDAOImpl();
        return cDAO.readWhere("rut = '" + cli.getRut()+ "'").get(0);
    }
}
