
$(document).ready(function () {
    $(document).on("click", "input[type=checkbox]", function () {
        var parent = $(this).parent().attr('id');
        $('#' + parent + ' input[type=checkbox]').prop("checked", false);
        $(this).prop("checked", true); 
    });
});

/*
 $(document).ready(function () {
 $(document).on("click", "input[type=checkbox]", function () {
 var parent = $(this).parent().attr('id');
 alert("Se hizo clic en un checkbox con el id del padre " + parent);
 $("#" + parent + " input[type=checkbox]:checked").prop("checked", false);
 $(this).attr('checked', 'checked');
 
 //$("#"+parent+"").children("input[type=checkbox]").removeAttr("checked");
 //$(this).attr("checked", "true");
 //$("#product1 input[type=checkbox]").removeAttr("checked");
 //$(this).attr("checked", true);
 //$('#'+parent+' input[type=checkbox]').next('checked').attr(('checked', 'true'));
 //var parent = $(this).parent().attr('id');
 //$('#'+parent+' input[type=checkbox]').removeAttr('checked');
 //$(this).attr('checked', 'checked');
 });
 });*/




/*
 $(document).ready(function() {
 $('input[type=checkbox]').live('click', function(){
 var parent = $(this).parent().attr('id');
 $('#'+parent+' input[type=checkbox]').removeAttr('checked');
 $(this).attr('checked', 'checked');
 });
 });*/

/*
 $(document).ready(function () {
 $(".micheckbox").click(function () {
 var id = $(this).attr("id");
 
 $('.micheckbox:checked').each(function () {
 if ($(".micheckbox").attr("id") !== id) {
 $(".micheckbox").attr("checked", "false");
 }            
 });
 
 });
 });*/
/*
 var _tip_check = '';
 $(document).ready(function () {
 $('input[type=checkbox]').live('click', function () {
 
 if ($(this).is(':checked' || _tip_check != $(this).attr('id'))) {
 var parent = $(this).parent().attr('id');
 $('#' + parent + ' input[type=checkbox]').removeAttr('checked');
 $(this).attr('checked', 'checked');
 } else
 $(this).removeAttr('checked');
 
 if (_tip_check != $(this).attr('id'))
 _tip_check = $(this).attr('id');
 });
 });*/

