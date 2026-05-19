package edu.ucam.beans;

public class Asignatura {

	private int id;
	private String nombre;
	private String curso; // Ejemplo: primero,segundo,...
	private int cuatrimestre;
	
	
	public Asignatura(int id, String nombre, String curso, int cuatrimestre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.cuatrimestre = cuatrimestre;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public int getCuatrimestre() {
		return cuatrimestre;
	}
	public void setCuatrimestre(int cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}
	
	
	
}
