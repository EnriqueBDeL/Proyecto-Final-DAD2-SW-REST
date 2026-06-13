package edu.ucam.services;

import java.util.List;

import edu.ucam.beans.Titulacion;
import edu.ucam.database.DataBaseTitulacion;
import edu.ucam.exception.ConflictException;
import edu.ucam.exception.NotFoundException;
import edu.ucam.exception.BadRequestException; 
public class TitulacionService {

	public List<Titulacion> listar() {
		return DataBaseTitulacion.listar();
	}

	public Titulacion obtenerPorId(int id) throws NotFoundException {
		Titulacion titulacion = DataBaseTitulacion.dameTitulacionPorId(id);
		if(titulacion == null) {
			throw new NotFoundException("No existe la titulacion con id " + id);
		}
		return titulacion;
	}

	public boolean alta(Titulacion titulacion) throws ConflictException, BadRequestException {
		validarCampos(titulacion);
		
		if(DataBaseTitulacion.dameTitulacionPorId(titulacion.getId()) != null) {
			throw new ConflictException("Ya existe una titulacion con id " + titulacion.getId());
		}
		return DataBaseTitulacion.alta(titulacion);
	}

	public boolean modificar(Titulacion titulacion) throws NotFoundException, BadRequestException {
		validarCampos(titulacion);
		
	    if(DataBaseTitulacion.dameTitulacionPorId(titulacion.getId()) == null) {
	        throw new NotFoundException("No existe la titulacion con id " + titulacion.getId() + " para modificar");
	    }
	    return DataBaseTitulacion.modificar(titulacion); 
	}

	public boolean eliminar(int id) throws NotFoundException {
		if(DataBaseTitulacion.dameTitulacionPorId(id) == null) {
			throw new NotFoundException("No existe la titulacion con id " + id + " para eliminar");
		}
		return DataBaseTitulacion.remove(id);
	}
	
	
	private void validarCampos(Titulacion titulacion) throws BadRequestException {
		if (titulacion.getNombre() == null || titulacion.getNombre().trim().isEmpty()) {
			throw new BadRequestException("El nombre de la titulación no puede estar vacío.");
		}
		if (titulacion.getFacultad() == null || titulacion.getFacultad().trim().isEmpty()) {
			throw new BadRequestException("La facultad no puede estar vacía.");
		}
	}
}
