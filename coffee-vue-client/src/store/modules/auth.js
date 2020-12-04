const state = {
  user: {}
}

const mutations = {
  LOGGED_IN (state, user) {
    console.log('LOGGED_IN');
    console.log(user);
    state.user = user
  },
  
  LOGGED_OUT (state) {
    console.log('LOGGED_OUT');
    state.user = null;
  }
}

export default {
  state,
  mutations
}
