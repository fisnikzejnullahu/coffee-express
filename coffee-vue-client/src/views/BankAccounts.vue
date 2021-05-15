<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>
  <section v-else class="ftco-section">
    <b-alert
      :show="dismissCountDown"
      dismissible
      variant="success"
      @dismissed="dismissCountDown = 0"
      @dismiss-count-down="countDownChanged"
    >
      <p>{{ alertText }}</p>
    </b-alert>

    <ConfirmationModal
      message="Are you sure you want to delete?"
      :onConfirm="onDeleteConfirmed"
      :id="whichId"
    />

    <div class="container">
      <div class="pl-1 mt-2">
        <button
          class="btn btn-primary btn-outline-primary btn-lg btn-block"
          @click="showNewBankAccView"
        >
          Add new bank account
        </button>
      </div>
      <div class="row">
        <div v-for="account in bankAccounts" :key="account.id">
          <div class="col mt-2 ml-2 pt-3 d-flex">
            <div class="cart-detail ftco-bg-dark p-3 p-md-4">
              <div class="form-group">
                <div class="col">
                  <p style="font-weight: bold; color: #fff">Card</p>
                  <p>
                    Card Number:
                    {{ account["credit_card_info"]["card_number"] }}
                  </p>
                  <p>
                    Expiration Date:
                    {{ account["credit_card_info"]["expiration_date"] }}
                  </p>
                  <button
                    type="button"
                    style="background: transparent; border: 0; cursor: pointer"
                    data-toggle="modal"
                    data-target="#exampleModal"
                    data-cid="70d273a8-03ec-11eb-adc1-0242ac120002"
                  >
                    <span
                      style="text-decoration: underline; color: #fff"
                      @click="showConfirmationDialog(account.id)"
                      >Delete</span
                    >
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- .col-md-8 -->
      </div>
    </div>
  </section>
</template>

<script>
import { mapGetters } from "vuex";
import LoadingScreen from "@/components/LoadingScreen";
import ConfirmationModal from "@/components/ConfirmationModal";
import Api from "@/API";

export default {
  data() {
    return {
      bankAccounts: [],
      loaded: false,
      isModalVisible: false,
      whichId: "",
      dismissSecs: 3,
      dismissCountDown: 0,
      alertText: "",
    };
  },
  components: {
    LoadingScreen,
    ConfirmationModal,
  },
  async created() {
    let id = this.currentUser().id;
    const response = await Api.getMyBankAccounts(id);
    if (response.headers.get("content-length") != 0) {
      this.bankAccounts = await response.json();
      console.log(this.bankAccounts);
    }
    this.loaded = true;
  },
  methods: {
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert(text) {
      this.alertText = text;
      this.dismissCountDown = this.dismissSecs;
    },
    ...mapGetters(["currentUser"]),
    showConfirmationDialog(accountId) {
      this.whichId = accountId;
      this.$bvModal.show("confirm-modal");
    },
    async onDeleteConfirmed(id) {
      let response = await Api.deleteBankAccount(id);
      if (response.status === 200) {
        let index = this.bankAccounts
          .map(function (item) {
            return item.id;
          })
          .indexOf(id);

        this.showAlert("Deleted successfully");
        setTimeout(() => {
          this.bankAccounts.splice(index, 1);
        }, 1000);
      }
    },
    showNewBankAccView() {
      this.$router.push("/add-bankacc");
    },
  },
};
</script>

<style>
.alert {
  position: absolute !important;
  z-index: 300;
  right: 0;
  margin-right: 2%;
  top: 5%;
  width: 20%;
}
</style>
