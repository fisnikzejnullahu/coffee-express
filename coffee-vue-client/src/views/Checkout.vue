<template>
  <section class="ftco-section">
    <div class="container">
      <div class="row">
        <div class="col-5 ftco-animate fadeInUp ftco-animated">
          <div class="row d-flex">
            <div class="col">
              <div class="cart-detail ftco-bg-dark p-3 p-md-4">
                <div class="form-group">
                  <div class="col">
                    <p style="font-weight: bold; color: #fff">
                      Billing Information
                    </p>
                    <p>Fisnik Zejnullahu</p>
                    <p>
                      Evlia Qelebia, Mitrovice, Kosovo
                      <span
                        style="
                          text-decoration: underline;
                          margin-left: 5px;
                          color: #fff;
                        "
                        >Edit</span
                      >
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row mt-2 pt-3 d-flex">
            <div class="col">
              <div class="cart-detail ftco-bg-dark p-3 p-md-4">
                <div class="form-group">
                  <div class="col">
                    <p style="font-weight: bold; color: #fff">Payment Method</p>
                    <p>
                      Ending with 005
                      <span
                        style="
                          text-decoration: underline;
                          margin-left: 5px;
                          color: #fff;
                        "
                        >Edit</span
                      >
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- .col-md-8 -->

        <div class="col-7 sidebar ftco-animate fadeInUp ftco-animated">
          <div class="row">
            <div class="col d-flex">
              <div class="cart-detail cart-total ftco-bg-dark p-3 p-md-4">
                <h3 class="billing-heading mb-4">Cart Total</h3>
                <p class="d-flex">
                  <span>Subtotal</span>
                  <span style="text-align: end">$20.60</span>
                </p>
                <p class="d-flex">
                  <span>Delivery</span>
                  <span style="text-align: end">$0.00</span>
                </p>
                <p class="d-flex">
                  <span>Discount</span>
                  <span style="text-align: end">$3.00</span>
                </p>
                <hr />
                <p class="d-flex total-price" style="font-size: 33px">
                  <span>Total</span>
                  <span style="text-align: end">$17.60</span>
                </p>
                <form action="/web-app/mvc/orders/place" method="post">
                  <a
                    href="javascript:"
                    onclick="parentNode.submit();"
                    class="btn btn-primary py-3 px-4"
                    >Place Order</a
                  >
                  <input
                    name="bankAccountId"
                    value="70d273a8-03ec-11eb-adc1-0242ac120002"
                    type="hidden"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
</style>