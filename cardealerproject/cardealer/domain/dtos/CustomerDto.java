package cardealerproject.cardealer.domain.dtos;

import cardealerproject.cardealer.domain.entities.Sale;

import java.util.Set;

public class CustomerDto {
    private long Id;
    private String Name;
    private String BirthDate;
    private boolean IsYoungDriver;
    private SaleDto[] Sales;

    public CustomerDto(){}

    public CustomerDto(long id, String name, String birthDate, boolean isYoungDriver, SaleDto[] Sales) {
        Id = id;
        Name = name;
        BirthDate = birthDate;
        IsYoungDriver = isYoungDriver;
        this.Sales = Sales;
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

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return IsYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        IsYoungDriver = youngDriver;
    }

    public SaleDto[] getSales() {
        return Sales;
    }

    public void setSales(SaleDto[] sales) {
        this.Sales = sales;
    }
}
