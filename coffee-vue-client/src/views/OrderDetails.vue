<template>
  <section class="ftco-section ftco-cart">
    <div class="container">
      <div class="row">
        <div class="col">
          <h4>Order Details:</h4>
        </div>
        <div class="col" style="text-align: end">
          <p>Order Number: {{ this.$route.params.id }}</p>
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
                <tr class="text-center">
                  <td class="image-prod">
                    <div
                      class="img"
                      style="
                        background-image: url('https://globalassets.starbucks.com/assets/f12bc8af498d45ed92c5d6f1dac64062.jpg?impolicy=1by1_tight_288');
                      "
                    ></div>
                  </td>

                  <td class="product-name">
                    <h3>Caffè Americano</h3>
                  </td>

                  <td class="price">$1.03</td>

                  <td class="quantity">
                    <div class="input-group mb-3">
                      <input
                        type="text"
                        name="quantity"
                        class="quantity form-control input-number"
                        value="1"
                        min="1"
                        max="100"
                      />
                    </div>
                  </td>

                  <td class="total">$ 1.03</td>
                </tr>
                <!-- END TR-->
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
          <div class="cart-total mb-3">
            <h3>Kitchen details:</h3>
            <p class="d-flex">
              <span>Placed at</span>
              <span>16 Nov 2020, 18:21:19</span>
            </p>
            <p class="d-flex">
              <span>Accepted at</span>
              <span>16 Nov 2020, 18:21:21</span>
            </p>
            <p class="d-flex">
              <span>Finished at</span>
              <span>16 Nov 2020, 18:23:13</span>
            </p>
            <hr />
            <p class="d-flex total-price">
              <span>Preparation time</span>
              <span>2 minutes</span>
            </p>
          </div>
        </div>
        <div class="col mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
          <div class="cart-total mb-3">
            <h3>Cart Totals</h3>
            <p class="d-flex">
              <span>Subtotal</span>
              <span>$1.03</span>
            </p>
            <p class="d-flex">
              <span>Delivery</span>
              <span>$0.00</span>
            </p>
            <p class="d-flex">
              <span>Discount</span>
              <span>$3.00</span>
            </p>
            <hr />
            <p class="d-flex total-price">
              <span>Total</span>
              <span>$ 1.03</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  created() {
    console.log(this.$route.params.id);
  },
};
</script>

<style>
</style>