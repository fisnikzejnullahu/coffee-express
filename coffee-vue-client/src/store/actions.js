import Api from "@/API";

export const getMenu = async ({commit}) => {
  console.log('ACTIONS: GETMENU');
  const response = await Api.getMenu();
  const menu = await response.json();
  commit('MENU_FETCHED', menu);
}

export const addToCart = ({ commit }, menuItem, quantity) => {
  console.log('ACTIONS: ADDTOCART: ' + menuItem.id + ", qnt: " + quantity);
  if (!quantity || quantity < 1) {
    quantity = 1;
  }
  commit('ADD_TO_CART', menuItem.id, quantity)
}

export const removeFromCart = ({ commit }, menuItem) => {
  console.log('ACTIONS: REMOVEFROMCART: ' + menuItem.id);
  commit('REMOVE_FROM_CART', menuItem)
}

export const login = async ({commit}, userInfo) => {
  console.log('ACTIONS: LOGIN');
  const response = await Api.login(userInfo);
  console.log(response);
  commit('LOGGED_IN', {
    username: userInfo.username
  });
  return response;
}

export const logout = async ({commit}) => {
  console.log('LOGOUT ACTION');
  await Api.logout();
  commit('LOGGED_OUT');
  window.location.href = '/';
}