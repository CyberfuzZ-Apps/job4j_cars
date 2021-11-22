$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/job4j_cars/index.do",
        dataType: "json"
    }).done(function (user) {
        document.getElementById('username').innerHTML = user.name;
    }).fail(function () {
        console.log('ERROR LOAD USERNAME')
    });
});