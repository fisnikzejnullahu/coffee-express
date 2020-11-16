<template>
<section class="ftco-section ftco-cart">
    <div class="container">
        <div class="row">
            <div class="col-md-12 ftco-animate fadeInUp ftco-animated">
                <div class="cart-list" style="overflow: hidden">
                    <table class="table">
                        <thead class="thead-primary">
                        <tr class="text-center">
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        
                            <tr class="text-center">

                                <td class="product-remove">

                                    <form action="/web-app/mvc/cart/removeFromCart" method="post">
                                        <a href="javascript:" onclick="parentNode.submit();">
                                            <span class="icon-close"></span>
                                        </a>
                                        <input name="menuItemId" value="1" type="hidden">
                                    </form>
                                </td>
                                <td class="image-prod">
                                    <div class="img" style="background-image:url('https://globalassets.starbucks.com/assets/f12bc8af498d45ed92c5d6f1dac64062.jpg?impolicy=1by1_tight_288');"></div>
                                </td>

                                <td class="product-name">
                                    <h3>Caffè Americano</h3>
                                </td>

                                <td class="price">$1.03</td>

                                <td class="quantity">
                                    <div class="input-group mb-3">
                                        <input type="text" name="quantity" class="quantity form-control input-number" value="1" min="1" max="100">
                                    </div>
                                </td>

                                <td class="total">$1.03</td>
                            </tr>
                            <!-- END TR-->
                        

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate fadeInUp ftco-animated">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span>$1.03</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>$0.00</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span>$0.00</span>
                    </p>
                    <hr>
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span>$1.03</span>
                    </p>
                </div>
                <p class="text-center"><a href="/web-app/mvc/cart/checkout" class="btn btn-primary py-3 px-4">Proceed to Checkout</a>
                </p>
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