import Vue from "vue";
import Vuex from "vuex";
import * as actions from './actions'
import * as getters from './getters'
import menuItems from './modules/menuitems'
import profile from './modules/profile'
import orders from './modules/orders'
import shoppingCart from './modules/shopping-cart'

Vue.use(Vuex);

export default new Vuex.Store({
  actions,
  getters,
  modules: {
    menuItems,
    profile,
    orders,
    shoppingCart
  },
});