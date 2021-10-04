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
    <div class="container" style="padding-top: 1em">
      <div class="row justify-content-center">
        <form @submit.prevent="saveChanges">
          <div class="form-row">
            <div class="form-group col-md-6">
              <label for="firstname">Firstname</label>
              <input
                type="text"
                v-model.trim="firstname"
                class="form-control"
                required
              />
            </div>
            <div class="form-group col-md-6">
              <label for="lastname">Lastname</label>
              <input
                type="text"
                v-model.trim="lastname"
                class="form-control"
                required
              />
            </div>
          </div>
          <div class="form-group">
            <label for="username">Username</label>
            <input
              type="text"
              v-model.trim="username"
              class="form-control"
              required
              disabled
              readonly
            />
          </div>
          <div class="form-group">
            <label for="registerDate">Registered At</label>
            <input
              type="text"
              v-model="registerDate"
              class="form-control"
              required
              disabled
              readonly
            />
          </div>
          <hr class="my-4" />
          <div class="row mb-4">
            <div class="col-md-6">
              <div class="form-group">
                <label for="newPassword">New Password</label>
                <input
                  type="password"
                  class="form-control"
                  v-model.trim="newPassword"
                  minlength="6"
                />
              </div>
              <div class="form-group">
                <label for="newPasswordConfirm">Confirm Password</label>
                <input
                  type="password"
                  class="form-control"
                  v-model.trim="newPasswordConfirm"
                  minlength="6"
                />
              </div>
            </div>
            <div class="col-md-6">
              <p class="mb-2">Password requirements</p>
              <p class="small text-muted mb-2">
                To create a new password, you have to meet all of the following
                requirements:
              </p>
              <ul class="small text-muted pl-4 mb-0">
                <li>Minimum 6 characters</li>
                <li>Can’t be the same as a previous password</li>
              </ul>
            </div>
          </div>
          <button
            type="submit"
            class="btn btn-primary btn-lg btn-block load-button"
            :class="{ 'loading-start': clicked }"
            :disabled="clicked"
            id="signin-btn"
            style="width: 100%"
          >
            <span style="color: inherit">Save Changes</span>
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
    </div>
  </section>
</template>

<script>
import Api from "@/API";
import LoadingScreen from "@/components/LoadingScreen";
import { mapGetters } from "vuex";
export default {
  name: "Orders",
  data() {
    return {
      firstname: "",
      lastname: "",
      username: "",
      registerDate: "",
      newPassword: "",
      newPasswordConfirm: "",
      clicked: false,
      loaded: false,
      dismissSecs: 3,
      dismissCountDown: 0,
      alertText: "",
    };
  },
  components: {
    LoadingScreen,
  },
  computed: {
    ...mapGetters(["currentUser"]),
  },
  async created() {
    let response = await Api.getMyAccountInfo(this.currentUser.id);
    let user = await response.json();
    this.firstname = user["first_name"];
    this.lastname = user["last_name"];
    this.username = user["username"];
    this.registerDate = user["registered_at"];
    this.loaded = true;
  },
  methods: {
    async saveChanges() {
      if (this.newPassword !== this.newPasswordConfirm) {
        alert("passwords do not match");
        return;
      }

      this.clicked = true;
      let response = await Api.updateProfile(this.currentUser.id, {
        account_id: this.currentUser["account_id"],
        first_name: this.firstname,
        last_name: this.lastname,
        username: this.username,
        new_password: this.newPassword.length !== 0 ? this.newPassword : null,
      });

      this.clicked = false;
      if (response.status === 204) {
        this.showAlert("Changes saved");
        this.newPassword = "";
        this.newPasswordConfirm = "";
      }
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert(text) {
      this.alertText = text;
      this.dismissCountDown = this.dismissSecs;
    },
  },
};
</script>

<style lang="scss" scoped>
.form-control:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>>
