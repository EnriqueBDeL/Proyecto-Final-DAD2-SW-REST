package edu.ucam.services;

import java.util.List;
import edu.ucam.beans.Profesor;
import edu.ucam.database.DataBaseProfesor;
import edu.ucam.exception.BadRequestException;
import edu.ucam.exception.ConflictException;
import edu.ucam.exception.NotFoundException;

public class ProfesorService {

	public List<Profesor> listar() {
		return DataBaseProfesor.listar();
	}

	public Profesor obtenerPorId(int id) throws NotFoundException {
		Profesor profesor = DataBaseProfesor.dameProfesorPorId(id);
		if(profesor == null) {
			throw new NotFoundException("No existe el profesor con id " + id);
		}
		return profesor;
	}

	public boolean alta(Profesor profesor) throws ConflictException, BadRequestException {
		validarCampos(profesor);
		if(DataBaseProfesor.dameProfesorPorId(profesor.getId()) != null) {
			throw new ConflictException("Ya existe un profesor con id " + profesor.getId());
		}
		return DataBaseProfesor.alta(profesor);
	}

	public boolean modificar(Profesor profesor) throws NotFoundException, BadRequestException {
		validarCampos(profesor);
		if(DataBaseProfesor.dameProfesorPorId(profesor.getId()) == null) {
			throw new NotFoundException("No existe el profesor para modificar.");
		}
		return DataBaseProfesor.modificar(profesor);
	}

	public boolean eliminar(int id) throws NotFoundException {
		if(DataBaseProfesor.dameProfesorPorId(id) == null) {
			throw new NotFoundException("No existe el profesor para eliminar.");
		}
		return DataBaseProfesor.remove(id);
	}

	private void validarCampos(Profesor profesor) throws BadRequestException {
		if (profesor.getNombre() == null || profesor.getNombre().trim().isEmpty()) {
			throw new BadRequestException("El nombre no puede estar vacío.");
		}
		if (profesor.getApellidos() == null || profesor.getApellidos().trim().isEmpty()) {
			throw new BadRequestException("Los apellidos no pueden estar vacíos.");
		}
	}
}