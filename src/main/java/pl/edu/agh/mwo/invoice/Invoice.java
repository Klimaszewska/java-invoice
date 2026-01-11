package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private final Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        while (quantity > 0) {
            this.products.add(product);
            quantity--;
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : this.products) {
            BigDecimal price = product.getPrice();
            subtotal = subtotal.add(price);
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (Product product : this.products) {
            BigDecimal tax = product.getPriceWithTax().subtract(product.getPrice());
            totalTax = totalTax.add(tax);
        }
        return totalTax;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : this.products) {
            BigDecimal price = product.getPriceWithTax();
            total = total.add(price);
        }
        return total;
    }
}
