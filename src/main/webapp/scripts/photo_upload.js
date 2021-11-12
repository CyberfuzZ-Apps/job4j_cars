function uploadPhoto() {
    let sellingId = (new URL(document.location)).searchParams.get('sellingId');
    let url = '/cars/upload.do?sellingId=' + sellingId;
    document.getElementById('photo_form').setAttribute('action', url);
}




