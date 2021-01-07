import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Menu from "../views/Menu.vue";
import MenuItemDetails from "../components/MenuItemDetails.vue";
import Orders from "../views/Orders.vue";
import OrderTrack from "../views/OrderTrack.vue";
import OrderDetails from "../views/OrderDetails.vue";
import Cart from "../views/Cart.vue";
import Checkout from "../views/Checkout.vue";
import BankAccounts from "../views/BankAccounts.vue";
import ErrorPage from "../views/ErrorPage.vue";
import SigninPage from "../views/SigninPage.vue";
import SignupPage from "../views/SignupPage.vue";
import AddBankAccount from "../components/AddBankAccount.vue";
import Profile from "../views/Profile.vue";

import store from "../store";


Vue.use(VueRouter);

const routes = [{
    path: "*",
    component: ErrorPage
  },
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/menu",
    name: "Menu",
    component: Menu
  },
  {
    path: "/orders",
    name: "Orders",
    component: Orders
  },
  {
    path: "/orders/details/:id",
    name: "OrderDetails",
    component: OrderDetails
  },
  {
    path: "/orders/track/:id",
    name: "OrderTrack",
    component: OrderTrack
  },
  {
    path: "/cart",
    name: "Cart",
    component: Cart
  },
  {
    path: "/checkout",
    name: "Checkout",
    component: Checkout
  },
  {
    path: "/bankaccounts",
    name: "BankAccounts",
    component: BankAccounts
  },
  {
    path: "/menu/details/:id",
    name: "MenuItemDetails",
    component: MenuItemDetails
  },
  {
    path: "/signin",
    name: "SigninPage",
    component: SigninPage,
  },
  {
    path: "/signup",
    name: "SignupPage",
    component: SignupPage,
  },
  {
    path: "/add-bankacc",
    name: "AddBankAccount",
    component: AddBankAccount,
  },
  {
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  linkActiveClass: "active", // active class for non-exact links.
  linkExactActiveClass: "active" // active class for *exact* links.
});

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const authPages = ['/signin', '/signup'];
  const loggedIn = store.getters.currentUser !== null;

  console.log(loggedIn);
  if (loggedIn && authPages.includes(to.path)) {
    return next(from);
  }

  //everybody is allowed to see menu and home view 
  if (!loggedIn && (to.path !== '/' && to.path !== '/menu') && !authPages.includes(to.path)) {
    return next('/signin');
  }

  next();
})

export default router;