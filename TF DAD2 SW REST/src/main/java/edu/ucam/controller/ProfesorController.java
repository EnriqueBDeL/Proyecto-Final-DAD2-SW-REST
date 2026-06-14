package edu.ucam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

import edu.ucam.beans.Profesor;
import edu.ucam.exception.ApiException;
import edu.ucam.services.ProfesorService;
import edu.ucam.utils.ParserObject;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profesor")
public class ProfesorController {

	private ProfesorService ps = new ProfesorService();

	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listado() {
		JSONObject respuesta = new JSONObject();
		for(Profesor p: ps.listar()) {
			respuesta.append("profesores", ParserObject.ProfesorToJSON(p));
		}
		return Response.status(200).entity(respuesta.toString()).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response borraProfesor(@PathParam("id") int id) {
		try {
			ps.eliminar(id);
			return Response.status(200).entity(true).build();
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alta(InputStream inputStream) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String linea = "";
		try {
			while((linea = bReader.readLine()) != null) {
				sb.append(linea);
			}
		} catch (IOException e) {
			return Response.status(500).entity(true).build();
		}

		JSONObject jsonRecibido = new JSONObject(sb.toString());
		if(jsonRecibido.has("id")) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", "No se debe enviar id en el alta");
			return Response.status(409).entity(errorJSON.toString()).build();
		}

		Profesor profesor = ParserObject.JSONToProfesor(jsonRecibido);

		try {
			ps.alta(profesor);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("profesor", ParserObject.ProfesorToJSON(profesor));
		return Response.status(200).entity(respuestaJSON.toString()).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modificar(InputStream inputStream) {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String linea = "";
		try {
			while((linea = bReader.readLine()) != null) {
				sb.append(linea);
			}
		} catch (IOException e) {
			return Response.status(500).entity(true).build();
		}

		JSONObject jsonRecibido = new JSONObject(sb.toString());
		if(!jsonRecibido.has("id")) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", "Se debe enviar el id para modificar");
			return Response.status(400).entity(errorJSON.toString()).build();
		}

		Profesor profesor = ParserObject.JSONToProfesor(jsonRecibido);

		try {
			ps.modificar(profesor);
		} catch(ApiException e) {
			JSONObject errorJSON = new JSONObject();
			errorJSON.put("resultado", e.getMessage());
			return Response.status(e.getHttpCode()).entity(errorJSON.toString()).build();
		}

		JSONObject respuestaJSON = new JSONObject();
		respuestaJSON.put("profesor", ParserObject.ProfesorToJSON(profesor));
		return Response.status(200).entity(respuestaJSON.toString()).build();
	}
}