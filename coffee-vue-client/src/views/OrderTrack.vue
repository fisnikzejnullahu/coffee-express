<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>
  <div v-else class="container padding-bottom-3x mb-1" style="padding-top: 7em">
    <div class="card mb-3">
      <div class="p-4 text-center text-white text-lg bg-dark rounded-top">
        <span class="text-uppercase">Tracking Order No - </span
        ><span class="text-medium">{{ this.$route.params.id }}</span>
      </div>
      <div
        class="d-flex flex-wrap flex-sm-nowrap justify-content-between py-3 px-2 bg-secondary"
      >
        <div class="w-100 text-center py-1 px-2">
          <span class="text-medium" id="order-status"
            >Status: {{ order["order_state"] }}</span
          >
          <span v-if="order['cancelled_reason']"
            >, Reason: {{ order["cancelled_reason"] }}</span
          >
        </div>
      </div>
      <div class="card-body">
        <div
          class="steps d-flex flex-wrap flex-sm-nowrap justify-content-between padding-top-2x padding-bottom-1x"
        >
          <div class="step" :class="{ completed: stepsCompleted >= 1 }" id="placed">
            <div class="step-icon-wrap">
              <div class="step-icon"><i class="pe-7s-more"></i></div>
            </div>
            <h4 class="step-title">Order Placed</h4>
          </div>
          <div class="step" :class="{ completed: stepsCompleted >= 2 }" id="accepted">
            <div class="step-icon-wrap">
              <div class="step-icon"><i class="pe-7s-check"></i></div>
            </div>
            <h4 class="step-title">Order Accepted</h4>
          </div>
          <div class="step" :class="{ completed: stepsCompleted >= 3 }" id="preparing">
            <div class="step-icon-wrap">
              <div class="step-icon"><i class="pe-7s-coffee"></i></div>
            </div>
            <h4 class="step-title">Preparing</h4>
          </div>
          <div class="step" :class="{ completed: stepsCompleted >= 4 }" id="ready">
            <div class="step-icon-wrap">
              <div class="step-icon"><i class="pe-7s-car"></i></div>
            </div>
            <h4 class="step-title">Ready for pickup</h4>
          </div>
          <div class="step" :class="{ completed: stepsCompleted >= 5 }" id="pickedup">
            <div class="step-icon-wrap">
              <div class="step-icon"><i class="pe-7s-coffee"></i></div>
            </div>
            <h4 class="step-title">Picked Up</h4>
          </div>
        </div>
      </div>
    </div>
    <div
      class="d-flex flex-wrap flex-md-nowrap justify-content-center justify-content-sm-between align-items-center"
    >
      <div class="text-left text-sm-right">
        <router-link
          :to="{ name: 'OrderDetails', params: { id: order.id } }"
          class="btn btn-outline-primary btn-rounded btn-sm"
          >View Order Details</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import LoadingScreen from "@/components/LoadingScreen";
import Api from "@/API";
export default {
  data() {
    return {
      order: {},
      stepsCompleted: 1,
      loaded: false,
    };
  },
  created() {
    this.trackOrder();
  },
  components: {
    LoadingScreen,
  },
  methods: {
    async trackOrder() {
      const response = await Api.trackOrder(this.$route.params.id);
      const body = await response.json();
      this.order = body;
      this.orderStateCheck(this.order["order_state"]);
      this.loaded = true;
      if (this.stepsCompleted < 4) {
        setTimeout(() => {
          this.trackOrder();
        }, 2000);
      }
    },
    orderStateCheck(state) {
      console.log(state);
      switch (state) {
        case "PLACED":
          this.stepsCompleted = 1;
          break;
        case "ACCEPTED":
          this.stepsCompleted = 2;
          break;
        case "PREPARING":
          this.stepsCompleted = 3;
          break;
        case "READY_FOR_PICKUP":
          this.stepsCompleted = 4;
          break;
        case "PICKED_UP":
          this.stepsCompleted = 5;
          break;
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import url(https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css);

body {
  margin-top: 20px;
}

.steps .step {
  display: block;
  width: 100%;
  margin-bottom: 35px;
  text-align: center;
}

.steps .step .step-icon-wrap {
  display: block;
  position: relative;
  width: 100%;
  height: 80px;
  text-align: center;
}

.steps .step .step-icon-wrap::before,
.steps .step .step-icon-wrap::after {
  display: block;
  position: absolute;
  top: 50%;
  width: 50%;
  height: 3px;
  margin-top: -1px;
  background-color: #e1e7ec;
  content: "";
  z-index: 1;
}

.steps .step .step-icon-wrap::before {
  left: 0;
}

.steps .step .step-icon-wrap::after {
  right: 0;
}

.steps .step .step-icon {
  display: inline-block;
  position: relative;
  width: 80px;
  height: 80px;
  border: 1px solid #e1e7ec;
  border-radius: 50%;
  background-color: #f5f5f5;
  color: #374250;
  font-size: 38px;
  line-height: 81px;
  z-index: 5;
}

.steps .step .step-title {
  margin-top: 16px;
  margin-bottom: 0;
  color: #606975;
  font-size: 14px;
  font-weight: 500;
}

.steps .step:first-child .step-icon-wrap::before {
  display: none;
}

.steps .step:last-child .step-icon-wrap::after {
  display: none;
}

.steps .step.completed .step-icon-wrap::before,
.steps .step.completed .step-icon-wrap::after {
  background-color: #28a745;
}

.steps .step.completed .step-icon {
  border-color: #28a745;
  background-color: #28a745;
  color: #fff;
}

@media (max-width: 576px) {
  .flex-sm-nowrap .step .step-icon-wrap::before,
  .flex-sm-nowrap .step .step-icon-wrap::after {
    display: none;
  }
}

@media (max-width: 768px) {
  .flex-md-nowrap .step .step-icon-wrap::before,
  .flex-md-nowrap .step .step-icon-wrap::after {
    display: none;
  }
}

@media (max-width: 991px) {
  .flex-lg-nowrap .step .step-icon-wrap::before,
  .flex-lg-nowrap .step .step-icon-wrap::after {
    display: none;
  }
}

@media (max-width: 1200px) {
  .flex-xl-nowrap .step .step-icon-wrap::before,
  .flex-xl-nowrap .step .step-icon-wrap::after {
    display: none;
  }
}

.bg-faded,
.bg-secondary {
  background-color: #f5f5f5 !important;
}
</style>
