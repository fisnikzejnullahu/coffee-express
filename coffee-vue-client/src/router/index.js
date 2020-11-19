import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Menu from "../views/Menu.vue";
import MenuItemDetails from "../views/MenuItemDetails.vue";
import Orders from "../views/Orders.vue";
import OrderTrack from "../views/OrderTrack.vue";
import Cart from "../views/Cart.vue";
import Checkout from "../views/Checkout.vue";
import BankAccounts from "../views/BankAccounts.vue";
import PageNotFound from "../views/PageNotFound.vue";


Vue.use(VueRouter);

const routes = [{
    path: "*",
    component: PageNotFound
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
    path: "/track",
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
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  linkActiveClass: "active", // active class for non-exact links.
  linkExactActiveClass: "active" // active class for *exact* links.
});

export default router;