package endmodule4.auction.service;

import endmodule4.auction.model.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
}