package com.yugabyte.app.yugastore.rest.clients;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="cart-microservice", url="${client.cart.baseUrl:http://localhost:8083}")
public interface ShoppingCartRestClient {

  @RequestMapping(value = "/cart-microservice/shoppingCart/addProduct", method = RequestMethod.POST)
  String addProductToCart(@RequestParam("userid") String userId,
    @RequestParam("sku") String sku);

  @RequestMapping("/cart-microservice/shoppingCart/productsInCart")
  Map<String, Integer> getProductsInCart(@RequestParam("userid") String userId);

  @RequestMapping("/cart-microservice/shoppingCart/removeProduct")
  String removeProductFromCart(@RequestParam("userid") String userId,
    @RequestParam("sku") String sku);

  @RequestMapping("/cart-microservice/shoppingCart/clearCart")
  String clearCart(@RequestParam("userid") String userId);
}
