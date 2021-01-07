<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>
  <transition v-else name="slide">
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
                        Mitrovice, Kosovo 40000
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
                      <p style="font-weight: bold; color: #fff">
                        Payment Method
                      </p>
                      <select
                        @change="onSelectedBankAccount"
                        class="form-control"
                        v-if="editingBankAccount"
                        v-model="selectedBankAccountPosition"
                      >
                        <option disabled value="">Select</option>
                        <option
                          v-for="(bankAcc, index) in allBankAccounts"
                          :key="bankAcc.id"
                          :value="index"
                        >
                          Ending with {{ lastDigitsOfCC(bankAcc) }}
                        </option>
                      </select>
                      <p v-if="bankAccount !== null && !editingBankAccount">
                        Ending with {{ lastDigitsOfCC(bankAccount) }}
                        <span
                          @click="editBankAccount"
                          style="
                            text-decoration: underline;
                            margin-left: 5px;
                            color: #fff;
                            cursor: pointer;
                          "
                          >Edit</span
                        >
                      </p>

                      <p v-else-if="!editingBankAccount">
                        You don't have any payment method
                        <span
                          @click="addPaymentMethod"
                          style="
                            text-decoration: underline;
                            margin-left: 5px;
                            color: #fff;
                            cursor: pointer;
                          "
                          >Add</span
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
                    type="submit"
                    class="btn btn-primary py-3 px-4 load-button"
                    :class="{ 'loading-start': clicked }"
                    :disabled="clicked"
                    id="signin-btn"
                    style="width: 100%"
                  >
                    <span style="color: inherit">Place Order</span>
                    <svg
                      version="1.1"
                      id="loader-1"
                      xmlns="http://www.w3.org/2000/svg"
                      xmlns:xlink="http://www.w3.org/1999/xlink"
                      x="0px"
                      y="0px"
                      width="23px"
                      height="23px"
                      viewBox="0 0 50 50"
                      style="enable-background: new 0 0 50 50"
                      xml:space="preserve"
                    >
                      <path
                        fill="#000"
                        d="M43.935,25.145c0-10.318-8.364-18.683-18.683-18.683c-10.318,0-18.683,8.365-18.683,18.683h4.068c0-8.071,6.543-14.615,14.615-14.615c8.072,0,14.615,6.543,14.615,14.615H43.935z"
                      >
                        <animateTransform
                          attributeType="xml"
                          attributeName="transform"
                          type="rotate"
                          from="0 25 25"
                          to="360 25 25"
                          dur="0.6s"
                          repeatCount="indefinite"
                        ></animateTransform>
                      </path>
                    </svg>
                  </button>
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
  </transition>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import Api from "@/API";
import LoadingScreen from "@/components/LoadingScreen";
export default {
  data() {
    return {
      clicked: false,
      loaded: false,
      bankAccount: {},
      editingBankAccount: false,
      selectedBankAccountPosition: "",
      allBankAccounts: [],
    };
  },
  components: {
    LoadingScreen,
  },
  created() {
    this.getPopularBankAccount();
  },
  methods: {
    ...mapActions(["placeOrder", "resetCart"]),
    addPaymentMethod() {
      this.$router.push({
        path: "/add-bankacc",
        query: { redirectTo: "/checkout" },
      });
    },
    async getPopularBankAccount() {
      let popularBankAccountResponse = await Api.getMyPopularBankAccount(
        this.currentUser.id
      );
      if (popularBankAccountResponse.headers.get("content-length") == 0) {
        this.bankAccount = null;
      } else {
        let popularBankAccount = await popularBankAccountResponse.json();
        delete popularBankAccount["customer_id"];
        this.bankAccount = popularBankAccount;
      }
      this.loaded = true;
    },
    async place() {
      if (this.bankAccount === null) {
        alert("no payment method found");
        return;
      }
      this.clicked = true;
      let response = await this.placeOrder({
        // "bank_account_id": '01a2d5d8-4ddc-11eb-ae93-0242ac130002',
        bank_account_id: this.bankAccount.id,
        items: this.cartItems,
      });

      if (response.status === 201) {
        this.resetCart();
        this.clicked = false;
        let orderId = response.headers
          .get("Location")
          .substring(response.headers.get("Location").lastIndexOf("/") + 1);
        this.$router.push({ name: "OrderTrack", params: { id: orderId } });
      }
      this.resetCart();
    },
    editBankAccount() {
      this.editingBankAccount = true;
      this.getAllBankAccounts();
    },
    async getAllBankAccounts() {
      let id = this.currentUser.id;
      const response = await Api.getMyBankAccounts(id);
      if (response.headers.get("content-length") != 0) {
        this.allBankAccounts = await response.json();
      }
      console.log(this.allBankAccounts);
    },
    onSelectedBankAccount() {
      this.bankAccount = this.allBankAccounts[this.selectedBankAccountPosition];
      this.editingBankAccount = false;
    },
    lastDigitsOfCC(bankAccount) {
      return bankAccount["credit_card_info"]["card_number"]
        .toString()
        .substring(12);
    },
  },
  computed: {
    ...mapGetters(["currentUser", "cartItems", "total"]),
  },
};
</script>

<style lang="scss" scoped></style>
