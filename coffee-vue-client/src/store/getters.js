export const cartItems = state => {
  console.log('GETTERS: cartItems');
  return state.shoppingCart.items.map(({ id, quantity }) => {
    const menuItem =
      state
        .menuItems.all
        .find(menuItem => menuItem.id === id)

    return {
      ...menuItem,
      quantity
    }
  })
}

export const itemsQuantity = state => {
  return cartItems(state).reduce((quantity, item) => {
    return quantity + item.quantity
  }, 0)
}

export const total = state => {
  console.log('total getters');
  const total = cartItems(state).reduce((subtotal, item) => {
    return subtotal + item.price * item.quantity
  }, 0.0)
  return total;
}

export const popularBankAccount = state => {
  console.log('popularBankAccount getters');
  return state.auth.user.popularBankAccount;
}

export const currentUser = state => {
  console.log('currentUser getters');
  return state.auth.user;
}