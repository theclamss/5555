package pl.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.server.model.Product;
import pl.server.model.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsByName(String name);
    Product findProductById(Long id);

    @Query("SELECT p FROM Product p INNER JOIN p.uservendor u WHERE u.id = :id")
    List<Product> findProductsByUserId(@Param("id") Long id);
    List<Product> findByUservendor_IdAndUservendorIsNotNull(Long id);


    List<Product> findProductByNameStartingWithIgnoreCase(String name);

    Product findProductByName(String name);

//    @Query("select * from product where product.category.name = ")
    List<Product> findByCategory_Name(String name);

}
