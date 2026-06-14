package edu.ucam.database;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import edu.ucam.beans.Asignacion;

public class DataBaseAsignacion {

	private static Map<Integer, Asignacion> listaAsignaciones = new Hashtable<Integer, Asignacion>();

	public static boolean alta(Asignacion asignacion) {
		listaAsignaciones.put(asignacion.getIdAsignatura(), asignacion);
		return true;
	}

	public static boolean remove(int idAsignatura) {
		if(listaAsignaciones.containsKey(idAsignatura)) {
			listaAsignaciones.remove(idAsignatura);
			return true;
		}
		return false;
	}

	public static Asignacion dameAsignacionPorAsignatura(int idAsignatura) {
		return listaAsignaciones.get(idAsignatura);
	}

	public static Integer dameProfesorAsignado(int idAsignatura) {
		Asignacion asignacion = listaAsignaciones.get(idAsignatura);
		if(asignacion == null) {
			return null;
		}
		return asignacion.getIdProfesor();
	}

	public static List<Asignacion> listar() {
		return new ArrayList<Asignacion>(listaAsignaciones.values());
	}
}