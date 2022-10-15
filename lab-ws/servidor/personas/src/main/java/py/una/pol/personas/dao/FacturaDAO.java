package py.una.pol.personas.dao;

import py.una.pol.personas.model.Factura;
import py.una.pol.personas.model.Persona;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// Se crea la facturaDao para insertar en la BD
@Stateless
public class FacturaDAO {
 
	
    @Inject
    private Logger log;

	public List<Factura> seleccionar() {
		String query = "SELECT ruc, razon, fecha, monto FROM factura ORDER BY ruc";
		
		List<Factura> lista = new ArrayList<Factura>();
		
		Connection conn = null; 
        try 
        {
        	conn = Bd.connect();
        	ResultSet rs = conn.createStatement().executeQuery(query);

        	while(rs.next()) {
        		Factura f = new Factura();
                f.setRuc(rs.getString(1));
                f.setRazonSocial(rs.getString(2));
                f.setFecha(rs.getString(3));
                f.setMonto(rs.getDouble(4));
        		
        		lista.add(f);
        	}
        	
        } catch (SQLException ex) {
            log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return lista;

	}
	
	public Factura seleccionarPorRuc(String ruc) {
		String SQL = "SELECT ruc, razon, fecha, monto FROM factura WHERE ruc = ? ";

		Factura f = null;

		Connection conn = null;
        try
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setString(1, ruc);

        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
				f = new Factura();
				f.setRuc(rs.getString(1));
				f.setRazonSocial(rs.getString(2));
				f.setFecha(rs.getString(3));
				f.setMonto(rs.getDouble(4));
        	}

        } catch (SQLException ex) {
        	log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return f;

	}
	
	
    public long insertar(Factura p) throws SQLException {

        String SQL = "INSERT INTO factura(ruc, razon, fecha, monto) "
                + "VALUES(?,?,?,?)";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, p.getRuc());
            pstmt.setString(2, p.getRazonSocial());
            pstmt.setString(3, p.getFecha());
			pstmt.setDouble(4, p.getMonto());

 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                	throw ex;
                }
            }
        } catch (SQLException ex) {
        	throw ex;
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        	
        return id;
    	
    	
    }
	

    /*public long actualizar(Persona p) throws SQLException {

        String SQL = "UPDATE persona SET nombre = ? , apellido = ? WHERE cedula = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getApellido());
            pstmt.setLong(3, p.getCedula());
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
        	log.severe("Error en la actualizacion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
        return id;
    }
    
    public long borrar(long cedula) throws SQLException {

        String SQL = "DELETE FROM persona WHERE cedula = ? ";
 
        long id = 0;
        Connection conn = null;
        
        try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, cedula);
 
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                	log.severe("Error en la eliminación: " + ex.getMessage());
                	throw ex;
                }
            }
        } catch (SQLException ex) {
        	log.severe("Error en la eliminación: " + ex.getMessage());
        	throw ex;
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        		throw ef;
        	}
        }
        return id;
    }*/
    

}
