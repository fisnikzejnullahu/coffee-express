<template>
  <section class="ftco-section">
    <div class="container">
      <div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5
                class="modal-title"
                id="exampleModalLabel"
                style="color: inherit"
              >
                Delete Confirmation
              </h5>
              <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">×</span>
              </button>
            </div>
            <div class="modal-body">Are you sure you want to delete?</div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-dismiss="modal"
                id="closeModalBtn"
              >
                Close
              </button>
              <button
                type="button"
                class="btn btn-danger"
                id="deleteConfirmBtn"
              >
                Delete
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div
          class="col-4 ftco-animate fadeInUp ftco-animated"
          div-acc-id="70d273a8-03ec-11eb-adc1-0242ac120002"
        >
          <div class="row mt-2 pt-3 d-flex">
            <div class="col">
              <div class="cart-detail ftco-bg-dark p-3 p-md-4">
                <div class="form-group">
                  <div class="col">
                    <p style="font-weight: bold; color: #fff">Card</p>
                    <p>Card Number: 378282246310005</p>
                    <p>Expiration Date: 2022-11-16</p>
                    <button
                      type="button"
                      style="
                        background: transparent;
                        border: 0;
                        cursor: pointer;
                      "
                      data-toggle="modal"
                      data-target="#exampleModal"
                      data-cid="70d273a8-03ec-11eb-adc1-0242ac120002"
                    >
                      <span style="text-decoration: underline; color: #fff"
                        >Delete</span
                      >
                    </button>
                  </div>
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
export default {};
</script>

<style>
</style>