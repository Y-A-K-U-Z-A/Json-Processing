package cardealerproject.cardealer.domain.dtos;

public class SupplierSeedDto {
    private String name;
    private boolean isImporter;

    public SupplierSeedDto(){}

    public SupplierSeedDto(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
