package org.esiee.dao;

import org.esiee.model.Product;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ITProductDaoImpl {
    private ProductDaoImpl productDao;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private MockedStatic<DatabaseConnection> databaseConnectionMock;

    @BeforeEach
    void setUp() throws SQLException {
        // Création des mocks JDBC
        Connection mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Simuler prepareStatement pour les deux signatures utilisées dans le DAO
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Mock statique de DatabaseConnection.getConnection() pour renvoyer notre mock
        databaseConnectionMock = mockStatic(DatabaseConnection.class);
        databaseConnectionMock.when(DatabaseConnection::getConnection).thenReturn(mockConnection);

        productDao = new ProductDaoImpl();
    }

    @AfterEach
    void tearDown() {
        // Fermeture du mock statique
        databaseConnectionMock.close();
    }

    @Test
    void testSaveProduct() throws SQLException {
        // Création d'un produit à enregistrer
        Product product = new Product("Laptop", "A powerful laptop", "laptop.jpg", 1, 2, true);

        // Appel de la méthode save
        productDao.save(product);

        // Vérification que les bons paramètres sont passés à la requête préparée
        verify(mockPreparedStatement, times(1)).setString(1, "Laptop");
        verify(mockPreparedStatement, times(1)).setString(2, "A powerful laptop");
        verify(mockPreparedStatement, times(1)).setString(3, "laptop.jpg");
        verify(mockPreparedStatement, times(1)).setInt(4, 1);
        verify(mockPreparedStatement, times(1)).setInt(5, 2);
        verify(mockPreparedStatement, times(1)).setBoolean(6, true);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testUpdateProduct() throws SQLException {
        // Création d'un produit avec id et disponibilité à mettre à jour
        Product product = new Product(10, "Tablet", "A lightweight tablet", Date.valueOf("2021-01-01"), "tablet.jpg", 2, 3, false);

        // Simuler qu'une ligne a été affectée lors de l'update
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = productDao.update(product);

        // Vérification des paramètres passés
        verify(mockPreparedStatement, times(1)).setBoolean(1, false);
        verify(mockPreparedStatement, times(1)).setInt(2, 10);
        verify(mockPreparedStatement, times(1)).executeUpdate();

        assertTrue(result);
    }

    @Test
    void testGetAllProducts() throws SQLException {
        // Simulation d'un ResultSet contenant une ligne représentant un produit
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(5);
        when(mockResultSet.getString("name")).thenReturn("Smartphone");
        when(mockResultSet.getString("description")).thenReturn("A smartphone description");
        when(mockResultSet.getDate("date_created")).thenReturn(Date.valueOf("2022-01-01"));
        when(mockResultSet.getString("images")).thenReturn("smartphone.jpg");
        when(mockResultSet.getInt("user_id")).thenReturn(3);
        when(mockResultSet.getInt("category_id")).thenReturn(4);
        when(mockResultSet.getBoolean("availability")).thenReturn(true);

        List<Product> products = productDao.getAllProducts();

        verify(mockPreparedStatement, times(1)).executeQuery();
        assertNotNull(products);
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals(5, product.getId());
        assertEquals("Smartphone", product.getName());
    }

    @Test
    void testGetProductByUserId() throws SQLException {
        // Simulation d'un ResultSet pour un produit appartenant à l'utilisateur avec user_id = 7
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(8);
        when(mockResultSet.getString("name")).thenReturn("Camera");
        when(mockResultSet.getString("description")).thenReturn("A digital camera");
        when(mockResultSet.getDate("date_created")).thenReturn(Date.valueOf("2022-05-01"));
        when(mockResultSet.getString("images")).thenReturn("camera.jpg");
        when(mockResultSet.getInt("user_id")).thenReturn(7);
        when(mockResultSet.getInt("category_id")).thenReturn(2);
        when(mockResultSet.getBoolean("availability")).thenReturn(true);

        List<Product> products = productDao.getProductByUserId(7);

        // Vérifier que l'ID utilisateur est bien passé en paramètre
        verify(mockPreparedStatement, times(1)).setInt(1, 7);
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertNotNull(products);
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals(8, product.getId());
        assertEquals("Camera", product.getName());
    }

    @Test
    void testGetFilteredProducts() throws SQLException {
        // Simulation d'un ResultSet pour filtrer les produits
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("id")).thenReturn(12);
        when(mockResultSet.getString("name")).thenReturn("Headphones");
        when(mockResultSet.getString("description")).thenReturn("Noise-cancelling headphones");
        when(mockResultSet.getDate("date_created")).thenReturn(Date.valueOf("2022-06-01"));
        when(mockResultSet.getString("images")).thenReturn("headphones.jpg");
        when(mockResultSet.getInt("user_id")).thenReturn(5);
        when(mockResultSet.getInt("category_id")).thenReturn(9);
        when(mockResultSet.getBoolean("availability")).thenReturn(true);

        List<Product> products = productDao.getFilteredProducts("Head", 9);

        verify(mockPreparedStatement, times(1)).setString(1, "%Head%");
        verify(mockPreparedStatement, times(1)).setInt(2, 9);
        verify(mockPreparedStatement, times(1)).executeQuery();
        assertNotNull(products);
        assertEquals(1, products.size());
        Product product = products.get(0);
        assertEquals(12, product.getId());
        assertEquals("Headphones", product.getName());
    }
}
