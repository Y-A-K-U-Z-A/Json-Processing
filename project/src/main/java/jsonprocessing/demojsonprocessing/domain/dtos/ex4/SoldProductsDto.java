package jsonprocessing.demojsonprocessing.domain.dtos.ex4;

import jsonprocessing.demojsonprocessing.domain.dtos.ex4.ProductDto;

import java.util.List;

public class SoldProductsDto {
    private int count;
    private List<ProductDto> products;

    public SoldProductsDto(int count, List<ProductDto> products) {
        this.count = count;
        this.products = products;
    }
    public SoldProductsDto(){}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
