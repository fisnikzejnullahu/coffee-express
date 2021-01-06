const state = {
  items: [],
}

const mutations = {
  ADD_TO_CART (state, menuItem) {
    let quantity = menuItem.quantity;
    console.log('MUTATION ADD TO CART');
    console.log(menuItem.id);
    console.log(quantity);
    const item = state.items.find(item => item.id === menuItem.id)

    if (!item) {
      state.items.push({
        id: menuItem.id,
        quantity: quantity ? quantity : 1
      })
    } else {
        item.quantity += quantity;
    }
  },

  REMOVE_FROM_CART (state, item) {
    console.log('REMOVE_FROM_CART mutation: ');
    console.log(item);
    console.log('REMOVE_FROM_CART mutation: ');
    const index = state.items.findIndex(items => items.id === item.id)
    state.items.splice(index, 1)
  },

  RESET_CART (state) {
    state.items = [];
  }
}

export default {
  state,
  mutations
}
