package esgi.infra.mysql;

import esgi.common.dto.UserFullDto;
import esgi.use_case.UserRepository;
import esgi.common.exceptions.UserNotFoundException;
import esgi.common.exceptions.AnyUserFoundException;
import esgi.infra.DateFormat;
import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.time.LocalDate;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.util.ArrayList;

public class UserRepositoryImpl implements UserRepository {
    public Statement statement = null;
    Connection connection;
    DateFormat dateFormat = new DateFormat ();
	Logger logger = LoggerFactory.getLogger(esgi.infra.mysql.UserRepositoryImpl.class);

    void mysqlConnection() {
        connection = DbConnect.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserFullDto getUser(UUID uuid_user) {
        mysqlConnection();

        String uuidString;
        UUID uuid = null;
        String name=null;
        String surname=null;
        String mail=null;
        java.time.LocalDate dateEndSubscription = null;
        int subscription=0;
        String identifiant = null;
        String password = null;
        String type = null;
        String uuidSpaceString;
        UUID uuidSpace = null;


        String getUser = "SELECT UUID, name, surname, mail, dayEndSubscription, monthEndSubscription, yearEndSubscription, subscription, identifiant, password, type, space FROM user WHERE UUID = '" + uuid_user.toString() + "' ";


        try {
            java.sql.ResultSet resultset = statement.executeQuery(getUser);

            if (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuid = UUID.fromString(uuidString);
                uuidSpaceString = resultset.getString("space");
                uuidSpace = UUID.fromString(uuidString);
                surname = resultset.getString("surname");
                name = resultset.getString("name");
                mail = resultset.getString("mail");
                identifiant = resultset.getString("identifiant");
                password = resultset.getString("password");
                type = resultset.getString("type");

                if(resultset.getInt ("yearEndSubscription") != 0 || resultset.getInt ("monthEndSubscription")!= 0 || resultset.getInt ("dayEndSubscription") != 0){

                    String dateEndSubscriptionString = resultset.getInt ("yearEndSubscription") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthEndSubscription")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayEndSubscription"));
                    dateEndSubscription = LocalDate.parse(dateEndSubscriptionString);
                }

                subscription = resultset.getInt("subscription");
            } else {
                throw new UserNotFoundException ();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UserFullDto userFullDto = new UserFullDto (uuid, surname, name, mail, dateEndSubscription, subscription, identifiant, password, type, uuidSpace);

        DbConnect.closeConnection(connection);
        return userFullDto;
    }

    @Override
    public List<UserFullDto> getUsers() {
        mysqlConnection();
        List<UserFullDto> userFullDtos = new ArrayList<> ();
        UserFullDto userFullDto;
        LocalDate dateEndSubscription= null;
        logger.debug ("USERREPOIMPL START");
        String getUsers = "SELECT UUID, name, surname, mail, dayEndSubscription, monthEndSubscription, yearEndSubscription, subscription, identifiant, password, type, space " +
                "FROM user " +
                "WHERE subscription = " + 1;

        logger.debug ("USERREPOIMPL getUsers " + getUsers);
        try {
            java.sql.ResultSet resultset = statement.executeQuery(getUsers);
            while (resultset.next()) {
                String uuidString = resultset.getString("UUID");
                String uuidSpaceString = resultset.getString("space");
                String name = resultset.getString("name");
                String surname = resultset.getString("surname");
                String mail = resultset.getString("mail");
                String identifiant = resultset.getString("identifiant");
	            String password = resultset.getString("password");
	            String type = resultset.getString("type");
	            logger.debug ("GETDATAS");
                if(resultset.getInt ("yearEndSubscription") != 0 || resultset.getInt ("monthEndSubscription")!= 0 || resultset.getInt ("dayEndSubscription") != 0){

                    String dateEndSubscriptionString = resultset.getInt ("yearEndSubscription") + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("monthEndSubscription")) + "-" + dateFormat.getFormatTwoChar (resultset.getInt ("dayEndSubscription"));
                    dateEndSubscription = LocalDate.parse(dateEndSubscriptionString);
                }
                logger.debug ("GETDATAS DATE");
	            userFullDto = new UserFullDto (UUID.fromString(uuidString), name, surname, mail, dateEndSubscription, 1, identifiant, password, type, UUID.fromString(uuidSpaceString));
                userFullDtos.add(userFullDto);
                if (resultset == null) {
                    throw new AnyUserFoundException ();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return userFullDtos;
    }

    @Override
    public UUID getUuidUserByNameAndSurname(String name, String surname) {
        mysqlConnection();

        String uuidString;

        UUID uuidUser = null;

        String getUserByNameAndSurname = "SELECT UUID FROM user WHERE name  = " + "'" + name + "' AND surname = " + "'" + surname + "'";
        logger.debug ("getUserByNameAndSurname = " + getUserByNameAndSurname);
        try {
            ResultSet resultset = statement.executeQuery(getUserByNameAndSurname);
            if (resultset.next()) {
                logger.debug ("resultset next == true");
                uuidString = resultset.getString("UUID");
                uuidUser = UUID.fromString(uuidString);
                logger.debug ("uuid USER = " + uuidString);
            } else {
                throw new UserNotFoundException ();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return uuidUser;
    }

    @Override
    public String getUserAuth(String identifiant, String password){
	    mysqlConnection();

		String type = null;

	    String getUserAuth = "SELECT type FROM user WHERE identifiant  = " + "'" + identifiant + "' AND password = " + "'" + password + "'";

	    try {
		    ResultSet resultset = statement.executeQuery(getUserAuth);
		    if (resultset.next()) {
			    type = resultset.getString("type");
		    } else {
			    throw new UserNotFoundException ();
		    }
	    } catch (SQLException e) {
		    e.printStackTrace();
	    }

	    DbConnect.closeConnection(connection);
	    return type;
    }

    public UserFullDto generateUUID(UserFullDto user) {
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

    @Override
    public boolean insertUser(UserFullDto userFullDto) {
        mysqlConnection();
        boolean ok = false;
        String uuidUser = userFullDto.getUuidUser().toString();
        String surname = userFullDto.getSurname ();
        String name = userFullDto.getName ();
        String mail = userFullDto.getMail();
        int subscription = userFullDto.getSubscription ();
	    String identifiant = userFullDto.getIdentifiant();
	    String password = userFullDto.getIdentifiant();
	    String type = userFullDto.getType ();
	    String formatDateEndSubscription = userFullDto.getDateEndSubscriptionString ();
	    UUID uuidSpace = userFullDto.getUuidSpace ();
	    String space = userFullDto.getUuidSpace ().toString();

        int newIdUser = 0;
        ResultSet generatedKeys = null;
        String insertUser = "INSERT INTO user" +
		        "(UUID, name, surname, mail, dayEndSubscription, monthEndSubscription, yearEndSubscription, subscription, identifiant, password, type, space)" +
		        "VALUES (" + "'" + uuidUser + "', '" +
                name + "', '" +
                surname + "', '" +
                mail + "', '" +
                Integer.parseInt(formatDateEndSubscription.substring (8, 10)) + "', '" +
                Integer.parseInt(formatDateEndSubscription.substring (5, 7)) + "', '" +
                Integer.parseInt(formatDateEndSubscription.substring (0, 4)) + "', '" +
                + subscription + "', '" + identifiant + "', '" + password + "', '" + type + "', '" + space +"')";

		logger.debug ("insertUser " + insertUser);
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

    @Override
    public boolean isEmployee(java.util.UUID uuid){

        mysqlConnection();
        boolean ok = false;
        String type = null;

        String getType = "SELECT type FROM user WHERE UUID = " + "'" + uuid.toString() + "' ";

        logger.debug ("UUID " + uuid);
        logger.debug ("TYPE USER " + getType);
        try {
            java.sql.ResultSet resultset = statement.executeQuery(getType);

            if (resultset.next()) {
                logger.debug ("IN RESULTSET NEXT ");
                type = resultset.getString("type");
                logger.debug ("TYPE " + type);
                if(type.equals ("employee")){
                    ok = true;
                    logger.debug ("OK " + ok);
                }
            } else {
                throw new UserNotFoundException ();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return ok;
    }

    @Override
    public void updateUser(int subscrip, UUID uuidUser) {
        mysqlConnection();

        String updateSubscription = "UPDATE user SET subscription ='" + (subscrip == 1 ? 0 : 1) + "' WHERE UUID = '" + uuidUser.toString () + "'";

        try {
            statement.executeUpdate (updateSubscription);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
