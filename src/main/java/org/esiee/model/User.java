package org.esiee.model;

    /**
     * Represents a User in the system.
     */
    public class User {
        private String name;
        private String email;
        private String password;

        /**
         * Gets the name of the user.
         *
         * @return the name of the user
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the email of the user.
         *
         * @return the email of the user
         */
        public String getEmail() {
            return email;
        }

        /**
         * Gets the password of the user.
         *
         * @return the password of the user
         */
        public String getPassword() {
            return password;
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
         * Sets the email of the user.
         *
         * @param email the new email of the user
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * Sets the password of the user.
         *
         * @param password the new password of the user
         */
        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * Constructs a new User with the specified name, email, and password.
         *
         * @param name the name of the user
         * @param email the email of the user
         * @param password the password of the user
         */
        public User(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }