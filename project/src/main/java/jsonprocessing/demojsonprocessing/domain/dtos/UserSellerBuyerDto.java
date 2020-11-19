package jsonprocessing.demojsonprocessing.domain.dtos;

import java.util.List;

public class UserSellerBuyerDto {
    private String firstName;
    private String lastName;
    private List<ProductsInRangeNamesDto> soldProducts;

    public UserSellerBuyerDto(){}

    public UserSellerBuyerDto(String firstName, String lastName, List<ProductsInRangeNamesDto> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductsInRangeNamesDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductsInRangeNamesDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
