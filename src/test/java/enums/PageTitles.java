package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PageTitles {
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT_YOUR_INFORMATION("Checkout: Your Information"),
    CHECKOUT_OVERVIEW("Checkout: Overview"),
    CHECKOUT_COMPLETE("Checkout: Complete!");

    private final String pageTitle;
}
