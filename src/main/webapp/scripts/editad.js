let sellingId = (new URL(document.location)).searchParams.get('id');

$(document).ready(function () {
    $.ajax({
        type: "PUT",
        url: "/job4j_cars/ad.do?sellingId=" + sellingId,
        dataType: "json"
        /*

         */
    }).done(function (data) {
        document.getElementById('brand').setAttribute('value', data['car']['brand']);
        document.getElementById('model').setAttribute('value', data['car']['model']);
        document.getElementById('year').setAttribute('value', data['car']['year']);
        document.querySelector('#body').value = data['car']['body'];
        document.querySelector('#engine').value = data['car']['engine'];
        document.getElementById('enginecapacity').setAttribute('value', data['car']['engineCapacity']);
        document.querySelector('#transmission').value = data['car']['transmission'];
        document.querySelector('#wheel').value = data['car']['wheel'];
        document.getElementById('mileage').setAttribute('value', data['car']['mileage']);
        document.getElementById('color').setAttribute('value', data['car']['color']);
        document.getElementById('price').setAttribute('value', data['price']);
        document.getElementById('description').innerText = data['description'];
        document.getElementById('photo').setAttribute('src', '/job4j_cars/download.do?sellingId=' + sellingId);
    }).fail(function () {
        console.log('ERROR LOAD PARAMETERS');
    })
});

function setUrl() {
    let url = '/job4j_cars/ad.do?sellingId=' + sellingId
        + '&brand=' + document.getElementById('brand').value
        + '&model=' + document.getElementById('model').value
        + '&year=' + document.getElementById('year').value
        + '&body=' + document.querySelector('#body').value
        + '&engine=' + document.querySelector('#engine').value
        + '&enginecapacity=' + document.getElementById('enginecapacity').value
        + '&transmission=' + document.querySelector('#transmission').value
        + '&wheel=' + document.querySelector('#wheel').value
        + '&mileage=' + document.getElementById('mileage').value
        + '&color=' + document.getElementById('color').value
        + '&price=' + document.getElementById('price').value
        + '&description=' + document.getElementById('description').value;
    document.getElementById('form').setAttribute('action', url);
}

function deletePhoto() {
    window.location.href = '/job4j_cars/photo_delete.do?sellingId=' + sellingId;
}

function updatePhoto() {
    window.location.href = '/job4j_cars/photo_upload.html?sellingId=' + sellingId;
}