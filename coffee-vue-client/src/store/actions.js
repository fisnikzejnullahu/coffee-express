import Api from "@/API";

export const getMenu = async ({commit}) => {
  console.log('ACTIONS: GETMENU');
  const response = await Api.getMenu();
  const menu = await response.json();
  commit('MENU_FETCHED', menu);
}

export const addToCart = ({ commit }, menuItem) => {
  let quantity = menuItem.quantity;
  console.log('ACTIONS: ADDTOCART: ' + menuItem.id + ", qnt: " + quantity);
  if (!quantity || quantity < 1) {
    quantity = 1;
  }
  menuItem.quantity = quantity;
  commit('ADD_TO_CART', menuItem)
}

export const removeFromCart = ({ commit }, menuItem) => {
  console.log('ACTIONS: REMOVEFROMCART: ' + menuItem.id);
  commit('REMOVE_FROM_CART', menuItem)
}

export const resetCart = ({ commit }) => {
  console.log('ACTIONS: RESET CART: ');
  commit('RESET_CART');
}

export const login = async ({commit}, userInfo) => {
  console.log('ACTIONS: LOGIN');
  let response = await Api.login(userInfo);
  console.log(response);
  if (response.status === 200) {
    let customerInfo = await response.json();
    commit('LOGGED_IN', customerInfo);
  }
  return response;
}

export const logout = async ({commit}) => {
  console.log('LOGOUT ACTION');
  await Api.logout();
  commit('LOGGED_OUT');
}

export const signup = async ({commit}, customerInfo) => {
  console.log('SIGNUP ACTION');
  const response = await Api.createCustomer(customerInfo);
  return response;
}

export const placeOrder = async ({commit}, placeOrderRequestData) => {
  console.log('placeOrder ACTION');
  let items = [];
  placeOrderRequestData.items.forEach(item => {
    items.push({
      "menu_item_id": item.id,
      "quantity": item.quantity
    });
  });
  placeOrderRequestData.items = items;
  const response = await Api.placeOrder(placeOrderRequestData);
  return response;
}