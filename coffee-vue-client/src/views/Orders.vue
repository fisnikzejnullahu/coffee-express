<template>
  <div class="container" style="padding-top: 7em">
    <div class="table-responsive" style="overflow: hidden">
      <div class="table-wrapper">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>#</th>
              <th>Order Date</th>
              <th>Status</th>
              <th>Net Amount</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(order, index) in orders" :key="order.orderId">
              <td>{{ index }}</td>
              <td>{{ order.finishedAt }}</td>
              <td><span class="status">•</span> {{ order.orderState }}</td>
              <td>${{ order.orderDetails.totalOfOrder }}</td>
              <td>
                <router-link
                  :to="{ name: 'OrderDetails', params: { id: order.orderId } }"
                  class="view"
                  title="View Details"
                  data-toggle="tooltip"
                  data-original-title="View Details"
                  ><i class="icon-fullscreen-enter"></i></router-link
                >
                <router-link
                  :to="{ name: 'OrderTrack', params: { id: order.orderId } }"
                  class="view"
                  title="Track"
                  data-toggle="tooltip"
                  data-original-title="Track"
                  ><i class="icon-eye"></i
                ></router-link>
              </td>
            </tr>
            <!-- END TR-->
          </tbody>
        </table>
        <div class="clearfix">
          <div class="row" style="font-size: 1.5em">
            <div class="col" style="text-align: end">
              <a
                href="#"
                class="view text-muted"
                style="pointer-events: none"
                title=""
                data-toggle="tooltip"
                data-original-title="View Details"
                >Previous</a
              >
            </div>
            <div class="col" style="text-align: start">
              <a
                href="#"
                class="view text-muted"
                style="pointer-events: none"
                title=""
                data-toggle="tooltip"
                data-original-title="View Details"
                >Next</a
              >
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Api from "@/API";
import { mapGetters } from "vuex";
export default {
  name: "Orders",
  data() {
    return {
      orders: [],
      page: 0,
    };
  },
  created() {
    this.fetchMyOrders();
  },
  methods: {
    ...mapGetters(["currentUser"]),
    async fetchMyOrders() {
      const response = await Api.getMyOrders(this.currentUser().id, this.page);
      const body = await response.json();
      this.orders = body.orders;
      console.log(this.orders);
    },
    async nextPage() {
      this.page++;
      this.fetchMyOrders();
    },
  },
};
</script>

<style lang="scss" scoped>
.table {
  color: gray;
}
</style>>