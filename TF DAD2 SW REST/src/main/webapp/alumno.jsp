<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>CRUD Alumno</title>
	<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
	<script type="text/javascript">


	function load(id, nombre, apellido1) {
			var existente = document.getElementById(id);
			if (existente) existente.remove();

			var entry = document.createElement('li');

			var aEditar = document.createElement('a');
			var linkEditar = document.createTextNode(" [Editar]");
			aEditar.appendChild(linkEditar);
			aEditar.href = "#";
			aEditar.onclick = function (e) {
				e.preventDefault(); 
				$('#id').val(id);
				$('#nombre').val(nombre);
				$('#apellido1').val(apellido1);
			};

			var aBorrar = document.createElement('a');
			var linkBorrar = document.createTextNode(" [Borrar]");
			aBorrar.appendChild(linkBorrar);
			aBorrar.href = "#";
			aBorrar.onclick = function (e) {
				e.preventDefault();
				$.ajax({
					url: 'rest/alumno/' + id,
					type: 'DELETE',
					dataType: "json",
					success: function (result) {
						document.getElementById(id).remove();
						if($('#id').val() == id) {
							$('#id').val('');
							$('#nombre').val('');
							$('#apellido1').val('');
						}
					},
					error: function (jqXhr, textStatus, errorMessage) {
						var mensaje = "Error desconocido al borrar.";
						if (jqXhr.status === 404) mensaje = "El alumno ya no existe en el servidor.";
						alert("Error " + jqXhr.status + ": " + mensaje);
					}
				});
			};

			entry.id = id;
			entry.appendChild(document.createTextNode("(" + id + ") " + nombre + " " + apellido1));
			entry.appendChild(aEditar);
			entry.appendChild(aBorrar);

			$('#alumnos').append(entry);
		}

		$(document).ready(function () {

			
			$.ajax({
				url: 'rest/alumno/listado',
				type: 'GET',
				dataType: "json",
				success: function (result) {
					if (result.alumnos) {
						jQuery.each(result.alumnos, function (i, val) {
							load(val.id, val.nombre, val.apellido1);
						});
					}
				},
				error: function (jqXhr) {
					alert("Error " + jqXhr.status + ": No se pudo cargar la lista de alumnos.");
				}
			});

			
			$("#crearAlumno").click(function () {
				var $btn = $(this);
				$btn.prop("disabled", true); 

				var alumnoInfo = { nombre: $('#nombre').val(), apellido1: $('#apellido1').val() };

				$.ajax({
					data: JSON.stringify(alumnoInfo),
					url: 'rest/alumno',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					type: 'POST',
					dataType: "json",
					success: function (result) {
						console.log(result);
						load(result.alumno.id, result.alumno.nombre, result.alumno.apellido1);
						$('#nombre').val('');
						$('#apellido1').val('');
					},
					error: function (jqXhr, textStatus, errorMessage) {
						var mensaje = "Error al crear el alumno.";
						if (jqXhr.status === 409) {
							mensaje = "Conflicto: No se debe enviar ID al crear un alumno.";
						} else if (jqXhr.status === 500) {
							mensaje = "Error interno del servidor.";
						}
						alert("Error " + jqXhr.status + ": " + mensaje);
					},
					complete: function () {
						$btn.prop("disabled", false); 
					}
				});
			});

		
			$("#actualizarAlumno").click(function () {
				var idActual = $('#id').val();
				if (!idActual) {
					alert("Error: Debes seleccionar un alumno de la lista para actualizarlo.");
					return; 
				}

				var $btn = $(this);
				$btn.prop("disabled", true); 

				var alumnoInfo = { id: parseInt(idActual), nombre: $('#nombre').val(), apellido1: $('#apellido1').val() };

				$.ajax({
					data: JSON.stringify(alumnoInfo),
					url: 'rest/alumno',
					headers: {
						'Accept': 'application/json',
						'Content-Type': 'application/json'
					},
					type: 'PUT',
					dataType: "json",
					success: function (result) {
						console.log(result);
						load(result.alumno.id, result.alumno.nombre, result.alumno.apellido1);
						$('#id').val('');
						$('#nombre').val('');
						$('#apellido1').val('');
					},
					error: function (jqXhr, textStatus, errorMessage) {
						var mensaje = "Error al actualizar el alumno.";
						if (jqXhr.status === 404) {
							mensaje = "El alumno que intentas modificar no existe.";
						} else if (jqXhr.status === 409) {
							mensaje = "Conflicto: Falta el ID del alumno.";
						}
						alert("Error " + jqXhr.status + ": " + mensaje);
					},
					complete: function () {
						$btn.prop("disabled", false); 
					}
				});
			});

		});
	</script>
</head>

<body>
	<h1>CRUD Alumnos</h1>
	<br>
	<a href="index.jsp">Volver al Menú Principal</a>
	<br><br>
	Formulario para gestionar alumnos.<br>
	Id: <input type="text" id="id" readonly placeholder="Autogenerado"><br>
	Nombre: <input type="text" id="nombre"><br>
	Apellido: <input type="text" id="apellido1"><br>
	<button id="crearAlumno">Crear</button>
	<button id="actualizarAlumno">Actualizar</button>

	<br><br>
	Listado de alumnos:
	<br>
	<ul id="alumnos">
	</ul>

</body>

</html>