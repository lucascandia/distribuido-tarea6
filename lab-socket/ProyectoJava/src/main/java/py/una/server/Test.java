package py.una.server;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import py.una.bd.PersonaDAO;
import py.una.entidad.Persona;

public class Test {

	
	
	public static void main(String args[]) throws SQLException{
		
		PersonaDAO pdao = new PersonaDAO();
		
		pdao.insertar(new Persona(125L,"Raquel", "Gomez") );
		
		List<Persona> lista = pdao.seleccionar(null);
		
		for (Persona p: lista){
			System.out.println(p.getCedula() + " " + p.getNombre() + " " + p.getApellido());
		}
	}
	
	
}
