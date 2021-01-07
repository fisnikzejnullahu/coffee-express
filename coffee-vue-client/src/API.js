const BASE_URL = "http://localhost:8080";
const API_VERSION = "v1"
const API_URL = `${BASE_URL}/api/${API_VERSION}`;

import router from './router';
import store from './store';

let refreshTokenCount = 0;
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

  console.log(refreshTokenCount);
  if (response.ok) {
    //maybe is ok when you refreshToken
    if (refreshTokenCount > 1) {
      refreshTokenCount = 0;
    }
  } else if (response.status === 404 || response.status === 403) {
    router.push('/error');
  } else if (response.status === 401) {
    if (store.getters.currentUser !== null) {
      if (refreshTokenCount < 1) {
        refreshTokenCount++;
        await refreshLogin();
        return call(url, object, httpMethod);
      } else {
        console.log(store);
        store.dispatch('logout');
        router.go();
      }
    }
  }

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