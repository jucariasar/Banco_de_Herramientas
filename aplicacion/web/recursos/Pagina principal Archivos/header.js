$(document).ready(function () {

    $(window).scroll(function () {
        if ($(this).scrollTop() > 0) {
            $('header').addClass('header2');
        } else {
            $('header').removeClass('header2');
        }
    });
});


/*
function consultaCentros() {
    ctexto = document.getElementById("formR").codigo_regional;
    top.abajo.location = "consul.php?id=" + ctexto.value;
}*/