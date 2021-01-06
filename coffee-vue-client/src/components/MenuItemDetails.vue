<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>
  <section v-else class="ftco-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 mb-5 ftco-animate fadeInUp ftco-animated">
          <img :src="menuItem.picture" class="img-fluid" alt="Caffè Misto picture" />
        </div>
        <div class="col-lg-6 product-details pl-md-5 ftco-animate fadeInUp ftco-animated">
          <h3>{{ menuItem.name }}</h3>
          <p class="price">
            <span>${{ menuItem.price }}</span>
          </p>
          <div class="row mt-4">
            <div class="input-group col-md-6 d-flex mb-3">
              <span class="input-group-btn mr-2">
                <button
                  type="button"
                  class="quantity-left-minus btn"
                  data-type="minus"
                  data-field=""
                  @click="decQuantity"
                >
                  <i class="icon-minus"></i>
                </button>
              </span>
              <input
                v-model="quantity"
                type="text"
                id="quantity"
                class="form-control input-number"
                min="1"
                max="100"
              />
              <span class="input-group-btn ml-2">
                <button
                  type="button"
                  class="quantity-right-plus btn"
                  data-type="plus"
                  data-field=""
                  @click="incQuantity"
                >
                  <i class="icon-plus"></i>
                </button>
              </span>
            </div>
          </div>
          <p>
            <a type="button" class="btn btn-primary py-3 px-5" @click="addToCart({'id': menuItem.id, quantity})">
              Add to Cart
            </a>
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Api from "@/API";
import LoadingScreen from "@/components/LoadingScreen";
import { mapActions } from "vuex";

export default {
  data() {
    return {
      quantity: 1,
      menuItem: {},
      loaded: false,
    };
  },
  components: {
    LoadingScreen,
  },
  async mounted() {
    console.log("MenuItemDetails.vue mounted()");
    let response = await Api.getMenuItem(this.$route.params.id);
    let menuItem = await response.json();
    console.log(menuItem);
    this.menuItem = menuItem;
    this.loaded = true;
  },
  methods: {
    ...mapActions(["addToCart"]),
    incQuantity() {
      this.quantity++;
    },
    decQuantity() {
      if (this.quantity > 1) {
        this.quantity--;
      }
    },
  },
};
</script>

<style></style>
