package edu.ucam.beans;

public class Asignacion {

	private int idAsignatura;
	private int idProfesor;

	public Asignacion() {
		super();
	}

	public Asignacion(int idAsignatura, int idProfesor) {
		super();
		this.idAsignatura = idAsignatura;
		this.idProfesor = idProfesor;
	}

	public int getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(int idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public int getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}
}