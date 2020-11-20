package cardealerproject.cardealer.domain.dtos;

public class CarPartsDto {
    private long Id;
    private String Make;
    private String Model;
    private long TravelledDistance;
    private PartDto[] parts;

    public CarPartsDto(){}

    public CarPartsDto(long id, String make, String model, long travelledDistance, PartDto[] parts) {
        Id = id;
        Make = make;
        Model = model;
        TravelledDistance = travelledDistance;
        this.parts = parts;
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

    public PartDto[] getPartDtos() {
        return parts;
    }

    public void setPartDtos(PartDto[] parts) {
        this.parts = parts;
    }
}
