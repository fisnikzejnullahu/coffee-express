const BASE_URL = "http://localhost:8080";
const API_VERSION = "v1"
const API_URL = `${BASE_URL}/api/${API_VERSION}`;

let retryUnauthorized = 0;
async function call(url, object, httpMethod) {
  console.log("CALLED API from /" + url);

  let response = await fetch(url, {
    method: httpMethod,
    body: object ? JSON.stringify(object) : null,
    credentials: "include",
    mode: 'cors',
    headers: {
      "content-type": "application/json",
    }
  });

  if (response.status === 401) {
    console.log('401####################');
    // console.log('0');
    if (retryUnauthorized === 0) {
    //   console.log('1');
      retryUnauthorized++;
      await refreshLogin();
      response = call(url, object, httpMethod); 
    // }
    // else if (retryUnauthorized > 0) {
    //   console.log('hahahaha');
    }
  }
  // else{
  //   retryUnauthorized = 0;
  // }

  return response;
}

async function refreshLogin() {
  console.log("REFRESHING TOKEN");
  return await call(`${API_URL}/login/refresh`, null, "POST");
}

export default {
  async getMenu() {
    return await call(`${API_URL}/baristas/items`, null, "GET");
  },
  async getMenuItem(itemId) {
    return await call(`${API_URL}/baristas/items/${itemId}`, null, "GET");
  },
  async login(userInfo) {
    return await call(`${API_URL}/login`, userInfo, "POST");
  },
  async logout() {
    return await call(`${API_URL}/logout`, null, "POST");
  },
  async createCustomer(customerInfo) {
    return await call(`${API_URL}/customers/`, customerInfo, "POST");
  },
  async getMyAccountInfo(customerId) {
    return await call(`${API_URL}/customers/${customerId}`, null, "GET");
  },
  async placeOrder(placeOrderRequestData) {
    return await call(`${API_URL}/orders/`, placeOrderRequestData, "POST");
  },
  async getMyOrders(customerId, page) {
    return await call(`${API_URL}/orders?customerId=${customerId}&page=${page}`, null, "GET");
  },
  async trackOrder(orderId) {
    return await call(`${API_URL}/orders/track/${orderId}`, null, "GET");
  },
  async getOrder(orderId) {
    return await call(`${API_URL}/orders/${orderId}`, null, "GET");
  },
  async createBankAccount(body) {
    return await call(`${API_URL}/bank-accounts`, body, "POST");
  },
  async deleteBankAccount(id) {
    return await call(`${API_URL}/bank-accounts/${id}`, null, "DELETE");
  },
  async getMyBankAccounts(customerId) {
    return await call(`${API_URL}/bank-accounts?customerId=${customerId}`, null, "GET");
  },
  async getMyPopularBankAccount(customerId) {
    return await call(`${API_URL}/bank-accounts/popular?customerId=${customerId}`, null, "GET");
  },
  async getMyPayments(customerId) {
    return await call(`${API_URL}/payments?customerId=${customerId}`, null, "GET");
  },
  async updateProfile(customerId, body) {
    console.log(body);
    return await call(`${API_URL}/customers/${customerId}`, body, "PUT");
  }
};