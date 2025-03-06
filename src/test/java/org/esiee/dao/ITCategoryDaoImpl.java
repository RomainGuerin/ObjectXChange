package org.esiee.dao;

import org.esiee.model.Category;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ITCategoryDaoImpl {
    private CategoryDaoImpl categoryDao;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> databaseConnectionMock;

    @BeforeEach
    void setUp() throws SQLException {
        // Création des mocks pour Connection, PreparedStatement et ResultSet
        Connection mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Simuler prepareStatement pour renvoyer le PreparedStatement mocké
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock statique de DatabaseConnection.getConnection() pour renvoyer notre connexion mockée
        databaseConnectionMock = mockStatic(DatabaseConnection.class);
        databaseConnectionMock.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

        // Instanciation de l'implémentation du DAO
        categoryDao = new CategoryDaoImpl();
    }

    @AfterEach
    void tearDown() {
        // Fermeture du mock statique
        databaseConnectionMock.close();
    }

    @Test
    void testGetAllCategory() throws SQLException {
        // Configuration du mock ResultSet pour simuler une ligne retournée par la requête
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Electronics");

        // Appel de la méthode à tester
        List<Category> categories = categoryDao.getAllCategory();

        // Vérifications
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertNotNull(categories);
        assertEquals(1, categories.size());
        Category category = categories.get(0);
        assertEquals(1, category.getId());
        assertEquals("Electronics", category.getName());
    }
}
