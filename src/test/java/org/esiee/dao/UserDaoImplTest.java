package org.esiee.dao;

import org.esiee.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserDaoImplTest {
    private UserDaoImpl userDao;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> databaseConnectionMock;

    @BeforeEach
    void setUp() throws SQLException {
        // Créer les mocks pour la connexion, la requête et le résultat
        Connection mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Simuler la préparation de la requête avec ou sans générer les clés
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock statique pour DatabaseConnection.getConnection()
        databaseConnectionMock = mockStatic(DatabaseConnection.class);
        databaseConnectionMock.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

        userDao = new UserDaoImpl();
    }

    @AfterEach
    void tearDown() {
        // Fermer le mock statique
        databaseConnectionMock.close();
    }

    @Test
    void testSaveUser() throws SQLException {
        User user = new User("Alice", "alice@example.com", "Secure123@!");

        // Appeler la méthode à tester
        userDao.save(user);

        // Vérifier que les paramètres sont bien passés et que la requête est exécutée
        verify(mockPreparedStatement, times(1)).setString(1, "alice@example.com");
        verify(mockPreparedStatement, times(1)).setString(2, "Alice");
        verify(mockPreparedStatement, times(1)).setString(3, "Secure123@!");
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testGetByEmail() throws SQLException {
        // Simuler le comportement du ResultSet
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Alice");
        when(mockResultSet.getString("email")).thenReturn("alice@example.com");
        when(mockResultSet.getString("password")).thenReturn("Secure123@!");

        // Appeler la méthode à tester
        User retrievedUser = userDao.getByEmail("alice@example.com");

        // Vérifier que le résultat est correct
        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getName());
        assertEquals("alice@example.com", retrievedUser.getEmail());
    }
}

