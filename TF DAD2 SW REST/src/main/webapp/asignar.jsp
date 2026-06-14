<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asignar Profesores a Asignaturas</title>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	function mensajeError(jqXhr, texto) {
		var mensaje = texto || "Error desconocido";
		if(jqXhr.responseJSON && jqXhr.responseJSON.resultado) {
			mensaje = jqXhr.responseJSON.resultado;
		}
		alert("Error " + jqXhr.status + ": " + mensaje);
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

	function cargarAsignaturas() {
		$('#idAsignatura').empty();
		$.ajax({
			url: 'rest/asignatura/listado',
			type: 'GET',
			dataType: 'json',
			success: function(result) {
				if(result.asignaturas) {
					jQuery.each(result.asignaturas, function(i, val) {
						$('#idAsignatura').append('<option value="' + val.id + '">' + val.nombre + ' (' + val.id + ')</option>');
					});
				}
			},
			error: function(jqXhr) {
				mensajeError(jqXhr, "No se pudo cargar la lista de asignaturas");
			}
		});
	}

	function cargarProfesores() {
		$('#idProfesor').empty();
		$.ajax({
			url: 'rest/profesor/listado',
			type: 'GET',
			dataType: 'json',
			success: function(result) {
				if(result.profesores) {
					jQuery.each(result.profesores, function(i, val) {
						$('#idProfesor').append('<option value="' + val.id + '">' + val.nombre + ' ' + val.apellidos + ' (' + val.id + ')</option>');
					});
				}
			},
			error: function(jqXhr) {
				mensajeError(jqXhr, "No se pudo cargar la lista de profesores");
			}
		});
	}

	function cargarAsignaciones() {
		$('#asignaciones').empty();
		$.ajax({
			url: 'rest/asignacion/listado',
			type: 'GET',
			dataType: 'json',
			success: function(result) {
				if(result.asignaciones) {
					jQuery.each(result.asignaciones, function(i, val) {
						var nombreAsignatura = val.asignatura ? val.asignatura.nombre : val.idAsignatura;
						var nombreProfesor = val.profesor ? (val.profesor.nombre + ' ' + val.profesor.apellidos) : val.idProfesor;
						var item = $('<li id="asignacion-' + val.idAsignatura + '"></li>');
						item.text(nombreAsignatura + ' -> ' + nombreProfesor + ' ');

						var borrar = $('<a href="#">[Desasignar]</a>');
						borrar.click(function(e) {
							e.preventDefault();
							var $enlace = $(this);
							bloquearEnlace($enlace, "[Desasignando...]");
							$.ajax({
								url: 'rest/asignacion/' + val.idAsignatura,
								type: 'DELETE',
								dataType: 'json',
								success: cargarAsignaciones,
								error: function(jqXhr) {
									mensajeError(jqXhr, "Error al desasignar");
								},
								complete: function() {
									desbloquearEnlace($enlace);
								}
							});
						});

						item.append(borrar);
						$('#asignaciones').append(item);
					});
				}
			},
			error: function(jqXhr) {
				mensajeError(jqXhr, "No se pudo cargar la lista de asignaciones");
			}
		});
	}

	$(document).ready(function() {
		cargarAsignaturas();
		cargarProfesores();
		cargarAsignaciones();

		$('#asignarProfesor').click(function() {
			var $btn = $(this);
			$btn.prop("disabled", true);
			var asignacion = {
				idAsignatura: parseInt($('#idAsignatura').val()),
				idProfesor: parseInt($('#idProfesor').val())
			};

			$.ajax({
				data: JSON.stringify(asignacion),
				url: 'rest/asignacion',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				type: 'POST',
				dataType: 'json',
				success: cargarAsignaciones,
				error: function(jqXhr) {
					mensajeError(jqXhr, "Error al asignar profesor");
				},
				complete: function() {
					$btn.prop("disabled", false);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>Asignar Profesores a Asignaturas</h1>
	<p><a href="index.jsp">Volver al Menú Principal</a></p>

	Asignatura:
	<select id="idAsignatura"></select><br>
	
	Profesor:
	<select id="idProfesor"></select><br>
	<button id="asignarProfesor">Asignar / Reasignar</button>

	<br><br>
	Asignaciones actuales:
	<ul id="asignaciones"></ul>
</body>
</html>
