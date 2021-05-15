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

const formatter = new Intl.NumberFormat('en-US', {
  minimumFractionDigits: 2,      
  maximumFractionDigits: 2,
});

export const total = state => {
  console.log('total getters');
  const total = cartItems(state).reduce((subtotal, item) => {
    return subtotal + item.price * item.quantity
  }, 0.0)
  return formatter.format(total);
}

export const popularBankAccount = state => {
  console.log('popularBankAccount getters');
  return state.auth.user.popularBankAccount;
}

export const currentUser = state => {
  console.log('GETTERS currentUser');
  // let signedInCookie = getCookie('signedIn');
  return (state.auth.user && state.auth.user.id) ? state.auth.user : null;
}