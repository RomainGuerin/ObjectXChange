package org.esiee.model;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Represents a user with an ID, name, email, and password.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!?]).{8,20}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    private int id;
    private String name;
    private String email;
    private String password;

    /**
     * Constructs a new User with the specified ID, name, email, and password.
     *
     * @param id the ID of the user
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @throws IllegalArgumentException if the email or password format is invalid
     */
    public User(int id, String name, String email, String password) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and include a number, a lowercase letter, an uppercase letter, and a special character");
        }
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructs a new User with the specified name, email, and password.
     *
     * @param name the name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @throws IllegalArgumentException if the email or password format is invalid
     */
    public User(String name, String email, String password) {
        this(0, name, email, password);
    }

    /**
     * Constructs a new User with the specified email and password.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @throws IllegalArgumentException if the email or password format is invalid
     */
    public User(String email, String password) {
        this(0, null, email, password);
    }

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the new email of the user
     * @throws IllegalArgumentException if the email format is invalid
     */
    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     * @throws IllegalArgumentException if the password format is invalid
     */
    public void setPassword(String password) {
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and include a number, a lowercase letter, an uppercase letter, and a special character");
        }
        this.password = password;
    }

    /**
     * Validates the email format.
     *
     * @param email the email to be validated
     * @return true if the email format is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * Validates the password format.
     *
     * @param password the password to be validated
     * @return true if the password format is valid, false otherwise
     */
    private boolean isValidPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}