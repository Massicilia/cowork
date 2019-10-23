package esgi.use_case;

import esgi.common.dto.ClientDto;
import esgi.common.dto.ClientFullDto;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {

    ClientFullDto getClient(java.util.UUID uuid_client);
    java.util.UUID getUuidClientByNameAndSurname (String nameClient);
    java.util.List<ClientFullDto> getClients ();

/*   boolean setClientSubscriptionOn(UUID uuidClient);
    boolean setClientSubscriptionOff(UUID uuidClient);

    boolean deleteClient(UUID uuidClient);

    boolean insertClient(ClientDto clientDto); */
}
