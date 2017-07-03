$(document).ready(function() {
	$('#submit').click(function(event) {
		var nombreVar = $('#nombre').val();
		var apellidoVar = $('#apellido').val();
		var edadVar = $('#edad').val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('Prueba_Ajax_2', {
				nombre : nombreVar,
				apellido: apellidoVar,
				edad: edadVar
			}, function(responseText) {
				$('#tabla').html(responseText);
			});
		});
});