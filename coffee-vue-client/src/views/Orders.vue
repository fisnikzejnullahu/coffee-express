<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>

  <div v-else class="container" style="padding-top: 7em">
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
              <td>{{ 1 + index + page * 5 }}</td>
              <td>{{ order.placedAt }}</td>
              <td><span class="status">•</span> {{ order.orderState }}</td>
              <td>${{ order.orderDetails.totalOfOrder }}</td>
              <td>
                <router-link
                  :to="{ name: 'OrderDetails', params: { id: order.orderId } }"
                  class="view"
                  title="View Details"
                  data-toggle="tooltip"
                  data-original-title="View Details"
                  ><i class="icon-fullscreen-enter" style="font-size: 1.5em"
                    ></i
                  ></router-link
                >
                <router-link
                  :to="{ name: 'OrderTrack', params: { id: order.orderId } }"
                  class="view"
                  title="Track"
                  data-toggle="tooltip"
                  data-original-title="Track"
                  ><i class="icon-eye" style="font-size: 1.5em"></i
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
                type="button"
                class="view"
                :class="page == 0 ? 'text-muted' : ''"
                @click="page != 0 ? previousPage() : null"
                title=""
                data-toggle="tooltip"
                data-original-title="View Details"
                >Previous</a
              >
            </div>
            <div class="col" style="text-align: start">
              <a
                type="button"
                class="view"
                :class="page + 1 == totalPages ? 'text-muted' : ''"
                @click="page + 1 != totalPages ? nextPage() : null"
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
import LoadingScreen from "@/components/LoadingScreen";
import { mapGetters } from "vuex";
export default {
  name: "Orders",
  data() {
    return {
      orders: [],
      page: 0,
      totalPages: 0,
      loaded: false,
    };
  },
  components: {
    LoadingScreen,
  },
  created() {
    this.fetchMyOrders();
  },
  computed: {
    ...mapGetters(["currentUser"]),
  },
  methods: {
    async fetchMyOrders() {
      console.log(this.page);
      const response = await Api.getMyOrders(this.currentUser.id, this.page);
      const body = await response.json();
      console.log(body);
      this.totalPages = body["total_pages"];
      this.orders = body.orders;
      this.loaded = true;
    },
    async nextPage() {
      this.page++;
      this.fetchMyOrders();
    },
    async previousPage() {
      this.page--;
      this.fetchMyOrders();
    },
  },
};
</script>

<style lang="scss" scoped>
.table {
  color: gray;
}
.table-hover tbody tr {
  cursor: pointer;
}

.table-hover tbody tr:hover {
  color: #ffffff;
}

.view:hover {
  filter: brightness(0.8);
}
</style>
>
