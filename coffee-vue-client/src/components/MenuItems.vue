<template>
  <div v-if="!loaded">
    <LoadingScreen />
  </div>

  <div v-else class="row">
    <MenuItem v-for="item in menuItems" v-bind:key="item.id" :item="item" />
  </div>
</template>

<script>
import MenuItem from "@/components/MenuItem";
import LoadingScreen from "@/components/LoadingScreen";
import { mapActions, mapState } from "vuex";

export default {
  data() {
    return {
      loaded: false,
    };
  },
  components: {
    MenuItem,
    LoadingScreen,
  },
  computed: {
    ...mapState({
      menuItems: state => state.menuItems.all,
    }),
  },
  methods: mapActions(["getMenu"]),
  created() {
    this.getMenu();
    this.loaded = true;
  },
};
</script>

<style>
</style>