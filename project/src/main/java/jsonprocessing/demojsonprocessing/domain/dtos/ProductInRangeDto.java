package jsonprocessing.demojsonprocessing.domain.dtos;


public class ProductInRangeDto {

    private String name;
    private double price;
    private String seller;

    public ProductInRangeDto(String name, double price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }
    public ProductInRangeDto(){}

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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
