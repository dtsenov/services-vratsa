package bg.softuni.servicesvratsa.web;

import bg.softuni.servicesvratsa.model.binding.AddToCartDTO;
import bg.softuni.servicesvratsa.model.view.CartViewModel;
import bg.softuni.servicesvratsa.service.CartService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
public class CartRestController {

    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

   @RequestMapping(value = "/products/all/{productId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<List<CartViewModel>> getCart(Authentication authentication) {

        List<CartViewModel> cartViewModels = cartService.getAllProductsByUser(authentication.getName());
        return ResponseEntity.ok(cartViewModels);
    }
}


