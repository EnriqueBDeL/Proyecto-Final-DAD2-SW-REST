package edu.ucam.utils;

import org.json.JSONObject;

import edu.ucam.beans.Asignacion;
import edu.ucam.beans.Asignatura;
import edu.ucam.beans.Profesor;
import edu.ucam.beans.Titulacion;
import edu.ucam.database.DataBaseAsignacion;

public class ParserObject {

	public static Titulacion JSONToTitulacion(JSONObject titulacionJson) {
		Titulacion titulacion = new Titulacion();

		if(titulacionJson.has("id"))
			titulacion.setId(titulacionJson.getInt("id"));
		titulacion.setNombre(titulacionJson.getString("nombre"));
		titulacion.setFacultad(titulacionJson.getString("facultad"));

		return titulacion;
	}

	public static JSONObject TitulacionToJSON(Titulacion titu) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", titu.getId());
		jsonObject.put("nombre", titu.getNombre());
		jsonObject.put("facultad", titu.getFacultad());
	
		return jsonObject;
	}




	public static Asignatura JSONToAsignatura(JSONObject asignaturaJson) {
		int id = asignaturaJson.has("id") ? asignaturaJson.getInt("id") : 0;
		String nombre = asignaturaJson.has("nombre") ? asignaturaJson.getString("nombre") : "";
		String curso = asignaturaJson.has("curso") ? asignaturaJson.getString("curso") : "";
		int cuatrimestre = asignaturaJson.has("cuatrimestre") ? asignaturaJson.getInt("cuatrimestre") : 1;
		
		return new Asignatura(id, nombre, curso, cuatrimestre);
	}
	
	public static JSONObject AsignaturaToJSON(Asignatura asig) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", asig.getId());
		jsonObject.put("nombre", asig.getNombre());
		jsonObject.put("curso", asig.getCurso());
		jsonObject.put("cuatrimestre", asig.getCuatrimestre());
		Integer idProfesor = DataBaseAsignacion.dameProfesorAsignado(asig.getId());
		if(idProfesor != null) {
			jsonObject.put("idProfesor", idProfesor);
		}
		return jsonObject;
	}

	public static Asignacion JSONToAsignacion(JSONObject asignacionJson) {
		Asignacion asignacion = new Asignacion();
		asignacion.setIdAsignatura(asignacionJson.getInt("idAsignatura"));
		asignacion.setIdProfesor(asignacionJson.getInt("idProfesor"));
		return asignacion;
	}

	public static JSONObject AsignacionToJSON(Asignacion asignacion) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("idAsignatura", asignacion.getIdAsignatura());
		jsonObject.put("idProfesor", asignacion.getIdProfesor());
		return jsonObject;
	}

	
	public static Profesor JSONToProfesor(JSONObject profesorJson) {
		Profesor profesor = new Profesor();
		if(profesorJson.has("id")) {
			profesor.setId(profesorJson.getInt("id"));
		}
		profesor.setNombre(profesorJson.getString("nombre"));
		profesor.setApellidos(profesorJson.getString("apellidos"));
		profesor.setDepartamento(profesorJson.getString("departamento"));
		return profesor;
	}

	public static JSONObject ProfesorToJSON(Profesor prof) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", prof.getId());
		jsonObject.put("nombre", prof.getNombre());
		jsonObject.put("apellidos", prof.getApellidos());
		jsonObject.put("departamento", prof.getDepartamento());
		return jsonObject;
	}

}