/*
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
 $("#areas").change(function(event){
 
 });
 });*/



// Prueba de todo lo anterior pero con methodo ajax de jQuery

$(document).ready(function () {
    $('#regionales').change(function (event) {
        limpiarCentros();
        limpiarAreas();
        var codigoR = {
            codR: $('#regionales').val(),
            opcion: "1"
        };

        $.ajax({
            type: "POST",
            url: "Prueba_Ajax",
            //dataType: "json", No me sirvio esta instrucción
            data: codigoR,
            success: function (str) {
                var obj = JSON.parse(str);
                var codigoC, nombreC;
                $.each(obj, function (i, e) {
                    codigoC = e.codigo;
                    nombreC = e.nombre;
                    $("#centros").append("<option type='number' value=" + codigoC + ">" + nombreC + "</option>");
                });
            }

        });
    });

    //(document).on("change", "#centros", function() {
        $("#centros").change(function(){
        limpiarAreas();
        var codigoC = {
            codC: $("#centros").val(),
            opcion: "2"
        };
        $.ajax({
            type: "POST",
            url: "Prueba_Ajax",
            data: codigoC,
            success: function (str) {
                var obj = JSON.parse(str);
                var nombreA;
                $.each(obj, function (i, e) {
                    nombreA = e.nombre;
                    $("#areas").append("<option type='number' value='"+ nombreA + "'>" + nombreA + "</option>");
                })

            }
        });
    });
});

function limpiarCentros() {
    $("#centros option").each(function () {
        if ($(this).val() != "") {
            $(this).remove();
        }
    });
}

function limpiarAreas() {
    $("#areas option").each(function () {
        if ($(this).val() != "")
            $(this).remove();
    });

}

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