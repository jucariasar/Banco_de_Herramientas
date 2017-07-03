/*$(document).ready(function(){
	$("#boton").click(function(){
		var agregar = $("input[name=itemDeLista]").val();
		$(".lista").append("<div class='item'>" + agregar + "</div>");
	});
	$(document).on("click", ".item", function(){
		$(this).remove();
	});
});*/

$(document).ready(function(){
	$("select[name=color1]").change(function(){
            alert($('select[name=color1]').val());
            $('input[name=valor1]').val($(this).val());
        });
	$("#ejemplo2").change(function(){
            alert($('select[id=ejemplo2]').val());
            $('#valor2').val($(this).val());
	});
	$(".ejemplo3").change(function(){
            alert($('select[class=ejemplo3]').val());
            $('.valor3').val($(this).val());
	});
});