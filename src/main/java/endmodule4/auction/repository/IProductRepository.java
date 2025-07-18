package endmodule4.auction.repository;

import endmodule4.auction.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p FROM Product p " +
            "WHERE p.name LIKE %:name% " +
            "AND p.price >= :price " +
            "AND p.category.name LIKE %:categoryName%",
            countQuery = "SELECT COUNT(p) FROM Product p " +
                    "WHERE p.name LIKE %:name% " +
                    "AND p.price >= :price " +
                    "AND p.category.name LIKE %:categoryName%")
    Page<Product> search(
            @Param("name") String name,
            @Param("price") Double price,
            @Param("categoryName") String categoryName,
            Pageable pageable
    );
}