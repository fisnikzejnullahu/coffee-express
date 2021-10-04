<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>

  <section v-else class="ftco-section ftco-cart">
    <div class="container">
      <div class="row mt-3">
        <div class="col">
          <h4>Order Details:</h4>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
          <div class="cart-list" style="overflow: hidden">
            <table class="table">
              <thead class="thead-primary">
                <tr class="text-center">
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
                  v-for="item in order.orderDetails.items"
                  :key="item.menuItem.id"
                >
                  <td class="image-prod">
                    <div
                      class="img"
                      :style="{
                        'background-image':
                          'url(' + item.menuItem.thumbnail + ')',
                      }"
                    ></div>
                  </td>

                  <td class="product-name">
                    <h3>{{ item.menuItem.name }}</h3>
                  </td>

                  <td class="price">${{ item.menuItem.price }}</td>

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

                  <td class="total">$ {{ item.totalPrice }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
          <div class="cart-total mb-3">
            <h3>Order Information:</h3>
            <p class="d-flex total-price">
              <span>Order State</span>
              <span>{{ order.orderState }}</span>
            </p>
            <p class="d-flex">
              <span>Placed at</span>
              <span>{{ order.placedAt }}</span>
            </p>
            <p class="d-flex">
              <span>Accepted at</span>
              <span>{{ order.acceptedAt }}</span>
            </p>
            <p class="d-flex">
              <span>Started at</span>
              <span>{{ order.startedAt }}</span>
            </p>
            <p class="d-flex">
              <span>Finished at</span>
              <span>{{ order.finishedAt }}</span>
            </p>
            <hr />
            <p class="d-flex total-price">
              <span>Estimated Preparation time</span>
              <span>{{ order.estimatedPreparationTime }}</span>
            </p>
          </div>
        </div>
        <div class="col mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
          <div class="cart-total mb-3">
            <h3>Cart Totals</h3>
            <p class="d-flex">
              <span>Subtotal</span>
              <span>${{ order.orderDetails.totalOfOrder }}</span>
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
              <span>${{ order.orderDetails.totalOfOrder }}</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import Api from "@/API";
import LoadingScreen from "@/components/LoadingScreen";

export default {
  data() {
    return {
      loaded: false,
      order: {},
    };
  },
  components: {
    LoadingScreen,
  },
  async created() {
    let response = await Api.getOrder(this.$route.params.id);
    let body = await response.json();
    this.order = body;
    this.loaded = true;
  },
};
</script>

<style lang="scss" scoped>
</style>
