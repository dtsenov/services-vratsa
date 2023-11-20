package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.service.CartService;
import com.cloudinary.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/products/all")
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable("productId") Long productId,
                                            @AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody AddToCartDTO addToCartDTO) {

        String username = userDetails.getUsername();

        cartService.addToCart(username, addToCartDTO);

        return ResponseEntity.ok("Добавено в количката");
    }
}


