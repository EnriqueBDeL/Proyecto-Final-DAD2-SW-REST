package edu.ucam.services;

import java.util.List;
import edu.ucam.beans.Asignatura;
import edu.ucam.database.DataBaseAsignatura;
import edu.ucam.exception.BadRequestException;
import edu.ucam.exception.ConflictException;
import edu.ucam.exception.NotFoundException;

public class AsignaturaService {

	
	
	public List<Asignatura> listar() {
		return DataBaseAsignatura.listar();
	}
	
	

	public Asignatura obtenerPorId(int id) throws NotFoundException {
		Asignatura asignatura = DataBaseAsignatura.dameAsignaturaPorId(id);
		if(asignatura == null) {
			throw new NotFoundException("No existe la asignatura con id " + id);
		}
		return asignatura;
	}

	public boolean alta(Asignatura asignatura) throws ConflictException, BadRequestException {
		validarCampos(asignatura);
		
		if(DataBaseAsignatura.dameAsignaturaPorId(asignatura.getId()) != null) {
			throw new ConflictException("Ya existe una asignatura con id " + asignatura.getId());
		}
		return DataBaseAsignatura.alta(asignatura);
	}
	
	

	public boolean modificar(Asignatura asignatura) throws NotFoundException, BadRequestException {
		validarCampos(asignatura);
		
	    if(DataBaseAsignatura.dameAsignaturaPorId(asignatura.getId()) == null) {
	        throw new NotFoundException("No existe la asignatura con id " + asignatura.getId() + " para modificar");
	    }
	    return DataBaseAsignatura.modificar(asignatura); 
	}

	public boolean eliminar(int id) throws NotFoundException {
		if(DataBaseAsignatura.dameAsignaturaPorId(id) == null) {
			throw new NotFoundException("No existe la asignatura con id " + id + " para eliminar");
		}
		return DataBaseAsignatura.remove(id);
	}
	
	
	
	private void validarCampos(Asignatura asignatura) throws BadRequestException {
		if (asignatura.getNombre() == null || asignatura.getNombre().trim().isEmpty()) {
			throw new BadRequestException("El nombre de la asignatura no puede estar vacío.");
		}
		if (asignatura.getCurso() == null || asignatura.getCurso().trim().isEmpty()) {
			throw new BadRequestException("El curso no puede estar vacío.");
		}
		if (asignatura.getCuatrimestre() != 1 && asignatura.getCuatrimestre() != 2) {
			throw new BadRequestException("El cuatrimestre debe ser 1 o 2.");
		}
	}
	
	
	
}