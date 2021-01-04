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
  console.log(getCookie('signedIn'));
  console.log('currentUser getters');
  return state.auth.user;
}

function getCookie(name) {
  var nameEQ = name + "=";
  var ca = document.cookie.split(';');
  for(var i=0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') c = c.substring(1,c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
  }
  return null;
}