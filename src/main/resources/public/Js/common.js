$(window).load(function () {
    $(".loader").fadeOut("slow");
})

$('#textarea1').val('New Text');
$('#textarea1').trigger('autoresize');
$(document).ready(function () {
    $('.parallax').parallax();

    // hide .navbar first
    $(".the-index-header").hide();

    // fade in .navbar
    $(function () {
        $(window).scroll(function () {
            // set distance user needs to scroll before we fadeIn navbar
            if ($(this).scrollTop() > 590) {
                $('.the-index-header').fadeIn();
            } else {
                $('.the-index-header').fadeOut();
            }
        });


    });
});

