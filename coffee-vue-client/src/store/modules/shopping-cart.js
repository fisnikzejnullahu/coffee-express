const state = {
  items: [],
}

const mutations = {
  ADD_TO_CART (state, menuItemId, quantity) {
    const item = state.items.find(item => item.id === menuItemId)

    if (!item) {
      state.items.push({
        id: menuItemId,
        quantity: quantity ? quantity : 1
      })
    } else {
      if (quantity) {
        item.quantity = quantity;
      }
      else {
        item.quantity++;
      }
    }
  },

  REMOVE_FROM_CART (state, item) {
    console.log('REMOVE_FROM_CART mutation: ');
    console.log(item);
    console.log('REMOVE_FROM_CART mutation: ');
    const index = state.items.findIndex(items => items.id === item.id)
    state.items.splice(index, 1)
  }
}

export default {
  state,
  mutations
}
