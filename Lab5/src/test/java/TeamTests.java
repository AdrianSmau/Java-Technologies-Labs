import com.example.lab5.entities.Team;
import com.example.lab5.repositories.RepositoryLayer;
import com.example.lab5.service.ServiceLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class TeamTests {
    public static final String MOCK = "mock";
    private final ServiceLayer serviceLayer = new ServiceLayer();
    private final RepositoryLayer repositoryLayer = mock(RepositoryLayer.class);

    @BeforeEach
    public void init() {
        serviceLayer.setRepositoryLayer(repositoryLayer);
    }

    @Test
    @DisplayName("Tests inserting a Team")
    void create() {
        when(repositoryLayer.fetchTeamsFromDB()).thenReturn(List.of(mock(Team.class)));
        Assertions.assertEquals(1, serviceLayer.getTeams().size());
    }

    @Test
    @DisplayName("Tests deleting a Team")
    void delete() {
        doThrow(CustomTestException.class).when(repositoryLayer).deleteFromDB(MOCK);
        Assertions.assertThrows(CustomTestException.class, () -> serviceLayer.deleteTeamAndCity(MOCK));
    }
}
