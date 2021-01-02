<template>
  <section class="ftco-section">
    <div class="container">
      <div class="row">
        <div class="col-5 ftco-animate fadeInUp ftco-animated">
          <div class="row d-flex">
            <div class="col">
              <div class="cart-detail ftco-bg-dark p-3 p-md-4">
                <div class="form-group">
                  <div class="col">
                    <p style="font-weight: bold; color: #fff">
                      Billing Information
                    </p>
                    <p>Fisnik Zejnullahu</p>
                    <p>
                      Evlia Qelebia, Mitrovice, Kosovo
                      <span
                        style="
                          text-decoration: underline;
                          margin-left: 5px;
                          color: #fff;
                        "
                        >Edit</span
                      >
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row mt-2 pt-3 d-flex">
            <div class="col">
              <div class="cart-detail ftco-bg-dark p-3 p-md-4">
                <div class="form-group">
                  <div class="col">
                    <p style="font-weight: bold; color: #fff">Payment Method</p>
                    <p>
                      Ending with {{lastDigitsOfCC}}
                      <span
                        style="
                          text-decoration: underline;
                          margin-left: 5px;
                          color: #fff;
                        "
                        >Edit</span
                      >
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- .col-md-8 -->

        <div class="col-7 sidebar ftco-animate fadeInUp ftco-animated">
          <div class="row">
            <div class="col d-flex">
              <div class="cart-detail cart-total ftco-bg-dark p-3 p-md-4">
                <h3 class="billing-heading mb-4">Cart Total</h3>
                <p class="d-flex">
                  <span>Subtotal</span>
                  <span style="text-align: end">${{ total }}</span>
                </p>
                <p class="d-flex">
                  <span>Delivery</span>
                  <span style="text-align: end">$0.00</span>
                </p>
                <p class="d-flex">
                  <span>Discount</span>
                  <span style="text-align: end">$0.00</span>
                </p>
                <hr />
                <p class="d-flex total-price" style="font-size: 33px">
                  <span>Total</span>
                  <span style="text-align: end">${{ total }}</span>
                </p>
                <!-- <form action="/web-app/mvc/orders/place" method="post"> -->
                  <button
                    @click="place"
                    class="btn btn-primary py-3 px-4"
                    >Place Order</button>
                  <input
                    name="bankAccountId"
                    value="70d273a8-03ec-11eb-adc1-0242ac120002"
                    type="hidden"
                  />
                <!-- </form> -->
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
  data() {
    return {
      bankAccount: {}
    }
  },
  created() {
    this.bankAccount = this.$store.getters.popularBankAccount;
    console.log(this.bankAccount);
  },
  methods: {
    ...mapActions(['placeOrder', 'resetCart']),
    async place() {
      let customerId = this.currentUser.id;
      let response = await this.placeOrder({
        "customer_id": customerId,
        "bank_account_id": this.bankAccount.id, 
        "items": this.cartItems});

        if (response.status === 201) {
          this.resetCart();
          let orderId = response.headers.get('Location').substring(response.headers.get('Location').lastIndexOf("/") + 1);
          this.$router.push({ name: 'OrderTrack', params: { id:  orderId} });
        }
    }
  },
  computed: {
    ...mapGetters(["currentUser", "cartItems", "total"]),
    lastDigitsOfCC() {
      return this.bankAccount["credit_card_info"]["card_number"].toString().substring(11);
    }
  }
};
</script>

<style lang="scss" scoped>
</style>