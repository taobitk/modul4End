package endmodule4.auction.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 5, max = 50, message = "Tên phải từ 5 đến 50 ký tự")
    private String name;

    @NotNull(message = "Giá không được để trống") // Thêm @NotNull
    @Min(value = 100000, message = "Giá phải lớn hơn 100,000")
    private Double price;

    @NotBlank(message = "Tình trạng không được để trống") // Thêm @NotBlank
    private String status;

    @NotNull(message = "Loại sản phẩm không được để trống")
    @ManyToOne
    @JoinColumn(name = "id_loai_sp", referencedColumnName = "cid")
    private Category category;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}