package jsonprocessing.demojsonprocessing.domain.dtos;

public class CategorySeedDto {
    private String name;

    public CategorySeedDto(){}

    public CategorySeedDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
