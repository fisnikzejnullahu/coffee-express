<template>
  <div class="col-md-3">
    <div class="menu-entry">
      <a
        :href="'/menu/details/' + item.id"
        class="img"
        :style="{ 'background-image': 'url(' + item.thumbnail + ')' }"
      ></a>
      <div class="text text-center pt-4">
        <h3>
          <a :href="'/menu/details/' + item.id">{{ item.name }}</a>
        </h3>

        <p class="price">
          <span>$ {{ item.price }}</span>
        </p>
        <p v-if="currentUser !== null">
          <button
            class="btn btn-primary btn-outline-primary"
            @click="addToCart(item)"
          >
            Add to Cart
          </button>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "MenuItem",
  props: {
    item: {},
  },
  computed: {
    ...mapGetters(["currentUser"]),
  },
  methods: mapActions(["addToCart"]),
};
</script>

<style>
</style>