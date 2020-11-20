package cardealerproject.cardealer.domain.dtos;

public class SupplierDto {
    private long Id;
    private String Name;
    private int partsCount;

    public SupplierDto(){}

    public SupplierDto(long id, String name, int partsCount) {
        Id = id;
        Name = name;
        this.partsCount = partsCount;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
