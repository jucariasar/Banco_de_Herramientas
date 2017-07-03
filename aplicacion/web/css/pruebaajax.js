
$(document).ready(function () {
    $('#regionales').change(function (event) {
        limpiarCentros();
        
        var codigoR = $('#regionales').val();
        // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
        $.post('Prueba_Ajax', {
            codR: codigoR
        }, function (str) {
            var obj = JSON.parse(str);
            var codigoC, nombreC;
            //alert(obj);
            $.each(obj, function (i, e) {
                codigoC = e.codigo;
                nombreC = e.nombre;
                $("#centros").append("<option type='number' value=" + codigoC + ">" + nombreC + "</option>");
            });
        });
    });
});


function limpiarCentros()
{
    $("#centros option").each(function(){
        if ($(this).val() != "" ){        
            $(this).remove();
        }
     });
}


// Prueba de todo lo anterior pero con methodo ajax de jQuery
// Para arreglar porque no funciona con la funcion ajax
/*
 $(document).ready(function() {
 $('#regionales').change(function(event) {
 var codigoR = {
 codR: $('#regionales').val()
 };
 // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
 $.ajax({
 type        : "POST",
 url         : "./Prueba_Ajax",
 data        : codigoR,
 dataType    : "json",
 encode      : true
 }).done(function(str) {
 var obj = JSON.parse(str);
 var codigoC, nombreC;
 alert(obj);
 $.each(obj, function (i, e) {
 codigoC = e.codigo;
 nombreC = e.nombre;
 $("#centros").append("<option type='number' value=" + codigoC + ">" + nombreC + "</option>");
 });
 });
 });
 });*/


// Arreglo de objetos JSON
//var temp = [{"codigo":"9203","nombre":"Centro para el Desarrollo del Hábitad y la Construcción","codigo_regional":"5"},{"codigo":"9204","nombre":"Centro de Tecnología de la Manufactura Avanzada","codigo_regional":"5"},{"codigo":"9206","nombre":"Centro Textil de Gestión Industrial","codigo_regional":"5"}];

// Ejemplos de pruebas de evento change de jQuery
/*
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
 });*/