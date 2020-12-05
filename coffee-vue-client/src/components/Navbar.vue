<template>
  <nav
    class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
    id="ftco-navbar"
  >
    <div class="container">
      <router-link to="/" class="navbar-brand" exact>
        Coffee<small>Express</small>
      </router-link>

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
          <router-link tag="li" to="/menu" class="nav-item" exact>
            <a class="nav-link">Menu</a>
          </router-link>

          <router-link
            tag="li"
            v-if="currentUser === null"
            to="/signin"
            class="nav-item"
            exact
          >
            <a class="nav-link">Sign in</a>
          </router-link>

          <router-link
            tag="li"
            v-if="currentUser === null"
            to="/signup"
            class="nav-item"
            exact
          >
            <a class="nav-link">Join Now</a>
          </router-link>

          <router-link
            v-if="currentUser !== null"
            tag="li"
            to="/orders"
            class="nav-item"
            exact
          >
            <a class="nav-link">My Orders</a>
          </router-link>
          <router-link
            v-if="currentUser !== null"
            tag="li"
            to="/bankaccounts"
            class="nav-item"
            exact
          >
            <a class="nav-link">My Bank Accounts</a>
          </router-link>
          <router-link
            v-if="currentUser !== null"
            tag="li"
            to="/profile"
            class="nav-item"
            exact
          >
            <a class="nav-link">My Profile</a>
          </router-link>
          <router-link
            v-if="currentUser !== null"
            tag="li"
            to="/logout"
            class="nav-item"
          >
            <a class="nav-link">Logout</a>
          </router-link>
          <li v-if="currentUser !== null" class="nav-item cart">
            <router-link to="/cart" class="nav-link">
              <span class="icon icon-shopping_cart"></span
              ><span
                class="bag d-flex justify-content-center align-items-center"
                ><small>{{ itemsQuantity }}</small></span
              ></router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["itemsQuantity", "currentUser"]),
  },
  mounted() {
    console.log(this.currentUser === null);
  },
};
</script>

<style>
</style>