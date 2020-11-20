package cardealerproject.cardealer.domain.dtos;

public class PartDto {
    private String Name;
    private double Price;

    public PartDto(){}

    public PartDto(String name, double price) {
        Name = name;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
