<template>
  <section class="ftco-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 mb-5 ftco-animate fadeInUp ftco-animated">
          <img
            :src="menuItem.picture"
            class="img-fluid"
            alt="Caffè Misto picture"
          />
        </div>
        <div
          class="col-lg-6 product-details pl-md-5 ftco-animate fadeInUp ftco-animated"
        >
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
                    onclick="document.querySelector('#quantity').value = parseInt(document.querySelector('#quantity').value) - 1"
                  >
                    <i class="icon-minus"></i>
                  </button>
                </span>
                <input
                  v-model="quantity"
                  type="text"
                  id="quantity"
                  name="quantity"
                  class="form-control input-number"
                  value="1"
                  min="1"
                  max="100"
                />
                <span class="input-group-btn ml-2">
                  <button
                    type="button"
                    class="quantity-right-plus btn"
                    data-type="plus"
                    data-field=""
                    onclick="document.querySelector('#quantity').value = parseInt(document.querySelector('#quantity').value) + 1"
                  >
                    <i class="icon-plus"></i>
                  </button>
                </span>
              </div>
            </div>
            <p>
              <a
                type="button"
                class="btn btn-primary py-3 px-5"
                @click="addToCart(menuItem, quantity)"
              >
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
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      quantity: 1,
      menuItem: {},
    };
  },
  methods: mapActions(["addToCart"]),
  async created() {
    console.log("MenuItemDetails.vue created()");
    let response = await Api.getMenuItem(this.$route.params.id);
    let menuItem = response.json();
    this.menuItem = menuItem;
    console.log(menuItem);
  },
};
</script>

<style>
</style>