package edu.ucam.database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import edu.ucam.beans.Profesor;

public class DataBaseProfesor {

	public static Map<Integer, Profesor> listaProfesores = new Hashtable<Integer, Profesor>();

	public static synchronized boolean alta(Profesor profesor) {
		profesor.setId(siguienteId());
		listaProfesores.put(profesor.getId(), profesor);
		return true;
	}

	public static boolean modificar(Profesor profesor) {
		if(listaProfesores.containsKey(profesor.getId())) {
			listaProfesores.put(profesor.getId(), profesor);
			return true;
		}
		return false;
	}

	public static boolean remove(int id) {
		if(listaProfesores.containsKey(id)) {
			listaProfesores.remove(id);
			return true;
		}
		return false;
	}

	public static Profesor dameProfesorPorId(int id) {
		return listaProfesores.get(id);
	}

	public static List<Profesor> listar() {
		List<Profesor> lista = new ArrayList<Profesor>();
		for(Profesor p: listaProfesores.values()) {
			lista.add(p);
		}
		return lista;
	}
	
	private static synchronized int siguienteId() {
		int maximo = 0;
		for(Profesor p: listaProfesores.values()) {
			if(p.getId() > maximo) maximo = p.getId();
		}
		return ++maximo;
	}
}