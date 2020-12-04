import Vue from "vue";
import Vuex from "vuex";
import * as actions from './actions'
import * as getters from './getters'
import menuItems from './modules/menuitems'
import auth from './modules/auth'
import orders from './modules/orders'
import shoppingCart from './modules/shopping-cart'
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  actions,
  getters,
  modules: {
    menuItems,
    auth,
    orders,
    shoppingCart
  },
  plugins: [createPersistedState()],
});