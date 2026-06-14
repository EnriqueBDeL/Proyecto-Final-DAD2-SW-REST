<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Asignaturas</title>
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
		$('#curso').val('');
		$('#cuatrimestre').val('1');
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
	function load(id, nombre, curso, cuatrimestre) {
		var existente = document.getElementById("asignatura-" + id);
		if(existente) existente.remove();
		var entry = document.createElement('li');
		entry.id = "asignatura-" + id;
		var aEditar = document.createElement('a');
		aEditar.href = "#";
		aEditar.appendChild(document.createTextNode(" [Editar]"));
		aEditar.onclick = function () {
			$('#id').val(id);
			$('#nombre').val(nombre);
			$('#curso').val(curso);
			$('#cuatrimestre').val(cuatrimestre);
			return false;
		};
		var aBorrar = document.createElement('a');
		aBorrar.href = "#";
		aBorrar.appendChild(document.createTextNode(" [Borrar]"));
		aBorrar.onclick = function () {
			var $enlace = $(this);
			bloquearEnlace($enlace, " [Borrando...]");
			$.ajax({
				url: 'rest/asignatura/' + id,
				type: 'DELETE',
				dataType: "json",
				success: function () {
					document.getElementById("asignatura-" + id).remove();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al borrar la asignatura");
				},
				complete: function () {
					desbloquearEnlace($enlace);
				}
			});
			return false;
		};
		entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " - " + curso + " - Cuatrimestre " + cuatrimestre));
		entry.appendChild(aEditar);
		entry.appendChild(aBorrar);
		$('#asignaturas').append(entry);
	}
	$(document).ready(function () {
		$("#crearAsignatura").click(function () {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var asignaturaInfo = {
				nombre: $('#nombre').val(),
				curso: $('#curso').val(),
				cuatrimestre: parseInt($('#cuatrimestre').val())
			};
			$.ajax({
				data: JSON.stringify(asignaturaInfo),
				url: 'rest/asignatura',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'POST',
				dataType: "json",
				success: function (result) {
					load(result.asignatura.id, result.asignatura.nombre, result.asignatura.curso, result.asignatura.cuatrimestre);
					limpiarFormulario();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al crear la asignatura");
				},
				complete: function () {
					$btn.prop("disabled", false);
				}
			});
		});
		$("#actualizarAsignatura").click(function () {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var asignaturaInfo = {
				id: parseInt($('#id').val()),
				nombre: $('#nombre').val(),
				curso: $('#curso').val(),
				cuatrimestre: parseInt($('#cuatrimestre').val())
			};
			$.ajax({
				data: JSON.stringify(asignaturaInfo),
				url: 'rest/asignatura',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'PUT',
				dataType: "json",
				success: function (result) {
					load(result.asignatura.id, result.asignatura.nombre, result.asignatura.curso, result.asignatura.cuatrimestre);
					limpiarFormulario();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al actualizar la asignatura");
				},
				complete: function () {
					$btn.prop("disabled", false);
				}
			});
		});
		$("#limpiar").click(limpiarFormulario);
		$.ajax({
			url: 'rest/asignatura/listado',
			type: 'GET',
			dataType: "json",
			success: function (result) {
				if(result.asignaturas) {
					jQuery.each(result.asignaturas, function (i, val) {
						load(val.id, val.nombre, val.curso, val.cuatrimestre);
					});
				}
			},
			error: function (jqXhr) {
				mensajeError(jqXhr, "No se pudo cargar la lista de asignaturas");
			}
		});
	});
</script>
</head>
<body>
	<h1>CRUD Asignaturas</h1>
	<p><a href="index.jsp">Volver al Menú Principal</a></p>
	
	Id:<input type="text" id="id" readonly><br>
	Nombre:<input type="text" id="nombre"><br>
	Curso:<input type="text" id="curso" placeholder="Primero, segundo..."><br>
	Cuatrimestre:
	<select id="cuatrimestre">
		<option value="1">1</option>
		<option value="2">2</option>
	</select><br>
	<button id="crearAsignatura">Crear</button>
	<button id="actualizarAsignatura">Actualizar</button>
	<button id="limpiar">Limpiar</button>
	<br><br>
	Listado de asignaturas
	<ul id="asignaturas"></ul>
</body>
</html>