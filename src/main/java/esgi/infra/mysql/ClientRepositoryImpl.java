package esgi.infra.mysql;

import esgi.use_case.ClientRepository;
import esgi.common.dto.ClientFullDto;
import esgi.common.exceptions.ClientNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.UUID;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {
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
    public ClientFullDto getClient(java.util.UUID uuid_client) {
        mysqlConnection();

        String nameClient=null;
        String surnameClient=null;
        String mail=null;
        boolean subscription=false;



        String getClient = "SELECT c.name, c.surname, c.mail, c.subscription " +
		        "FROM client c " +
		        "WHERE c.UUID = " + "'" + uuid_client.toString() + "' ";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getClient);
            if (resultset.next()) {
                surnameClient = resultset.getString("surname");
                nameClient = resultset.getString("name");
                mail = resultset.getString("mail");
                subscription = resultset.getBoolean("subscription");
            } else {
                throw new ClientNotFoundException();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        ClientFullDto clientFullDto = new ClientFullDto(uuid_client, surnameClient, nameClient, mail, subscription);

        DbConnect.closeConnection(connection);
        return clientFullDto;
    }

    @Override
    public java.util.List<ClientFullDto> getClients() {
        mysqlConnection();
        java.util.List<ClientFullDto> clientFullDtos = new java.util.ArrayList<>();
        ClientFullDto clientFullDto;
        String getClients = "SELECT client.UUID, client.name, client.surname, client.mail "+
                "FROM client " +
                "WHERE client.subscription = " + 1;
        try {
            java.sql.ResultSet resultset = statement.executeQuery(getClients);
            while (resultset.next()) {

                String uuidString = resultset.getString("UUID");
                String name = resultset.getString("name");
                String surname = resultset.getString("surname");
                String mail = resultset.getString("mail");
                clientFullDto = new ClientFullDto(UUID.fromString(uuidString), name, surname, mail, true);
                clientFullDtos.add(clientFullDto);
                if (resultset == null) {
                    throw new ClientNotFoundException();
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return clientFullDtos;
    }

    @Override
    public java.util.UUID getUuidClientByNameAndSurname(String nameClient, String surnameClient) {
        mysqlConnection();
        String uuidString;

        java.util.UUID uuidClient = null;



        String getClientByNameAndSurname = "SELECT client.UUID"+
                "FROM client" +
                "WHERE client.name = " + "'" + nameClient + "' and client.surname = '" + surnameClient +
                "'";

        try {
            java.sql.ResultSet resultset = statement.executeQuery(getClientByNameAndSurname);
            if (resultset.next()) {
                uuidString = resultset.getString("UUID");
                uuidClient = java.util.UUID.fromString(uuidString);
            } else {
                throw new ClientNotFoundException();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return uuidClient;
    }


    public ClientFullDto generateUUID(ClientFullDto client) {
        boolean uuidExist = true;
        UUID uuidClient = UUID.randomUUID();
        while (uuidExist) {
            uuidClient = UUID.randomUUID();
            List<ClientFullDto> clientFullDtos = this.getClients();
            uuidExist = clientFullDtos.stream()
                    .map(ClientFullDto::getUuidClient)
                    .anyMatch(uuidClient::equals);
        }
        client.setUuidClient(uuidClient);
        return client;
    }


    public boolean insertClient(ClientFullDto clientFullDto) {
        mysqlConnection();
        boolean ok = false;
        String uuidClient = clientFullDto.getUuidClient().toString();
        String surname = clientFullDto.getSurnameClient ();
        String name = clientFullDto.getNameClient ();
        String mail = clientFullDto.getMail();
        int subscription = clientFullDto.getSubscription() ? 1 : 0;
        int newIdClient = 0;
        ResultSet generatedKeys = null;
        String insertClient = "INSERT INTO client" +
		        "(UUID, name, surname, subscription, mail)" +
		        "VALUES (" + "'" + uuidClient + "', '" + name + "', '" + surname + "', '" + subscription + "', '" + mail + "')";




	    try {
            statement.execute(insertClient, statement.RETURN_GENERATED_KEYS);
            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newIdClient = generatedKeys.getInt(1);
                ok = true;
            }
        } catch (SQLException e) {
            ok = false;
            e.printStackTrace();
        }

        DbConnect.closeConnection(connection);
        return ok;
    }
/*     @Override
    public CandidateDto getCandidateForSchedule(UUID uuidCandidate) {
        mysqlConnection();
        String uuidString;
        UUID uuid = null;
        int experience = 0;
        SkillsDto skillsDto;
        String getCandidate = "SELECT p.uuidPerson, p.experience " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "'";
        try {
            ResultSet resultset = statement.executeQuery(getCandidate);
            if (resultset.next()) {
                uuidString = resultset.getString("uuidPerson");
                uuid = UUID.fromString(uuidString);
                experience = Integer.parseInt(resultset.getString("experience"));
            } else {
                throw new CandidateNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CandidateDto candidateDto = new CandidateDto(uuid, null, experience);

        String getSkillsCandidate = "SELECT s.nameSkill, spc.isKeySkill " +
                "FROM Person p " +
                "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE p.uuidPerson = " + "'" + uuidCandidate.toString() + "'" +
                "AND pr.isCandidate = " + 1;
        try {
            ResultSet resultsetSkills = statement.executeQuery(getSkillsCandidate);
            List<String> keySkills = new ArrayList<>();
            List<String> skills = new ArrayList<>();
            skillsDto = new SkillsDto();
            while (resultsetSkills.next()) {
                String skill = resultsetSkills.getString("nameSkill");
                if (resultsetSkills.getInt("isKeySkill") == 0) {
                    skills.add(skill);
                } else {
                    keySkills.add(skill);
                }
            }
            skillsDto.setKeySkills(keySkills);
            skillsDto.setOtherSkills(skills);
            candidateDto.setSkills(skillsDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnect.closeConnection(connection);
        return candidateDto;
    }

    public boolean deleteCandidate(UUID uuid) {
        mysqlConnection();
        boolean work;

        int idInterview = 0;
        String uuidStringCandidate = "";
        UUID uuidCandidate = null;
        String uuidStringRecruiter = "";
        UUID uuidRecruiter = null;
        int idAvailabilityMonth = 0;
        int idAvailabilityDay = 0;
        int idAvailabilityHour = 0;

        String deleteCandidate = "Delete p " +
                "FROM Person p " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE p.uuidPerson = " + "'" + uuid.toString() + "' " +
                "AND pr.isCandidate = " + 1;
        try {
            statement.execute(deleteCandidate);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }

        // Get interview informations before reinsert recruiter availability
        String selectInterview = "SELECT i.idInterview, i.uuidRecruiter, i.uuidCandidate, " +
                "i.idAvailabilityMonth, i.idAvailabilityDay, i.idAvailabilityHour " +
                "FROM Interview i " +
                "WHERE i.uuidCandidate = " + "'" + uuid.toString() + "'";

        ResultSet resultsetInterview = null;
        try {
            resultsetInterview = statement.executeQuery(selectInterview);
            while (resultsetInterview.next()) {
                idInterview = resultsetInterview.getInt("idInterview");
                uuidStringCandidate = resultsetInterview.getString("uuidCandidate");
                uuidStringRecruiter = resultsetInterview.getString("uuidRecruiter");
                uuidCandidate = UUID.fromString(uuidStringCandidate);
                uuidRecruiter = UUID.fromString(uuidStringRecruiter);
                idAvailabilityMonth = resultsetInterview.getInt("idAvailabilityMonth");
                idAvailabilityDay = resultsetInterview.getInt("idAvailabilityDay");
                idAvailabilityHour = resultsetInterview.getInt("idAvailabilityHour");
                work = true;
            }
            if (resultsetInterview == null) {
                throw new AnyInterviewFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (idInterview == 0) {
            return work = true;
        }

        DateMapper dateMapper = new DateMapper();
        InfraDateForm infraDateFormToDelete = new InfraDateForm(idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour);
        LocalDateTime dateTime = dateMapper.mapInfraDateFormToDateTime(infraDateFormToDelete);
        InterviewDeleterDto interviewDeleterDto = new InterviewDeleterDto(idInterview, dateTime, uuidRecruiter, uuidCandidate);

        InfraDateForm infraDateForm = dateMapper.mapDateTimeToInfraDateForm(interviewDeleterDto.getDateInterview());
        int hourAvailability = infraDateForm.getHour();
        int dayAvailability = infraDateForm.getDay();
        int monthAvailability = infraDateForm.getMonth();

        // Delete interview
        String deleteCandidateInterview = "Delete i " +
                "FROM Interview i " +
                "WHERE i.uuidCandidate = " + "'" + uuid.toString() + "'";

        try {
            statement.execute(deleteCandidateInterview);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }

        // Reinsert Recruiter availabilities
        String reInsertRecruiterAvailability = "INSERT INTO PersonAvailabilityConf" +
                "(uuidPerson, idAvailabilityMonth, idAvailabilityDay, idAvailabilityHour)" +
                "VALUES (" + "'" + interviewDeleterDto.getUuidRecruiter().toString() + "', " + monthAvailability + ", " + dayAvailability + ", " + hourAvailability + ")";

        try {
            statement.executeUpdate(reInsertRecruiterAvailability);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Get mail to notify recruiter's interview has benn canceled
        String mailRecruiter = "SELECT p.mail " +
                "FROM Person p " +
                "WHERE p.uuidPerson = " + "'" + interviewDeleterDto.getUuidRecruiter().toString() + "'";

        ResultSet mailRecruiterResult = null;
        try {
            mailRecruiterResult = statement.executeQuery(mailRecruiter);
            while (mailRecruiterResult.next()) {
                mailRecruiter = mailRecruiterResult.getString("mail");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("recruitorapp@gmail.com");
        mailMessage.setTo(mailRecruiter);
        mailMessage.setSubject("Interview schedule");
        mailMessage.setText("Your interview scheduled for " + interviewDeleterDto.getDateInterview() + " has been " +
                "canceled because candidate is out of process");
        MailConfig.getMailConfig().send(mailMessage);

        DbConnect.closeConnection(connection);
        return work;
    }


    public boolean updateCandidate(CandidateFullDto candidate) {
        if (candidate.getUuid() == null) {
            throw new CannotUpdateNullCandidateException();
        }
        mysqlConnection();
        boolean work;
        String firstNameCandidate = candidate.getFirstName();
        String lastName = candidate.getLastName();
        String mail = candidate.getMail();
        int experience = candidate.getExperience();
        String updateCandidate = "UPDATE Person " +
                "SET firstName = " + "'" + firstNameCandidate + "', " +
                "lastName = " + "'" + lastName + "', " +
                "mail = " + "'" + mail + "', " +
                "experience = " + "'" + experience + "' " +
                "WHERE uuidPerson = " + "'" + candidate.getUuid().toString() + "'";
        try {
            statement.execute(updateCandidate);
            work = true;
        } catch (SQLException e) {
            e.printStackTrace();
            work = false;
        }
        DbConnect.closeConnection(connection);
        return work;
    }

    public CandidateFullDto generateUUID(CandidateFullDto candidate) {
        boolean uuidExist = true;
        UUID uuidCandidate = UUID.randomUUID();
        while (uuidExist) {
            uuidCandidate = UUID.randomUUID();
            List<CandidateFullDto> candidateFullDtos = this.getCandidates();
            uuidExist = candidateFullDtos.stream()
                    .map(CandidateFullDto::getUuid)
                    .anyMatch(uuidCandidate::equals);
        }
        candidate.setUuid(uuidCandidate);
        return candidate;
    }

    public List<CandidateFullDto> getCandidatesLessInterview() {
        mysqlConnection();
        List<CandidateFullDto> candidateFullDtos = new ArrayList<>();
        CandidateFullDto candidateFullDto;
        String getCandidates = "SELECT p.uuidPerson, p.firstName, p.lastName, p.experience, p.mail, e.name FROM Person p " +
                "INNER JOIN Enterprise e ON p.id_enterprise = e.id_enterprise " +
                "INNER JOIN Profile pr ON p.idPerson = pr.idProfile " +
                "WHERE pr.isCandidate = " + 1 + " AND " +
                "p.uuidPerson NOT IN (SELECT uuidCandidate FROM Interview)";
        try {
            ResultSet resultset = statement.executeQuery(getCandidates);
            while (resultset.next()) {
                UUID uuid = UUID.fromString(resultset.getString("uuidPerson"));
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                int experience = Integer.parseInt(resultset.getString("experience"));
                String mail = resultset.getString("mail");
                String enterprise = resultset.getString("name");
                candidateFullDto = new CandidateFullDto(uuid, firstName, lastName, mail, experience, enterprise, null, null);
                candidateFullDtos.add(candidateFullDto);
                if (resultset == null) {
                    throw new AnyCandidateFoundException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (CandidateFullDto candidateFullDto1 : candidateFullDtos) {
            System.out.println(candidateFullDto1.getUuid().toString().getClass().getName());
            System.out.println(candidateFullDto1.getUuid());
            String getSkillsRecruiters = "SELECT s.idSkill, s.nameSkill, spc.isKeySkill " +
                    "FROM Person p " +
                    "INNER JOIN SkillPersonConf spc ON spc.idPerson = p.idPerson " +
                    "INNER JOIN Skill s ON s.idSkill = spc.idSkill " +
                    "WHERE p.uuidPerson = " + "'" + candidateFullDto1.getUuid() + "'";
            try {
                ResultSet resultsetSkills = statement.executeQuery(getSkillsRecruiters);
                List<SkillFullDto> keySkills = new ArrayList<>();
                List<SkillFullDto> skills = new ArrayList<>();
                while (resultsetSkills.next()) {
                    int idSkill = resultsetSkills.getInt("idSkill");
                    String nameSkill = resultsetSkills.getString("nameSkill");
                    SkillFullDto skill = new SkillFullDto(idSkill, nameSkill);
                    if (resultsetSkills.getInt("isKeySkill") == 0) {
                        skills.add(skill);
                    } else {
                        keySkills.add(skill);
                    }
                }
                candidateFullDto1.setKeySkills(keySkills);
                candidateFullDto1.setSkills(skills);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DbConnect.closeConnection(connection);
        return candidateFullDtos;
    }
     */
}
