async function getOrderInfo(orderId) {
    console.log(orderId)
    const response = await fetch('http://localhost:8088/orders/' + orderId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    });
    console.log(response)
    const body = await response.json();
    console.log(body);

    if (body['order-state'] === 'CANCELLED') {
        console.log("CANCELLED");
        console.log("Reason: " + body['cancelled-reason']);
    } else {
        updateOrderStatusSpan(body['order-state']);
        applyChanges(body['order-state']);
        if (body['order-state'] !== 'READY_FOR_PICKUP') {
            setTimeout(() => {
                getOrderInfo(orderId);
            }, 1000);
        }
    }
}

function applyChanges(state) {
    let stepsCompleted = 0;
    switch (state) {
        case 'PLACED':
            stepsCompleted = 1;
            break;
        case 'ACCEPTED':
            stepsCompleted = 2;
            break;
        case 'PREPARING':
            stepsCompleted = 3;
            break;
        case 'READY_FOR_PICKUP':
            stepsCompleted = 4;
            break;
        case 'PICKED_UP':
            stepsCompleted = 5;
            break;
    }
    updateHtmlCompleted(stepsCompleted);
}

function updateOrderStatusSpan(status) {
    document.querySelector('#order-status').innerHTML = 'Status: ' + status;
}

function updateHtmlCompleted(stepsCompleted) {
    console.log(stepsCompleted);
    let steps = document.querySelectorAll(".step");
    for (let i = 0; i < stepsCompleted; i++) {
        steps[i].classList.add('completed');
    }
}