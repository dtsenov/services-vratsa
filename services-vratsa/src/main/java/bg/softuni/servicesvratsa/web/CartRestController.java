package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.service.CartService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

   @PostMapping(value = "/products/all/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addToCart(@PathVariable("productId") Long productId,
                                            @AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody AddToCartDTO addToCartDTO,
                                            HttpServletRequest request) {

       if (request.getMethod().equals(HttpMethod.POST.toString())) {
           request.setAttribute(CsrfToken.class.getName(), null);
       }

            String username = userDetails.getUsername();

            cartService.addToCart(username, addToCartDTO);

        return ResponseEntity.ok("Добавено в количката");
    }

    @GetMapping("/cart")
    @ResponseBody
    public ResponseEntity<List<CartViewModel>> getCart() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<CartViewModel> cartViewModels = cartService.getAllProductsByUser(authentication.getName());
        return ResponseEntity.ok(cartViewModels);
    }

    @DeleteMapping("/cart/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {

        cartService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }
}


