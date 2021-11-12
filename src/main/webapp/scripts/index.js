let userEmail;

getUserName();

$(document).ready(getSellingList);

function soldUpdate(sellingId) {
    let sold = document.getElementById('sold' + sellingId).value;
    if (sold) {
        window.location.href='/cars/update.do?sellingId=' + sellingId;
    }
}

function getUserName() {
    $.ajax({
        type: "POST",
        url: "/cars/index.do",
        dataType: "json"
    }).done(function (user) {
        userEmail = user['email'];
        document.getElementById('username').innerHTML = user.name;
    }).fail(function () {
        console.log('ERROR LOAD USERNAME');
    });
}

function getSellingList() {
    let only_my_ads = $('#only_my_ads').is(':checked');
    $.ajax({
        type: "PUT",
        url: "/cars/index.do?only_my_ads=" + only_my_ads,
        dataType: "json",
    }).done(function (data) {
        $('#table tr').empty();
        $('#table thead').html(
            '<tr>' +
            '<th scope="col"></th>' +
            '<th scope="col">Фото</th>' +
            '<th scope="col">Авто</th>' +
            '<th scope="col">Характеристики</th>' +
            '<th scope="col">Описание</th>' +
            '<th scope="col">Цена</th>' +
            '<th scope="col">Автор</th>' +
            '<th scope="col">Статус</th>' +
            '<th scope="col">Дата</th>' +
            '</tr>');
        for (let i = 0; i < data.length; i++) {
            let sellingId = data[i]['id'];
            let header = data[i]['header'];
            let description = data[i]['description'];
            let price = data[i]['price'];
            let created = data[i]['created'];
            let sold = '';
            if (!data[i]['sold']) {
                sold = 'Продается';
                header = '<a href="/cars/details.html?id=' + sellingId + '">' + header + '</a>';
            } else {
                sold = 'Продано';
            }
            let userName = data[i]['user']['name'];
            let transmission = data[i]['car']['transmission'];
            let body = data[i]['car']['body'];
            let engine = data[i]['car']['engine'];
            let mileage = data[i]['car']['mileage'];
            let engineCapacity = data[i]['car']['engineCapacity'];
            let wheel = data[i]['car']['wheel'];
            let color = data[i]['car']['color'];
            let edit_delete = '';
            if (userEmail === data[i]['user']['email']) {
                edit_delete = '<a href="/cars/ad/editad.html?id=' + sellingId + '">'
                    + '<i class="fa fa-edit mr-3"></i>' + '</a>'
                    + '<a href="/cars/delete.do?id=' + sellingId + '">'
                    + '<i class="fa fa-trash mr-3"></i>' + '</a>';
            }
            if (userEmail === data[i]['user']['email'] && !data[i]['sold']) {
                sold = '<select id="sold' + sellingId + '" name="sold' + sellingId + '" '
                    + 'onchange="soldUpdate(' + sellingId + ')">' +
                    '<option value="false">Продается</option>' +
                    '<option value="true">Продано</option></select>'
            }
            $('#table tr:last').after(
                '<tr>' +
                '<td>' + edit_delete + '</td>' +
                '<td>' + '<img src="/cars/download.do?sellingId=' + sellingId + '" ' +
                'width="100px" height="100px"/>' +
                '</td>' +
                '<td>' + header + '</td>' +
                '<td>' + body + ', '
                + engine + ', '
                + transmission + ', '
                + engineCapacity + ' л., '
                + wheel + ', '
                + color + ', '
                + mileage + ' км</td>' +
                '<td>' + description + '</td>' +
                '<td>' + price + ' руб.</td>' +
                '<td>' + userName + '</td>' +
                '<td>' + sold + '</td>' +
                '<td>' + created + '</td>' +
                '</tr>');
        }
    }).fail(function () {
        console.log('ERROR LOAD SELLING LIST');
    });
}