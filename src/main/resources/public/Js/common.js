$(window).load(function () {
    $(".loader").fadeOut("slow");
})

$('#textarea1').val('New Text');
$('#textarea1').trigger('autoresize');
$(document).ready(function () {
    $(".button-collapse").sideNav();

    $('.parallax').parallax();
    $('.modal-trigger').leanModal();
    // hide .navbar first
    $(".the-index-header").hide();

    $(window).scroll(function () {
        // set distance user needs to scroll before we fadeIn navbar
        if ($(this).scrollTop() > 590) {
            $('.the-index-header').fadeIn();
        } else {
            $('.the-index-header').fadeOut();
        }
    });

    // fade in .navbar

});

