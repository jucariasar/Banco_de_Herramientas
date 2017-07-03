// Ejemplo de prueba

$(document).ready(function(){
	$("#regionales").change(function(){
		// Una forma
		alert($('select[id=regionales]').val());
		$('#valorselect').val($(this).val());   


		// Otra forma
		//var valor = $("#regionales").val(); Otra Forma
		//alert(valor);
		//$('#valorselect').val(valor);	
	});
});

/*
$(document).ready(function() {
	$('#regionales').change(function(event) {
		var codigoRegional = $('#regionales').val();
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('Prueba_Ajax', {
				regionales : nombreVar,
			}, function(responseText) {
				$alert(responseText);
			});
		});
});*/