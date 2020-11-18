const state = {
  all: []
}

const mutations = {
  MENU_FETCHED (state, menu) {
    console.log('MENU_FETCHED');
    state.all = menu
  },
}

export default {
  state,
  mutations
}
