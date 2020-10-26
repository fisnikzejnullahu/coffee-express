async function deleteAccount(accountId) {
    console.log(typeof accountId)
    const response = await fetch('http://localhost:8082/accounts/' + accountId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });

    if (response.status === 200){
        const body = await response.json();

        console.log(body)
        if (body.success) {
            document.querySelector('div[div-acc-id="' + accountId + '"]').remove();
        }
    }
}

$('#deleteConfirmBtn').on('click', function (e){
    // console.log($('#deleteConfirmBtn').attr('data-cid'))
    deleteAccount($('#deleteConfirmBtn').attr('data-cid'));
    $('#closeModalBtn').click();
});

$('#exampleModal').on('show.bs.modal', function (e) {
    $('#deleteConfirmBtn').attr('data-cid', $(e.relatedTarget).attr('data-cid'));
})