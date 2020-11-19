package jsonprocessing.demojsonprocessing.domain.dtos.ex4;

import jsonprocessing.demojsonprocessing.domain.dtos.ex4.SoldProductsDto;

import java.util.List;

public class UserDto {
    private String firstName;
    private String lastName;
    private int age;
    private List<SoldProductsDto> soldProducts;

    public UserDto(){}

    public UserDto(String firstName, String lastName, int age, List<SoldProductsDto> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<SoldProductsDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductsDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
