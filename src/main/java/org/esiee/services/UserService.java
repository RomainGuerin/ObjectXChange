package org.esiee.services;

    import org.esiee.dao.UserDao;
    import org.esiee.model.User;

    /**
     * Service class for managing user-related operations.
     */
    public class UserService {
        private UserDao userDao;

        /**
         * Constructs a UserService with the specified UserDao.
         *
         * @param userDao The UserDao to be used by this service
         */
        public UserService(UserDao userDao) {
            this.userDao = userDao;
        }

        /**
         * Registers a new user with the specified name, password, and email.
         *
         * @param name The name of the user
         * @param password The password of the user
         * @param email The email of the user
         * @throws IllegalArgumentException if a user with the same name or email already exists
         */
        public void registerUser(String name, String password, String email) {
            if (userDao.findByName(name) != null || userDao.findByEmail(email) != null) {
                throw new IllegalArgumentException("User already exists");
            }
            User newUser = new User(name, password, email);
            userDao.save(newUser);
        }

        /**
         * Logs in a user with the specified name and password.
         *
         * @param name The name of the user
         * @param password The password of the user
         * @return The logged-in user
         * @throws IllegalArgumentException if the credentials are invalid
         */
        public User loginUser(String name, String password) {
            User user = userDao.findByName(name);
            if (user == null || !user.getPassword().equals(password)) {
                throw new IllegalArgumentException("Invalid credentials");
            }
            return user;
        }
    }