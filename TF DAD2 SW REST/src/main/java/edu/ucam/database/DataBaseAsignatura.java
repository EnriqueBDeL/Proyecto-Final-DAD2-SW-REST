package edu.ucam.database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import edu.ucam.beans.Asignatura;

public class DataBaseAsignatura {


	public static Map<Integer, Asignatura> listaAsignaturas = new Hashtable<Integer, Asignatura>();

	
	public static synchronized boolean alta(Asignatura asignatura) {
		asignatura.setId(siguienteId());
		listaAsignaturas.put(asignatura.getId(), asignatura);
		return true;
	}
	
	
	public static boolean remove(int id) {
		if(listaAsignaturas.containsKey(id)) {
			listaAsignaturas.remove(id);
			return true;
		}
		return false;
	}

	public static Asignatura dameAsignaturaPorId(int id) {
		return listaAsignaturas.get(id);
	}

	public static List<Asignatura> listar() {
		List<Asignatura> lista = new ArrayList<Asignatura>();
		for(Asignatura a: listaAsignaturas.values()) {
			lista.add(a);
		}
		return lista;
	}
	
	
	public static boolean modificar(Asignatura asignatura) {
	    if(listaAsignaturas.containsKey(asignatura.getId())) {
	        listaAsignaturas.put(asignatura.getId(), asignatura);
	        return true;
	    }
	    return false;
	}
	
	private static synchronized int siguienteId() {
		int maximo = 0;
		for(Asignatura a: listaAsignaturas.values()) {
			if(a.getId() > maximo)
				maximo = a.getId();
		}
		return ++maximo;
	}
}