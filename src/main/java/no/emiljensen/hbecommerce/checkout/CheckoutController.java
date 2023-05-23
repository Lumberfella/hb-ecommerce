package no.emiljensen.hbecommerce.checkout;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping(value = "/checkout", consumes = "application/json")
    public ResponseEntity<Object> checkout(@RequestBody List<String> ids) {
        int checkoutPrice = checkoutService.getCheckoutPrice(ids);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("price", checkoutPrice));
    }
}
