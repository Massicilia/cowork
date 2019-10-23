package esgi.infra.mysql;

import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class UserRepositoryImpl implements esgi.use_case.UserRepository {
    public java.sql.Statement statement = null;
    java.sql.Connection connection;

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public esgi.common.dto.UserFullDto getUser(java.util.UUID uuid_user) {
        mysqlConnection();

        String name=null;
        String surname=null;
        String mail=null;
        java.util.Date dateEndSubscription = null;
        int subscription=0;



        String getUser = "SELECT u.name, u.surname, u.mail, u.dateEndSubscription, u.subscription " +
		        "FROM user u " +
		        "WHERE c.UUID = " + "'" + uuid_user.toString() + "' ";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getUser);
            if (resultset.next()) {
                surname = resultset.getString("surname");
                name = resultset.getString("name");
                mail = resultset.getString("mail");
                dateEndSubscription = resultset.getDate ("dateEndSubscription");
                subscription = resultset.getInt("subscription");
            } else {
                throw new esgi.common.exceptions.UserNotFoundException ();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        esgi.common.dto.UserFullDto userFullDto = new esgi.common.dto.UserFullDto (uuid_user, surname, name, mail, dateEndSubscription, subscription);

        DbConnect.closeConnection(connection);
        return userFullDto;
    }

    @Override
    public java.util.List<esgi.common.dto.UserFullDto> getUsers() {
        mysqlConnection();
        java.util.List<esgi.common.dto.UserFullDto> userFullDtos = new java.util.ArrayList<>();
        esgi.common.dto.UserFullDto userFullDto;
        String getUsers = "SELECT user.UUID, user.name, user.surname, user.mail, user.dateEndSubscription "+
                "FROM user " +
                "WHERE user.subscription = " + 1;
        try {
            java.sql.ResultSet resultset = statement.executeQuery(getUsers);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String name = resultset.getString("name");
                String surname = resultset.getString("surname");
                String mail = resultset.getString("mail");
                java.util.Date dateEndSubscription = resultset.getDate ("dateEndSubscription");
                userFullDto = new esgi.common.dto.UserFullDto (UUID.fromString(uuidString), name, surname, mail, dateEndSubscription, 1);
                userFullDtos.add(userFullDto);
                if (resultset == null) {
                    throw new esgi.common.exceptions.AnyUserFoundException ();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return userFullDtos;
    }

    @Override
    public java.util.UUID getUuidUserByNameAndSurname(String name) {
        mysqlConnection();
        String uuidString;

        java.util.UUID uuidUser = null;



        String getUserByNameAndSurname = "SELECT UUID"+
                "FROM user" +
                "WHERE CONCAT(name , surname) = " + "'" + name + "'";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getUserByNameAndSurname);
            if (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuidUser = java.util.UUID.fromString(uuidString);
            } else {
                throw new esgi.common.exceptions.UserNotFoundException ();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return uuidUser;
    }


    public esgi.common.dto.UserFullDto generateUUID(esgi.common.dto.UserFullDto user) {
        boolean uuidExist = true;
        UUID uuidUser = UUID.randomUUID();
        while (uuidExist) {
            uuidUser = UUID.randomUUID();
            List<esgi.common.dto.UserFullDto> userFullDtos = this.getUsers();
            uuidExist = userFullDtos.stream()
                    .map(esgi.common.dto.UserFullDto::getUuidUser)
                    .anyMatch(uuidUser::equals);
        }
        user.setUuidUser(uuidUser);
        return user;
    }


    public boolean insertUser(esgi.common.dto.UserFullDto userFullDto) {
        mysqlConnection();
        boolean ok = false;
        String uuidUser = userFullDto.getUuidUser().toString();
        String surname = userFullDto.getSurname ();
        String name = userFullDto.getName ();
        String mail = userFullDto.getMail();
        java.util.Date dateEndSubscription = userFullDto.getDateEndSubscription ();
        int subscription = userFullDto.isSubscription ();
        int newIdUser = 0;
        ResultSet generatedKeys = null;
        String insertUser = "INSERT INTO user" +
		        "(UUID, name, surname, mail, dateEndSubscription, subscription)" +
		        "VALUES (" + "'" + uuidUser + "', '" + name + "', '" + surname + "', '" + mail + "', '" + "', '" + dateEndSubscription + "', '" + subscription + "')";


	    try {
            statement.execute(insertUser, statement.RETURN_GENERATED_KEYS);
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newIdUser = generatedKeys.getInt(1);
                ok = true;
            }
        } catch (SQLException e) {
            ok = false;
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return ok;
    }


}
