package endmodule4.auction.service.impl;

import endmodule4.auction.model.Category;
import endmodule4.auction.repository.ICategoryRepository;
import endmodule4.auction.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}