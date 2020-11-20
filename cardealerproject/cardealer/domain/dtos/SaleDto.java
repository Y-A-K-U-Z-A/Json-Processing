package cardealerproject.cardealer.domain.dtos;

public class SaleDto {
    private long id;
    private CarDto car;

    public SaleDto(){}

    public SaleDto(long id, CarDto car) {
        this.id = id;
        this.car = car;
    }

    public long getFullName() {
        return id;
    }

    public void setFullName(long id) {
        this.id = id;
    }

    public CarDto getBoughtCars() {
        return car;
    }

    public void setBoughtCars(CarDto car) {
        this.car = car;
    }
}
