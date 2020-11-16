<template>
  <div v-if="!loaded">
    <loading-screen />
  </div>

  <div v-else class="row">
    <div class="col-md-3" v-for="item in menuItems" v-bind:key="item.id">
      <div class="menu-entry">
        <a
          :href="'/menu/details/' + item.id"
          class="img"
          :style="{ 'background-image': 'url(' + item.thumbnail + ')' }"
        ></a>
        <div class="text text-center pt-4">
          <h3>
            <a href="product-single.html">{{ item.name }}</a>
          </h3>

          <p class="price">
            <span>$ {{ item.price }}</span>
          </p>
          <form action="/web-app/mvc/cart/addInCart" method="post">
            <input name="menuItemId" value="2" type="hidden" />
            <input name="quantity" value="1" type="hidden" />
            <p>
              <button type="submit" class="btn btn-primary btn-outline-primary">
                Add to Cart
              </button>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Api from "@/API";
import LoadingScreen from "@/components/LoadingScreen";

export default {
  data() {
    return {
      menuItems: [],
      loaded: false,
    };
  },
  components: {
    "loading-screen": LoadingScreen,
  },
  async created() {
    console.log("MenuItems.vue created()");
    var result = await Api.getMenu();
    this.menuItems = result;
    this.loaded = true;
    console.log(result);
  },
};
</script>

<style>
</style>