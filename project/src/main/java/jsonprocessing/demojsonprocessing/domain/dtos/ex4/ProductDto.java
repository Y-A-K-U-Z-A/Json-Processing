package jsonprocessing.demojsonprocessing.domain.dtos.ex4;

public class ProductDto {
    private String name;
    private double price;

    public ProductDto(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public ProductDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
