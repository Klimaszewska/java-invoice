package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Invoice {
    private final Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetValue() {
        BigDecimal result = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal price = product.getPrice();
            result = result.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        return result;
    }

    public BigDecimal getTax() {
        return getGrossValue().subtract(getNetValue());
    }

    public BigDecimal getGrossValue() {
        BigDecimal result = BigDecimal.ZERO;
        for (Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal price = product.getPriceWithTax();
            result = result.add(price.multiply(BigDecimal.valueOf(quantity)));
        }
        return result;
    }
}
