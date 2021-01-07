<template>
  <section class="ftco-section ftco-cart">
    <div class="container">
      <div class="row">
        <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
          <div class="cart-list" style="overflow: hidden">
            <table class="table">
              <thead class="thead-primary">
                <tr class="text-center">
                  <th>&nbsp;</th>
                  <th>&nbsp;</th>
                  <th>Product</th>
                  <th>Price</th>
                  <th>Quantity</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  class="text-center"
                  v-for="item in cartItems"
                  v-bind:key="item.id"
                >
                  <td class="product-remove">
                    <a type="button" @click="removeFromCart(item)">
                      <span class="icon-close"></span>
                    </a>
                    <input name="menuItemId" value="1" type="hidden" />
                  </td>
                  <td class="image-prod">
                    <div
                      class="img"
                      :style="{
                        'background-image': 'url(' + item.thumbnail + ')',
                      }"
                    ></div>
                  </td>

                  <td class="product-name">
                    <h3>{{ item.name }}</h3>
                  </td>

                  <td class="price">$ {{ item.price }}</td>

                  <td class="quantity">
                    <div class="input-group mb-3">
                      <input
                        type="text"
                        name="quantity"
                        class="quantity form-control input-number"
                        :value="item.quantity"
                        disabled
                      />
                    </div>
                  </td>

                  <td class="total">$1.03</td>
                </tr>
                <!-- END TR-->
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="row justify-content-end">
        <div
          class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate fadeInUp ftco-animated"
        >
          <div class="cart-total mb-3">
            <h3>Cart Totals</h3>
            <p class="d-flex">
              <span>Subtotal</span>
              <span>${{ total }}</span>
            </p>
            <p class="d-flex">
              <span>Delivery</span>
              <span>$0.00</span>
            </p>
            <p class="d-flex">
              <span>Discount</span>
              <span>$0.00</span>
            </p>
            <hr />
            <p class="d-flex total-price">
              <span>Total</span>
              <span>${{ total }}</span>
            </p>
          </div>
          <p class="text-center">
            <router-link to="/checkout" class="btn btn-primary py-3 px-4"
              >Proceed to Checkout</router-link
            >
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters(["cartItems", "total"]),
  },
  methods: {
    ...mapActions(["removeFromCart"]),
  },
};
</script>

<style lang="scss" scoped>
</style>