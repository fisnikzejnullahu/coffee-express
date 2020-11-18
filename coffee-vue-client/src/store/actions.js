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

export const addToCart = ({ commit }, menuItem) => {
  console.log('ACTIONS: ADDTOCART: ' + menuItem.id);
  commit('ADD_TO_CART', menuItem.id)
}

// export const removeFromCart = ({ commit }, product) => {
//   commit('REMOVE_FROM_CART', product)
// }

// export const toggleCoupon = ({ commit }, coupon) => {
//   commit('TOGGLE_COUPON', coupon)
// }