<template>
  <section class="ftco-section">
    <div class="container">
      <div class="card-body p-5">
        <h4>Create a new bank account</h4>
        <form role="form" class="mt-4" @submit.prevent="create">
          <div class="form-group">
            <label for="username">Full name (on the card)</label>
            <div class="input-group">
              <div class="input-group-prepend">
                <span
                  class="input-group-text"
                  style="background-color: #c49b63 !important; border: 0"
                  ><i class="icon-user" style="font-size: 1.5em"></i
                ></span>
              </div>
              <input
                v-model="cardName"
                type="text"
                class="form-control pl-4"
                name="username"
                placeholder="name"
                required
              />
            </div>
            <!-- input-group.// -->
          </div>
          <!-- form-group.// -->

          <div class="form-group">
            <label for="cardNumber">Card number</label>
            <div class="input-group">
              <div class="input-group-prepend">
                <span
                  class="input-group-text"
                  style="background-color: #c49b63 !important; border: 0"
                  ><i class="icon-credit-card" style="font-size: 1.5em"></i
                ></span>
              </div>
              <input
                v-model="cardNumber"
                required
                type="text"
                class="form-control pl-4"
                name="cardNumber"
                placeholder="number"
              />
            </div>
            <!-- input-group.// -->
          </div>
          <!-- form-group.// -->

          <div class="row">
            <div class="col-sm-8">
              <div class="form-group">
                <label><span class="hidden-xs">Expiration</span> </label>
                <div class="form-inline">
                  <select class="form-control" style="width: 45%" v-model="expMonth">
                    <option disabled selected>MM</option>
                    <option value="01">01 - January</option>
                    <option value="02">02 - February</option>
                    <option value="03">03 - March</option>
                    <option value="04">04 - April</option>
                    <option value="05">05 - May</option>
                    <option value="06">06 - June</option>
                    <option value="07">07 - July</option>
                    <option value="08">08 - August</option>
                    <option value="09">9 - September</option>
                    <option value="10">10 - October</option>
                    <option value="11">11 - November</option>
                    <option value="12">12 - December</option>
                  </select>
                  <span style="width: 10%; text-align: center"> / </span>
                  <select class="form-control" style="width: 45%" v-model="expYear">
                    <option disabled selected>YY</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                    <option value="2026">2026</option>
                    <option value="2027">2027</option>
                    <option value="2028">2028</option>
                    <option value="2029">2029</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="col-sm-4">
              <div class="form-group">
                <label
                  data-toggle="tooltip"
                  title=""
                  data-original-title="3 digits code on back side of the card"
                  >CVV <i class="fa fa-question-circle"></i
                ></label>
                <input class="form-control" required type="text" v-model="cvc" />
              </div>
              <!-- form-group.// -->
            </div>
          </div>
          <!-- row.// -->
          <button
            type="submit"
            class="subscribe btn btn-primary btn-block btn-lg mt-2 load-button"
            :class="{ 'loading-start': clicked }"
            :disabled="clicked"
            id="signin-btn"
            style="width: 100%"
          >
            <span style="color: inherit">Create</span>
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
        </form>
      </div>
      <!-- card-body.// -->
    </div>
  </section>
</template>

<script>
import { mapGetters } from "vuex";
import Api from "@/API";

export default {
  data() {
    return {
      clicked: false,
      cardName: "",
      cardNumber: "",
      expMonth: "MM",
      expYear: "YY",
      cvc: "",
    };
  },
  computed: {
    ...mapGetters(["currentUser"]),
  },
  methods: {
    async create() {
      this.clicked = true;
      let response = await Api.createBankAccount({
        customer_id: this.currentUser.id,
        credit_card_info: {
          card_number: this.cardNumber,
          expiration_date: this.expYear + "-" + this.expMonth + "-01",
          cvc: this.cvc,
        },
      });
      if (response.status === 201) {
        this.clicked = false;
        setTimeout(() => {
          if (this.$route.query.redirectTo) {
            this.$router.push(this.$route.query.redirectTo);
          } else {
            this.$router.push("/bankaccounts");
          }
        }, 500);
      }
    },
  },
};
</script>

<style>
.form-control option {
  background: #000000;
}
</style>
