// const BASE_URL = "http://localhost:3001";
// const API_URL = `${BASE_URL}/api`;
const BARISTAS_SERVICE_URL = "http://localhost:8083"

// export default API_URL;

// GET = async resource => {
//   const response = await fetch(`${API_URL}/${resource}`);
//   return response.json();
// };
async function call(url, object, httpMethod) {
  console.log("CALLED API from /" + url);

  return await fetch(url, {
    method: httpMethod,
    body: object ? JSON.stringify(object) : null,
    credentials: "include",
    mode: 'cors',
    headers: {
      "content-type": "application/json",
    }
  });
}

export default {
  async getMenu() {
    return await call(`${BARISTAS_SERVICE_URL}/baristas/items`, null, "GET");
  },
  async getMenuItem(itemId) {
    return await call(`${BARISTAS_SERVICE_URL}/baristas/items/${itemId}`, null, "GET");
  },
  async login(userInfo) {
    return await call(`http://localhost:8080/login`, userInfo, "POST");
  },
  async logout() {
    return await call(`http://localhost:8080/logout`, null, "POST");
  },
  async register(userInfo) {
    return await call(`http://localhost:8080/register`, null, "POST");
  },
  async placeOrder(placeOrderRequestData) {
    return await call(`http://localhost:8080/logout`, null, "POST");
  },
  async getMyOrders(customerId, page) {
    return await call(`http://localhost:8084/history?customerId=${customerId}&page=${page}`, null, "GET");
  },
  async getOrder(orderId) {
    return await call(`http://localhost:8080/logout`, null, "POST");
  },
  async trackOrder(orderId) {
    return await call(`http://localhost:8088/orders/${orderId}`, null, "GET");
  },
  async getMyBankAccounts(customerId) {
    return await call(`http://localhost:8082/accounts?customerId=${customerId}`, null, "GET");
  },
  async getMyPayments(customerId) {
    return await call(`http://localhost:8080/logout`, null, "POST");
  }
};