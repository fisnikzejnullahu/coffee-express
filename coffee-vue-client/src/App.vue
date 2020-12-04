<template>
  <div id="app">
    <Navbar v-if="!currentPageIsLogin" />
    <router-view />
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";

export default {
  name: "Home",
  components: {
    Navbar,
  },
  data() {
    return {
      currentPageIsLogin:
        this.$router.currentRoute.path === "/signin" ||
        this.$router.currentRoute.path === "/signup",
    };
  },
  watch: {
    $route(to, from) {
      console.log(to);
      if (to.path === "/signin" || to.path === "/signup") {
        this.currentPageIsLogin = true;
      } else {
        this.currentPageIsLogin = false;
      }
    },
  },
};
</script>

<style lang="scss">
</style>
