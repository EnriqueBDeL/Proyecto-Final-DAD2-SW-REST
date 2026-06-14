<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD Profesores</title>
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
		$('#apellidos').val('');
		$('#departamento').val('');
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
	function load(id, nombre, apellidos, departamento) {
		var existente = document.getElementById("profesor-" + id);
		if(existente) existente.remove();
		var entry = document.createElement('li');
		entry.id = "profesor-" + id;
		var aEditar = document.createElement('a');
		aEditar.href = "#";
		aEditar.appendChild(document.createTextNode(" [Editar]"));
		aEditar.onclick = function () {
			$('#id').val(id);
			$('#nombre').val(nombre);
			$('#apellidos').val(apellidos);
			$('#departamento').val(departamento);
			return false;
		};
		var aBorrar = document.createElement('a');
		aBorrar.href = "#";
		aBorrar.appendChild(document.createTextNode(" [Borrar]"));
		aBorrar.onclick = function () {
			var $enlace = $(this);
			bloquearEnlace($enlace, " [Borrando...]");
			$.ajax({
				url: 'rest/profesor/' + id,
				type: 'DELETE',
				dataType: "json",
				success: function () {
					document.getElementById("profesor-" + id).remove();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al borrar el profesor");
				},
				complete: function () {
					desbloquearEnlace($enlace);
				}
			});
			return false;
		};
		entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " " + apellidos + " - " + departamento));
		entry.appendChild(aEditar);
		entry.appendChild(aBorrar);
		$('#profesores').append(entry);
	}
	$(document).ready(function () {
		$("#crearProfesor").click(function () {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var profesorInfo = {
				nombre: $('#nombre').val(),
				apellidos: $('#apellidos').val(),
				departamento: $('#departamento').val()
			};
			$.ajax({
				data: JSON.stringify(profesorInfo),
				url: 'rest/profesor',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'POST',
				dataType: "json",
				success: function (result) {
					load(result.profesor.id, result.profesor.nombre, result.profesor.apellidos, result.profesor.departamento);
					limpiarFormulario();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al crear el profesor");
				},
				complete: function () {
					$btn.prop("disabled", false);
				}
			});
		});
		$("#actualizarProfesor").click(function () {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var profesorInfo = {
				id: parseInt($('#id').val()),
				nombre: $('#nombre').val(),
				apellidos: $('#apellidos').val(),
				departamento: $('#departamento').val()
			};
			$.ajax({
				data: JSON.stringify(profesorInfo),
				url: 'rest/profesor',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'PUT',
				dataType: "json",
				success: function (result) {
					load(result.profesor.id, result.profesor.nombre, result.profesor.apellidos, result.profesor.departamento);
					limpiarFormulario();
				},
				error: function (jqXhr) {
					mensajeError(jqXhr, "Error al actualizar el profesor");
				},
				complete: function () {
					$btn.prop("disabled", false);
				}
			});
		});
		$("#limpiar").click(limpiarFormulario);
		$.ajax({
			url: 'rest/profesor/listado',
			type: 'GET',
			dataType: "json",
			success: function (result) {
				if(result.profesores) {
					jQuery.each(result.profesores, function (i, val) {
						load(val.id, val.nombre, val.apellidos, val.departamento);
					});
				}
			},
			error: function (jqXhr) {
				mensajeError(jqXhr, "No se pudo cargar la lista de profesores");
			}
		});
	});
</script>
</head>
<body>
	<h1>CRUD Profesores</h1>
	<p><a href="index.jsp">Volver al Menú Principal</a></p>
	
	Id:
	<input type="text" id="id" readonly><br>
	
	Nombre:
	<input type="text" id="nombre"><br>
	
	Apellidos:
	<input type="text" id="apellidos"><br>
	
	Departamento:
	<input type="text" id="departamento"><br>
	<button id="crearProfesor">Crear</button>
	<button id="actualizarProfesor">Actualizar</button>
	<button id="limpiar">Limpiar</button>
	
	<br><br>
	Listado de profesores
	<ul id="profesores"></ul>
</body>
</html>