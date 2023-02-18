

package pl.server.model;

        import com.fasterxml.jackson.annotation.JsonIgnore;
        import lombok.*;

        import javax.persistence.*;
        import javax.validation.constraints.Size;
        import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private BigDecimal price;

    @Size(min = 10, max = 500)
    private String description;

    private String fileName;

    @ManyToOne
    @JsonIgnore
    private Category category;


    private Integer Quantity;

    private String status;

}

