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
                  <menu-items />
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
import MenuItems from "@/components/MenuItems";

export default {
  name: "Menu",
  components: {
    "menu-items": MenuItems,
  },
};
</script>

<style scoped lang="scss">
</style>