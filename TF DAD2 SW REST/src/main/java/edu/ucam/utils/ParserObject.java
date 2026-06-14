package edu.ucam.utils;

import org.json.JSONObject;

import edu.ucam.beans.Alumno;
import edu.ucam.beans.Asignatura;
import edu.ucam.beans.Profesor;
import edu.ucam.beans.Titulacion;

public class ParserObject {

	public static Alumno JSONToAlumno(JSONObject alumnoJson) {
		Alumno alumno = new Alumno();
		
		if(alumnoJson.has("id"))
			alumno.setId(alumnoJson.getInt("id"));
		alumno.setNombre(alumnoJson.getString("nombre"));
		alumno.setApellido1(alumnoJson.getString("apellido1"));
			
		return alumno;
	}
	
	public static JSONObject AlumnoToJSON(Alumno alu) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", alu.getId());
		jsonObject.put("nombre", alu.getNombre());
		jsonObject.put("apellido1", alu.getApellido1());
	
		return jsonObject;
	}
	
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
