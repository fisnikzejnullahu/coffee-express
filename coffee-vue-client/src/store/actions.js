import Api from "@/API";

// export const getProfile = ({
//   commit
// }) => {
// api.getProfile(profile => {
//   commit('RECEIVE_PROFILE', profile)
// })
// }

export const getMenu = async ({commit}) => {
  console.log('ACTIONS: GETMENU');
  const menu = await Api.getMenu();
  commit('MENU_FETCHED', menu);
}

// export const getPromotions = ({ commit }) => {
//   api.getPromotions(promotions => {
//     commit('RECEIVE_PROMOTIONS', promotions)
//   })
// }

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

// export const toggleCoupon = ({ commit }, coupon) => {
//   commit('TOGGLE_COUPON', coupon)
// }