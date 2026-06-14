package edu.ucam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import edu.ucam.beans.Asignacion;
import edu.ucam.beans.Asignatura;
import edu.ucam.beans.Profesor;
import edu.ucam.database.DataBaseAsignatura;
import edu.ucam.database.DataBaseProfesor;
import edu.ucam.exception.ApiException;
import edu.ucam.services.AsignacionService;
import edu.ucam.utils.ParserObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/asignacion")
public class AsignacionController {

	private AsignacionService as = new AsignacionService();

	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuesta = new JSONObject();
		for(Asignacion asignacion: as.listar()) {
			JSONObject item = ParserObject.AsignacionToJSON(asignacion);
			Asignatura asignatura = DataBaseAsignatura.dameAsignaturaPorId(asignacion.getIdAsignatura());
			Profesor profesor = DataBaseProfesor.dameProfesorPorId(asignacion.getIdProfesor());
			if(asignatura != null) {
				item.put("asignatura", ParserObject.AsignaturaToJSON(asignatura));
			}
			if(profesor != null) {
				item.put("profesor", ParserObject.ProfesorToJSON(profesor));
			}
			respuesta.append("asignaciones", item);
		}
		return Response.status(200).entity(respuesta.toString()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alta(InputStream inputStream) {
		Asignacion asignacion = ParserObject.JSONToAsignacion(leerJson(inputStream));
		try {
			as.alta(asignacion);
		} catch(ApiException e) {
			return error(e);
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("asignacion", ParserObject.AsignacionToJSON(asignacion));
		return Response.status(200).entity(respuestaJSON.toString()).build();
	}

	@DELETE
	@Path("/{idAsignatura}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminar(@PathParam("idAsignatura") int idAsignatura) {
		try {
			as.eliminar(idAsignatura);
			return Response.status(200).entity(true).build();
		} catch(ApiException e) {
			return error(e);
		}
	}

	private JSONObject leerJson(InputStream inputStream) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String linea = "";
		try {
			while((linea = bReader.readLine()) != null) {
				sb.append(linea);
			}
		} catch(IOException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", "Error leyendo la peticion");
			return errorJSON;
		}
		return new JSONObject(sb.toString());
	}

	private Response error(ApiException e) {
		JSONObject errorJSON = new JSONObject();
		errorJSON.put("resultado", e.getMessage());
		return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();