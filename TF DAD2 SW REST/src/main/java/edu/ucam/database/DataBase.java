package edu.ucam.database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.Alumno;

public class DataBase {
	
	public static Map<Integer, Alumno> listaAlumnos = new Hashtable<Integer, Alumno>();
	
	public static boolean alta(Alumno alumno) {
		alumno.setId(siguienteId());
		listaAlumnos.put(alumno.getId(), alumno);
		return true;
	} 
	
	public static boolean remove(int id) {
		if(listaAlumnos.containsKey(id)) {
			listaAlumnos.remove(id);
			return true;
		}
		return false;
	}
	
	public static Alumno dameAlumnoPorId(int id) {
		return listaAlumnos.get(id);
	}

	public static List<Alumno> listar() {
		List<Alumno> lista = new ArrayList<Alumno>();
		for(Alumno a: listaAlumnos.values()) {
			lista.add(a);
		}
		return lista;
	}

	public static boolean modificar(Alumno alumno) {
	    if(listaAlumnos.containsKey(alumno.getId())) {
	        listaAlumnos.put(alumno.getId(), alumno);
	        return true;
	    }
	    return false;
	}
	
	private static int siguienteId() {
		int maximo = 0;
		for(Alumno a: listaAlumnos.values()) {
			if(a.getId() > maximo)
				maximo = a.getId();
		}
		return ++maximo;
	}
}
