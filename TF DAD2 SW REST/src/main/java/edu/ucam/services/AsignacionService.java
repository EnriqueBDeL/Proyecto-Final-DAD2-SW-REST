package edu.ucam.services;

import java.util.List;

import edu.ucam.beans.Asignacion;
import edu.ucam.database.DataBaseAsignacion;
import edu.ucam.database.DataBaseAsignatura;
import edu.ucam.database.DataBaseProfesor;
import edu.ucam.exception.BadRequestException;
import edu.ucam.exception.NotFoundException;

public class AsignacionService {

	public List<Asignacion> listar() {
		return DataBaseAsignacion.listar();
	}

	public boolean alta(Asignacion asignacion) throws BadRequestException, NotFoundException {
		validarCampos(asignacion);
		if(DataBaseAsignatura.dameAsignaturaPorId(asignacion.getIdAsignatura()) == null) {
			throw new NotFoundException("No existe la asignatura con id " + asignacion.getIdAsignatura());
		}
		if(DataBaseProfesor.dameProfesorPorId(asignacion.getIdProfesor()) == null) {
			throw new NotFoundException("No existe el profesor con id " + asignacion.getIdProfesor());
		}
		return DataBaseAsignacion.alta(asignacion);
	}

	public boolean eliminar(int idAsignatura) throws NotFoundException {
		if(DataBaseAsignatura.dameAsignaturaPorId(idAsignatura) == null) {
			throw new NotFoundException("No existe la asignatura con id " + idAsignatura);
		}
		if(DataBaseAsignacion.dameAsignacionPorAsignatura(idAsignatura) == null) {
			throw new NotFoundException("La asignatura no tiene profesor asignado.");
		}
		return DataBaseAsignacion.remove(idAsignatura);
	}

	private void validarCampos(Asignacion asignacion) throws BadRequestException {
		if(asignacion.getIdAsignatura() <= 0) {
			throw new BadRequestException("El id de asignatura debe ser mayor que cero.");
		}
		if(asignacion.getIdProfesor() <= 0) {
			throw new BadRequestException("El id de profesor debe ser mayor que cero.");
		}
	}
}