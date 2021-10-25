package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderSystem;
import com.fisnikz.coffee_express.orders.entity.Order;
import com.fisnikz.coffee_express.orders.entity.OrderItem;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author airhacks.com
 */
public class OrdersResourceIT {

    private OrderSystem orderSystem;

    public static void main(String[] args) {
        String[] bankAccountIds = {"70d273a8-03ec-11eb-adc1-0242ac120002", "3af3a6e2-57cb-4cf0-9608-f8190040b97b",
                "e90770f3-0522-47d3-a9be-c9ef9402326e",};

        String[] accessTokens = {"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ5TjFIX0dHQXBlUFJBdWNXQlgxbDdZZExvNXRqb3RkcFNzQm9tWlBWc0ljIn0.eyJleHAiOjE2MzQxMzk0MzAsImlhdCI6MTYzNDEzOTEzMCwianRpIjoiY2NlZDliOTUtMDc5NC00YjdjLTgzZjQtYTk4ODJhMzZiZWI5IiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjgwODAvcmVhbG1zL3B1YmxpYyIsImF1ZCI6WyJyZWFsbS1tYW5hZ2VtZW50IiwiYWNjb3VudCJdLCJzdWIiOiI1YTBmODA0Yi1iM2FiLTQ5NjAtOTQxNC1kNjBhMDkwZTY2YjAiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJjb2ZmZWUtZXhwcmVzcy1hZG1pbi1hcGktY2xpZW50Iiwic2Vzc2lvbl9zdGF0ZSI6ImU4ZjI4NzU5LTYzMDAtNDAwZi1iNzQyLTdhMTA3ZWMzYzRmOCIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZnVsbF9hY2Nlc3MiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwiZGVmYXVsdC1yb2xlcy1wdWJsaWMiXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbInZpZXctcmVhbG0iLCJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwicmVhbG0tYWRtaW4iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwicXVlcnktcmVhbG1zIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsIm1hbmFnZS1yZWFsbSIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1hdXRob3JpemF0aW9uIiwibWFuYWdlLWNsaWVudHMiLCJxdWVyeS1ncm91cHMiXX0sImNvZmZlZS1leHByZXNzLWFkbWluLWFwaS1jbGllbnQiOnsicm9sZXMiOlsibWFuYWdlX3JlYWxtIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiJlOGYyODc1OS02MzAwLTQwMGYtYjc0Mi03YTEwN2VjM2M0ZjgiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IkZpc25payBaZWpudWxsYWh1IiwiZ3JvdXBzIjpbImZ1bGxfYWNjZXNzIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImRlZmF1bHQtcm9sZXMtcHVibGljIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImZpc25pa3oiLCJnaXZlbl9uYW1lIjoiRmlzbmlrIiwiY3VzdG9tZXJfaWQiOiIwNDVjZjE5ZS0zNGI5LTRkMWUtYTU2Ni05MjE4NzQxMjlmZjAiLCJmYW1pbHlfbmFtZSI6Ilplam51bGxhaHUifQ.P_XTATu9WegkW3vSXq9VgEwirOeZewWkF7jR8QGfds95iBBofCIuKoeE_eK89GluRD8gGTOPjAy60PLIAt8JUquCS8lU96pgGnpi0lpRyxG21ckwxKCJSC0w740J9voBx5jQkMXU8zyOZDFVrwLogYF6pxe2MXAos8cHe3n-Wcdf-tg1y2MmG_YPb-ucWUvGkhjbS8bIz_rbasoJoBhQANgIrukjfbO3iVM1TApj72R8olpUzVJbIXbZrdmLmv30a9WRWqSQP2kQa9YpRHu429lC2Se9ZBiNqhj-AsJuNyDXcYYe--cusnIEUI6Oio8a0InANanun1-b7eoatRFk-g"};

        ExecutorService executorService = Executors.newFixedThreadPool(12);
        OrderSystem orderSystem = new OrderSystem();
        for (int i = 0; i < 50; i++) {
            int randomNumber = new Random().nextInt(3);
            var bankAccountId = bankAccountIds[0];
            var accessToken = accessTokens[0];
            Order order = newOrder(bankAccountId);


            CompletableFuture.supplyAsync(() -> orderSystem.placeOrder(order, accessToken), executorService)
                    .thenAccept(createdOrderLocation -> {
                        String orderId = createdOrderLocation.toString().substring(createdOrderLocation.toString().lastIndexOf("/"));
                        System.out.println("OrderID: = " + orderId);
                    }).exceptionally(throwable -> {
                System.out.println("Ex: " + throwable.getMessage());
//                throwable.printStackTrace();
                return null;
            });
        }

        System.out.println("done, placed orders: ");
    }

//    @BeforeEach
//    void init() {
//        this.orderSystem = new OrderSystem();
//    }
//
//    @Test
//    void placeAndFind() {
//        String[] bankAccountIds = {"70d273a8-03ec-11eb-adc1-0242ac120002", "3af3a6e2-57cb-4cf0-9608-f8190040b97b",
//                "e90770f3-0522-47d3-a9be-c9ef9402326e",};
//
//        String[] accessTokens = {"eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmVTJGZzM4ZURQUUVNZjh2MFBRb3M5T3BYLVpPOXpxdzNVcG1xaGdwQVM4In0.eyJleHAiOjE2MzMyNzcyMjgsImlhdCI6MTYzMzI3NjkyOCwianRpIjoiNDg2ZGFjMWEtNTJkYS00ODgxLTg1Y2UtNTI1ZGY5MGM4ODMyIiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjgwODAvYXV0aC9yZWFsbXMvcHVibGljIiwiYXVkIjpbInJlYWxtLW1hbmFnZW1lbnQiLCJhY2NvdW50Il0sInN1YiI6ImZlY2UyZTNmLWNlODItNDEzZC04YzdhLTYxZjlmNmQ2ZGZlNCIsInR5cCI6IkJlYXJlciIsImF6cCI6ImNvZmZlZS1leHByZXNzLWFkbWluLWFwaS1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiNjdlNGFhM2MtMDk4Zi00NTgzLWFmOWUtYTMwZjZkNGI0NjRjIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJmdWxsX2FjY2VzcyIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLXB1YmxpYyIsInVzZXJfZGF0YSJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlYWxtLW1hbmFnZW1lbnQiOnsicm9sZXMiOlsidmlldy1yZWFsbSIsInZpZXctaWRlbnRpdHktcHJvdmlkZXJzIiwibWFuYWdlLWlkZW50aXR5LXByb3ZpZGVycyIsImltcGVyc29uYXRpb24iLCJyZWFsbS1hZG1pbiIsImNyZWF0ZS1jbGllbnQiLCJtYW5hZ2UtdXNlcnMiLCJxdWVyeS1yZWFsbXMiLCJ2aWV3LWF1dGhvcml6YXRpb24iLCJxdWVyeS1jbGllbnRzIiwicXVlcnktdXNlcnMiLCJtYW5hZ2UtZXZlbnRzIiwibWFuYWdlLXJlYWxtIiwidmlldy1ldmVudHMiLCJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwibWFuYWdlLWF1dGhvcml6YXRpb24iLCJtYW5hZ2UtY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyJdfSwiY29mZmVlLWV4cHJlc3MtYWRtaW4tYXBpLWNsaWVudCI6eyJyb2xlcyI6WyJtYW5hZ2VfcmVhbG0iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBlbWFpbCIsInNpZCI6IjY3ZTRhYTNjLTA5OGYtNDU4My1hZjllLWEzMGY2ZDRiNDY0YyIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiZmlzbmlrIHplam51bGxhaHUiLCJncm91cHMiOlsiZnVsbF9hY2Nlc3MiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwiZGVmYXVsdC1yb2xlcy1wdWJsaWMiLCJ1c2VyX2RhdGEiXSwicHJlZmVycmVkX3VzZXJuYW1lIjoiZmlzbmlreiIsImdpdmVuX25hbWUiOiJmaXNuaWsiLCJjdXN0b21lcl9pZCI6IjA0NWNmMTllLTM0YjktNGQxZS1hNTY2LTkyMTg3NDEyOWZmMCIsImZhbWlseV9uYW1lIjoiemVqbnVsbGFodSIsImVtYWlsIjoiZmlzbmlrekBnbWFpbC5jb20ifQ.fb_fislsJqQfDIh7NVynhArxyVYcmwNvQkf7xUG81eXkHKEXL4RUjx6UDtip2k61KjXGF2Kk-cIzuIfFCf9EYgCYVFR0SdLXoVrLqpptJWASwE6-UwjTK_PT8ukpcRfTdrxfG2ve3y-0ol8SjulQCWyMXKgy1Qe7QT42a_HoF7611psYJ8DjScfhnKZBnlEccuGBymqHWUtBOF0JLxxxjpWVSzq-4mbasPO0x5hk6gSy3DsmWDu2mVQlv8AvfIb4al6O3w35PRl2GwQak0Km_GbtrcHp4n64cRCJLG-5CFuA1I7XGRDtZjkyLwLEtgXe8o0YcT6t9C6xTbjiGqa9cw", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJmVTJGZzM4ZURQUUVNZjh2MFBRb3M5T3BYLVpPOXpxdzNVcG1xaGdwQVM4In0.eyJleHAiOjE2MzMyNzY0ODMsImlhdCI6MTYzMzI3NjE4MywianRpIjoiZTI5MWNlZWMtZWQwYy00YTc1LTlkMTgtZDY2Y2IxNTc4MzA0IiwiaXNzIjoiaHR0cDovL2tleWNsb2FrOjgwODAvYXV0aC9yZWFsbXMvcHVibGljIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjlmZTA2YTQxLWJmNDYtNGYwMi04YjkwLWY5MTM3ZWJjMWJiZCIsInR5cCI6IkJlYXJlciIsImF6cCI6ImNvZmZlZS1leHByZXNzLWFkbWluLWFwaS1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiZGNlMGI0NTktMjYzOS00ZTkyLWFhMDYtYmNjNTA5NjY0NDQzIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwidXNlcl9kYXRhIiwiZGVmYXVsdC1yb2xlcy1wdWJsaWMiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiJkY2UwYjQ1OS0yNjM5LTRlOTItYWEwNi1iY2M1MDk2NjQ0NDMiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6ImNuaWt1MiBjbmlrdTIiLCJncm91cHMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsInVzZXJfZGF0YSIsImRlZmF1bHQtcm9sZXMtcHVibGljIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImNuaWt1MiIsImdpdmVuX25hbWUiOiJjbmlrdTIiLCJjdXN0b21lcl9pZCI6IjQ5NmU4NWQ4LTA2YTctNGE0MC04Y2Y0LTkzNjQyZDBhNzFiOCIsImZhbWlseV9uYW1lIjoiY25pa3UyIn0.fLl1ArW2O_GSupcYtN4RCLgWImNOpQGAsYj9Fe3ESoLBYLWj1QK-E3KXEM-Gvf43K3_1-DJRR0iY8w0Miap2Ek34pTRh62acdnIRatE-rf4Bx3vHzeX67sz7Jy4awlXE9szI7ho_EJ78bluw2wn8BhWcopnsic-P5zLjv--9vVzJSOV0uxCAMIU4n_JWMKbGY0_ZTXNxMx3_zptIaOybPurAArK7lwqWvW352KLtbzybeUp-Ba0Sw5xIXhxc5w93LToyObkUzbI1FfrCQXjp6xYehOr7WMGyZixnODx9m_6G8qUN1WweEbs1sRbMj7a8NPxnX0UgQwqpfuL4WJS3uw",
//                "e90770f3-0522-47d3-a9be-c9ef9402326e",};
//
//        ExecutorService executorService = Executors.newFixedThreadPool(12);
//        for (int i = 0; i < 1; i++) {
//            int randomNumber = new Random().nextInt(3);
//            var bankAccountId = bankAccountIds[0];
//            var accessToken = accessTokens[0];
//            Order order = newOrder(bankAccountId);
//
//            CompletableFuture.supplyAsync(() -> this.orderSystem.placeOrder(order, accessToken), executorService)
//                    .thenAccept(createdOrderLocation -> {
//                        String orderId = createdOrderLocation.toString().substring(createdOrderLocation.toString().lastIndexOf("/"));
//                        System.out.println("OrderID: = " + orderId);
//                    });
////            assertNotNull(createdOrderLocation);
////            URI createdOrderLocation = this.orderSystem.placeOrder(order);
//
////            JsonObject responseOrder = this.orderSystem.getOrder(orderId);
////            assertNotNull(responseOrder);
////            System.out.println(responseOrder);
////            assertEquals("PLACED", responseOrder.getString("order-state"));
////            System.out.println(responseOrder);
//        }
//    }
//
//    @Test
//    void placeOrderFail() {
//        Order order = newOrder("70d273a8");
//        order.getItems().get(0).setMenuItemId(-99);
//
//        this.orderSystem.placeInvalidOrder(order);
//    }

    static Order newOrder(String bankAccountId) {
        List<OrderItem> items = Stream.of(
                new OrderItem(new Random().nextInt(55), (short) new Random().nextInt(5)),
                new OrderItem(new Random().nextInt(55), (short) new Random().nextInt(5)),
                new OrderItem(new Random().nextInt(55), (short) new Random().nextInt(5)),
                new OrderItem(new Random().nextInt(55), (short) new Random().nextInt(5))
        ).collect(Collectors.toList());

        return new Order(bankAccountId, items);
    }
}