<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Titulacion</title>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function mensajeError(jqXhr, texto) {
		var mensaje = texto || "Error desconocido";
		if(jqXhr.responseJSON && jqXhr.responseJSON.resultado) {
			mensaje = jqXhr.responseJSON.resultado;
		}
		alert("Error " + jqXhr.status + ": " + mensaje);
	}
	function limpiarFormulario() {
		$('#id').val('');
		$('#nombre').val('');
		$('#facultad').val('');
	}
	function bloquearEnlace($enlace, texto) {
		$enlace.data('texto-original', $enlace.text());
		$enlace.text(texto);
		$enlace.css('pointer-events', 'none');
	}
	function desbloquearEnlace($enlace) {
		$enlace.text($enlace.data('texto-original'));
		$enlace.css('pointer-events', 'auto');
	}
	function load(id, nombre, facultad) {
		var existente = document.getElementById("titulacion-" + id);
		if(existente) existente.remove();
		var entry = document.createElement('li');
		entry.id = "titulacion-" + id;
		var aEditar = document.createElement('a');
		aEditar.href = "#";
		aEditar.appendChild(document.createTextNode(" [Editar]"));
		aEditar.onclick = function () {
			$('#id').val(id);
			$('#nombre').val(nombre);
			$('#facultad').val(facultad);
			return false;
		};
		var aBorrar = document.createElement('a');
		aBorrar.href = "#";
		aBorrar.appendChild(document.createTextNode(" [Borrar]"));
		aBorrar.onclick = function () {
			var $enlace = $(this);
			bloquearEnlace($enlace, " [Borrando...]");
			$.ajax({
				url: 'rest/titulacion/' + id,
				type: 'DELETE',
				dataType: "json",
				success: function () {
					document.getElementById("titulacion-" + id).remove();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al borrar la titulacion");
				},
				complete: function () {
					desbloquearEnlace($enlace);
				}
			});
			return false;
		};
		entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " - " + facultad));
		entry.appendChild(aEditar);
		entry.appendChild(aBorrar);
		$('#titulaciones').append(entry);
	}
	$(document).ready(function () {
		$("#crearTitulacion").click(function () {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var titulacionInfo = { nombre: $('#nombre').val(), facultad: $('#facultad').val() };
			$.ajax({
				data: JSON.stringify(titulacionInfo),
				url: 'rest/titulacion',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'POST',
				dataType: "json",
				success: function (result) {
					load(result.titulacion.id, result.titulacion.nombre, result.titulacion.facultad);
					limpiarFormulario();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al crear la titulacion");
				},
				complete: function () {
					$btn.prop("disabled", false);
				}
			});
		});
		$("#actualizarTitulacion").click(function () {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var titulacionInfo = { id: parseInt($('#id').val()), nombre: $('#nombre').val(), facultad: $('#facultad').val() };
			$.ajax({
				data: JSON.stringify(titulacionInfo),
				url: 'rest/titulacion',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'PUT',
				dataType: "json",
				success: function (result) {
					load(result.titulacion.id, result.titulacion.nombre, result.titulacion.facultad);
					limpiarFormulario();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al actualizar la titulacion");
				},
				complete: function () {
					$btn.prop("disabled", false);
				}
			});
		});
		$.ajax({
			url: 'rest/titulacion/listado',
			type: 'GET',
			dataType: "json",
			success: function (result) {
				if(result.titulaciones) {
					jQuery.each(result.titulaciones, function (i, val) {
						load(val.id, val.nombre, val.facultad);
					});
				}
			},
			error: function (jqXhr) {
				mensajeError(jqXhr, "No se pudo cargar la lista de titulaciones");
			}
		});
	});
</script>
</head>
<body>
	<h1>CRUD Titulacion</h1>
	<p><a href="index.jsp">Volver al Menú Principal</a></p>
	<br>
	
	Id:
	<input type="text" id="id" readonly><br>
	
	Nombre:
	<input type="text" id="nombre"><br>
	
	Facultad:
	<input type="text" id="facultad"><br>
	<button id="crearTitulacion">Crear</button>
	<button id="actualizarTitulacion">Actualizar</button>
	
	<br><br>
	Listado de titulaciones
	<ul id="titulaciones"></ul>
</body>
</html>