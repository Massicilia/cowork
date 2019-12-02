package esgi.use_case;

public interface UserRepository {


    esgi.common.dto.UserFullDto getUser(java.util.UUID uuid_user);
    java.util.List<esgi.common.dto.UserFullDto> getUsers();
    java.util.UUID getUuidUserByNameAndSurname(String name, String surname);
    String getUserAuth(String identifiant, String password);
    boolean insertUser(esgi.common.dto.UserFullDto userFullDto);
    boolean isEmployee(java.util.UUID uuid);
    void updateUser(int subscrip, java.util.UUID uuidUser);
    esgi.common.dto.UserFullDto getUserUserByUsernameAndPassword(String identifiant, String password);
}
