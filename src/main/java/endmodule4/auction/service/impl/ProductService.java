package endmodule4.auction.service.impl;

import endmodule4.auction.model.Product;
import endmodule4.auction.repository.IProductRepository;
import endmodule4.auction.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> search(String name, Double price, String categoryName, Pageable pageable) {
        if (price == null) {
            price = 0.0;
        }
        return productRepository.search(name, price, categoryName, pageable);
    }
}