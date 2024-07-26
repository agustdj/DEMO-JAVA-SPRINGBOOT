package   BUOI2.LAB02.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String phone;
    private String address;
    private String note;
    private String payment;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}