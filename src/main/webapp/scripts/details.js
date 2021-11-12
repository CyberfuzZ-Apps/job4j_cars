getDetails();

function getDetails() {
    let sellingId = (new URL(document.location)).searchParams.get('id');
    $.ajax({
        type: "POST",
        url: "/cars/details.do",
        dataType: "json",
        data: {
            sellingId: sellingId
        }
    }).done(function (data) {
        let sellingId = data['id'];
        let header = data['header'];
        let description = data['description'];
        let price = data['price'];
        let created = data['created'];
        let transmission = data['car']['transmission'];
        let body = data['car']['body'];
        let engine = data['car']['engine'];
        let mileage = data['car']['mileage'];
        let engineCapacity = data['car']['engineCapacity'];
        let wheel = data['car']['wheel'];
        let color = data['car']['color'];
        document.getElementById('car_header').innerHTML = header + '&nbsp;';
        document.getElementById('price').innerHTML = price + ' руб.';
        document.getElementById('photo').setAttribute(
            'src', '/cars/download.do?sellingId=' + sellingId);
        document.getElementById('description').innerHTML = description;
        document.getElementById('created').innerHTML = created;
        document.getElementById('resources').innerHTML =
            body + '<br>' +
            engine + '<br>' +
            engineCapacity + ' л.' + '<br>' +
            transmission + '<br>' +
            wheel + '<br>' +
            mileage + ' км.' + '<br>' +
            color + '<br>';
    }).fail(function () {
        console.log('ERROR LOAD DETAILS');
    });
}