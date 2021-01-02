<template>
  <b-modal id="confirm-modal" title="Confirm" @ok="ok">
    <p>{{ message }}</p>
  </b-modal>
</template>

<script>
export default {
  props: {
      message: String,
      onConfirm: Function,
      id: String,
  },
  methods: {
    ok() {
      this.onConfirm(this.id);
    },
  },
};
</script>

<style>
#confirm-modal .modal-title {
  color: #000000;
}
</style>