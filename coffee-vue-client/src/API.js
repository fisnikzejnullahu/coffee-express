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

  const response = await fetch(url, {
    method: httpMethod,
    body: object ? JSON.stringify(object) : null,
    headers: {
      "content-type": "application/json",
      // authorization: `${sessionStorage.token}`
    }
  });

  return response.json();
}

export default {
  async getMenu() {
    return await call(`${BARISTAS_SERVICE_URL}/baristas/items`, null, "GET");
  },
  async getMenuItem(itemId) {
    return await call(`${BARISTAS_SERVICE_URL}/baristas/items/${itemId}`, null, "GET");
  },
};