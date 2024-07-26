package BUOI2.LAB02.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


public class CartItem {
        private Product product;
        private int quantity;
        // Constructors
        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
