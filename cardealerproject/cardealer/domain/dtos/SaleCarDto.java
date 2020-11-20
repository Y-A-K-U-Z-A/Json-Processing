package cardealerproject.cardealer.domain.dtos;

public class SaleCarDto {
    private CarSeedDto car;
    private String customerName;
    private double Discount;
    private double price;
    private double priceWithDiscount;

    public SaleCarDto(){}

    public SaleCarDto(CarSeedDto car, String customerName, double discount, double price, double priceWithDiscount) {
        this.car = car;
        this.customerName = customerName;
        this.Discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarSeedDto getCar() {
        return car;
    }

    public void setCar(CarSeedDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public CarSeedDto getCars() {
        return car;
    }

    public void setCars(CarSeedDto car) {
        this.car = car;
    }
}
