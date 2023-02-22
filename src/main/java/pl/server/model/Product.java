package pl.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", category=" + category +
                ", uservendor=" + uservendor +
                '}';
    }


    private String name;

    private BigDecimal price;

    @Size(min = 10, max = 500)
    private String description;

    private String fileName;

    @ManyToOne

    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    private User uservendor;





}
