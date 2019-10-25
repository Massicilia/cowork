package esgi.use_case;

public interface UserRepository {


    esgi.common.dto.UserFullDto getUser(java.util.UUID uuid_user);
    java.util.List<esgi.common.dto.UserFullDto> getUsers();
    java.util.UUID getUuidUserByNameAndSurname(String name, String surname);

}
