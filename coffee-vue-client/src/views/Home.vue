<template>
  <section class="home-slider owl-carousel">
    <div
      class="slider-item"
      :style="{
        'background-image': 'url(' + require('@/assets/images/bg_3.jpg') + ')',
      }"
    >
      <div class="overlay"></div>
      <div class="container">
        <div
          class="row slider-text justify-content-center align-items-center"
          data-scrollax-parent="true"
        >
          <div class="col-md-8 col-sm-12 text-center ftco-animate">
            <span class="subheading">Welcome</span>
            <h1 class="mb-4">Amazing Taste &amp; Beautiful Place</h1>
            <p class="mb-4 mb-md-5">
              A small river named Duden flows by their place and supplies it
              with the necessary regelialia.
            </p>
            <p>
              <!-- <a href="#" class="btn btn-primary p-3 px-xl-4 py-xl-3">Order Now</a> -->
              <router-link
                to="/menu"
                class="btn btn-white btn-outline-white p-3 px-xl-4 py-xl-3"
                >View Menu</router-link
              >
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
// @ is an alias to /src

export default {
  name: "Home",
};
</script>

<style scoped lang="scss">
.ftco-animate {
  opacity: 1;
  visibility: visible;
}
</style>