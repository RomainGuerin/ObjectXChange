package org.esiee.manager;

import org.esiee.model.*;
import org.esiee.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class UserManagerTest {
    private UserService userService;
    private UserManager userManager;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userManager = new UserManager(userService);
    }

    @Test
    void testRegister() {
        userManager.register("Alice", "alice@example.com", "Password123!");
        // Vérifie que registerUser est appelé avec un User dont les champs sont corrects
        verify(userService, times(1)).registerUser(argThat(user ->
                "Alice".equals(user.getName()) &&
                        "alice@example.com".equals(user.getEmail()) &&
                        "Password123!".equals(user.getPassword())
        ));
    }

    @Test
    void testLogin() {
        User dummyUser = new User("Alice", "alice@example.com", "Password123!");
        when(userService.loginUser(any(User.class))).thenReturn(dummyUser);

        User user = userManager.login("alice@example.com", "Password123!");
        verify(userService, times(1)).loginUser(argThat(u ->
                "alice@example.com".equals(u.getEmail()) &&
                        "Password123!".equals(u.getPassword())
        ));
        assertNotNull(user);
        assertEquals("Alice", user.getName());
    }

    @Test
    void testAddProduct() {
        userManager.addProduct("Laptop", "A powerful laptop", "laptop.jpg", 1, 2, true);
        verify(userService, times(1)).addProduct(argThat(p ->
                "Laptop".equals(p.getName()) &&
                        "A powerful laptop".equals(p.getDescription()) &&
                        "laptop.jpg".equals(p.getImage()) &&
                        p.getUserId() == 1 &&
                        p.getCategoryId() == 2 &&
                        p.isAvailable()
        ));
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(10, "Tablet", "A tablet", new Timestamp(System.currentTimeMillis()),"tablet.jpg", 1, 3, false);
        when(userService.updateProduct(product, true)).thenReturn(true);
        boolean result = userManager.updateProduct(product, true);
        verify(userService, times(1)).updateProduct(product, true);
        assertTrue(result);
    }

    @Test
    void testGetAllProducts() {
        List<Product> dummyProducts = Collections.singletonList(
                new Product(1, "Phone", "Smartphone", null, "phone.jpg", 1, 2, true));
        when(userService.getAllProducts()).thenReturn(dummyProducts);
        List<Product> products = userManager.getAllProducts();
        verify(userService, times(1)).getAllProducts();
        assertEquals(dummyProducts, products);
    }

    @Test
    void testGetAllProductsByUserId() {
        List<Product> dummyProducts = Collections.singletonList(
                new Product(1, "Phone", "Smartphone", null, "phone.jpg", 1, 2, true));
        when(userService.getAllProductsByUserId(1)).thenReturn(dummyProducts);
        List<Product> products = userManager.getAllProductsByUserId(1);
        verify(userService, times(1)).getAllProductsByUserId(1);
        assertEquals(dummyProducts, products);
    }

    @Test
    void testGetAllProductsFiltered() {
        List<Product> dummyProducts = Collections.singletonList(
                new Product(1, "Phone", "Smartphone", null, "phone.jpg", 1, 2, true));
        when(userService.getAllProductsFiltered("Phone", 2)).thenReturn(dummyProducts);
        List<Product> products = userManager.getAllProductsFiltered("Phone", 2);
        verify(userService, times(1)).getAllProductsFiltered("Phone", 2);
        assertEquals(dummyProducts, products);
    }

    @Test
    void testGetAllCategory() {
        List<Category> dummyCategories = Collections.singletonList(new Category(1, "Electronics"));
        when(userService.getAllCategory()).thenReturn(dummyCategories);
        List<Category> categories = userManager.getAllCategory();
        verify(userService, times(1)).getAllCategory();
        assertEquals(dummyCategories, categories);
    }

    @Test
    void testSetNewExchange() {
        userManager.setNewExchange(100, 200, Status.PENDING);
        verify(userService, times(1)).setNewExchange(argThat(exchange ->
                exchange.getProductIdAsked() == 100 &&
                        exchange.getProductIdOffered() == 200 &&
                        exchange.getStatus() == Status.PENDING
        ));
    }

    @Test
    void testUpdateExchange() {
        Exchange exchange = new Exchange(10, 100, 200, Status.PENDING, new Date(), new Date());
        when(userService.updateExchange(exchange, Status.ACCEPTED)).thenReturn(true);
        boolean result = userManager.updateExchange(exchange, Status.ACCEPTED);
        verify(userService, times(1)).updateExchange(exchange, Status.ACCEPTED);
        assertTrue(result);
    }

    @Test
    void testGetAllExchangesByUserId() {
        List<Exchange> dummyExchanges = Collections.singletonList(
                new Exchange(1, 100, 200, Status.PENDING, new Date(), new Date()));
        when(userService.getAllExchangesByUserId(1)).thenReturn(dummyExchanges);
        List<Exchange> exchanges = userManager.getAllExchangesByUserId(1);
        verify(userService, times(1)).getAllExchangesByUserId(1);
        assertEquals(dummyExchanges, exchanges);
    }
}
