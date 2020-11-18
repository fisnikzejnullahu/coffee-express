<template>
  <nav
    class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
    id="ftco-navbar"
  >
    <div class="container">
      <a class="navbar-brand" href="index.html">Coffee<small>Blend</small></a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#ftco-nav"
        aria-controls="ftco-nav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="oi oi-menu"></span> Menu
      </button>
      <div class="collapse navbar-collapse" id="ftco-nav">
        <ul class="navbar-nav ml-auto">
          <router-link tag="li" to="/" class="nav-item" exact>
            <a class="nav-link">Home</a>
          </router-link>
          <router-link tag="li" to="/menu" class="nav-item" exact>
            <a class="nav-link">Menu</a>
          </router-link>
          <router-link tag="li" to="/orders" class="nav-item" exact>
            <a class="nav-link">My Orders</a>
          </router-link>
          <router-link tag="li" to="/bankaccounts" class="nav-item" exact>
            <a class="nav-link">My Bank Accounts</a>
          </router-link>
          <li class="nav-item cart">
            <router-link to="/cart" class="nav-link">
              <span class="icon icon-shopping_cart"></span
              ><span
                class="bag d-flex justify-content-center align-items-center"
                ><small>{{ itemsQuantity }}</small></span
              ></router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['itemsQuantity'])
  }
};
</script>

<style>
</style>