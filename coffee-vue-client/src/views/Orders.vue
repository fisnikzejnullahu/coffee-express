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
                
                
                    <tr>
                        <td>1
                        </td>
                        <td>16 Nov 2020</td>
                        <td><span class="status">•</span> ACCEPTED</td>
                        <td>$1.03</td>
                        <td><a href="/web-app/mvc/orders/e1d14010-9aab-46d4-93ca-c3d92e913162" class="view" title="View Details" data-toggle="tooltip" data-original-title="View Details"><i class="icon-fullscreen-enter"></i></a>
                            <a href="/web-app/mvc/orders/track/e1d14010-9aab-46d4-93ca-c3d92e913162" class="view" title="Track" data-toggle="tooltip" data-original-title="Track"><i class="icon-eye"></i></a>
                        </td>
                    </tr>
                    <!-- END TR-->
                

                </tbody>
            </table>
            <div class="clearfix">
                <div class="row" style="font-size: 1.5em;">
                    <div class="col" style="text-align: end;">
                        
                            
                            
                                <a href="#" class="view text-muted" style="pointer-events: none" title="" data-toggle="tooltip" data-original-title="View Details">Previous</a>
                            

                        
                    </div>
                    <div class="col" style="text-align: start;">

                        
                            
                            
                                <a href="#" class="view text-muted" style="pointer-events: none" title="" data-toggle="tooltip" data-original-title="View Details">Next</a>
                            

                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
  name: "Orders",
};
</script>

<style lang="scss" scoped>
</style>>