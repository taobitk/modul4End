package endmodule4.auction.service;

import endmodule4.auction.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    void save(Product product);

    Product findById(Integer id);

    void remove(Integer id);

    Page<Product> search(String name, Double price, String categoryName, Pageable pageable);
}