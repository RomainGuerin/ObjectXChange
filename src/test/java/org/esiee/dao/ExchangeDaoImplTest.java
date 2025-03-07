package org.esiee.dao;

import org.esiee.model.Exchange;
import org.esiee.model.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ExchangeDaoImplTest {
    private ExchangeDaoImpl exchangeDao;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> databaseConnectionMock;

    @BeforeEach
    void setUp() throws SQLException {
        // Création des mocks pour les objets JDBC
        Connection mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Simuler prepareStatement() pour les deux signatures utilisées dans le DAO
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock statique de DatabaseConnection.getConnection()
        databaseConnectionMock = mockStatic(DatabaseConnection.class);
        databaseConnectionMock.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

        exchangeDao = new ExchangeDaoImpl();
    }

    @AfterEach
    void tearDown() {
        // Fermeture du mock statique
        databaseConnectionMock.close();
    }

    @Test
    void testSaveExchange() throws SQLException {
        // Création d'un échange pour le test
        Exchange exchange = new Exchange(0, 100, 200, Status.Pending, new Date(), new Date());

        // Appel de la méthode save
        exchangeDao.save(exchange);

        // Vérification que les bons paramètres ont été passés
        verify(mockPreparedStatement, times(1)).setInt(1, 100);
        verify(mockPreparedStatement, times(1)).setInt(2, 200);
        verify(mockPreparedStatement, times(1)).setString(3, Status.Pending.toString());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testUpdateExchange() throws SQLException {
        int exchangeId = 10;
        Date now = new Date();
        // Création d'un échange avec un id et une date de mise à jour
        Exchange exchange = new Exchange(exchangeId, 100, 200, Status.Accepted, new Date(), now);

        // Simuler qu'une ligne a été affectée lors de l'update
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = exchangeDao.update(exchange);

        // Vérifier les paramètres transmis
        verify(mockPreparedStatement, times(1)).setString(1, Status.Accepted.toString());
        verify(mockPreparedStatement, times(1)).setString(2, now.toString());
        verify(mockPreparedStatement, times(1)).setInt(3, exchangeId);
        verify(mockPreparedStatement, times(1)).executeUpdate();

        assertTrue(result);
    }

    @Test
    void testGetExchangesByUserId() throws SQLException {
        int userId = 7;
        // Simulation d'un ResultSet contenant une ligne pour un échange
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(15);
        when(mockResultSet.getInt("product_id_asked")).thenReturn(101);
        when(mockResultSet.getInt("product_id_offered")).thenReturn(202);
        when(mockResultSet.getString("status")).thenReturn(Status.Pending.toString());
        Timestamp createdTimestamp = Timestamp.valueOf("2022-01-01 00:00:00");
        Timestamp updatedTimestamp = Timestamp.valueOf("2022-01-02 00:00:00");
        when(mockResultSet.getTimestamp("created_at")).thenReturn(createdTimestamp);
        when(mockResultSet.getTimestamp("updated_at")).thenReturn(updatedTimestamp);

        List<Exchange> exchanges = exchangeDao.getExchangesByUserId(userId);

        // Vérifier que les paramètres ont été passés
        verify(mockPreparedStatement, times(1)).setInt(1, userId);
        verify(mockPreparedStatement, times(1)).setInt(2, userId);
        verify(mockPreparedStatement, times(1)).executeQuery();

        assertNotNull(exchanges);
        assertEquals(1, exchanges.size());
        Exchange exchange = exchanges.get(0);
        assertEquals(15, exchange.getId());
        assertEquals(101, exchange.getProductIdAsked());
        assertEquals(202, exchange.getProductIdOffered());
        assertEquals(Status.Pending, exchange.getStatus());
        assertEquals(createdTimestamp.getTime(), exchange.getDateCreated().getTime());
        assertEquals(updatedTimestamp.getTime(), exchange.getDateUpdated().getTime());
    }
}
