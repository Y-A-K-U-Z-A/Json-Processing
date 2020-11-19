package jsonprocessing.demojsonprocessing.domain.dtos.ex4;

import java.util.List;

public class UserCountDto {
    private int usersCount;
    private List<UserDto> users;

    public UserCountDto(){}

    public UserCountDto(int usersCount, List<UserDto> users) {
        this.usersCount = usersCount;
        this.users = users;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
