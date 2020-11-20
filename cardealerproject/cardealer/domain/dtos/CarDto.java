package cardealerproject.cardealer.domain.dtos;

public class CarDto {
    private long Id;
    private String Make;
    private String Model;
    private long TravelledDistance;

    public CarDto(){}

    public CarDto(long id, String make, String model, long travelledDistance) {
        Id = id;
        Make = make;
        Model = model;
        TravelledDistance = travelledDistance;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        TravelledDistance = travelledDistance;
    }
}
