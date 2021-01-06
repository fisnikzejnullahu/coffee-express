<template>
  <section class="ftco-menu mb-5">
    <div class="container">
      <div class="row d-md-flex">
        <div class="col-lg-12 ftco-animate p-md-5 fadeInUp ftco-animated">
          <div class="row">
            <div class="col-md-12 d-flex align-items-center">
              <div
                class="tab-content ftco-animate fadeInUp ftco-animated"
                id="v-pills-tabContent"
              >
                <div
                  class="tab-pane fade show active"
                  id="v-pills-0"
                  role="tabpanel"
                  aria-labelledby="v-pills-0-tab"
                >
                  <div v-if="!loaded">
                    <LoadingScreen />
                  </div>

                  <div v-else class="row">
                    <MenuItem
                      v-for="item in menuItems"
                      v-bind:key="item.id"
                      :item="item"
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import MenuItem from "@/components/MenuItem";
import LoadingScreen from "@/components/LoadingScreen";
import { mapActions, mapState } from "vuex";

export default {
  name: "Menu",
  components: {
    MenuItem,
    LoadingScreen,
  },
  data() {
    return {
      loaded: false,
    };
  },
  computed: {
    ...mapState({
      menuItems: (state) => state.menuItems.all,
    }),
  },
  methods: mapActions(["getMenu"]),
  async created() {
    await this.getMenu();
    this.loaded = true;
  },
};
</script>

<style scoped lang="scss"></style>
