package org.esiee.model;

    /**
     * Represents a category with an ID and a name.
     */
    public class Category {
        private int id;
        private String name;

        /**
         * Constructs a new Category with the specified ID and name.
         *
         * @param id the ID of the category
         * @param name the name of the category
         * @throws IllegalArgumentException if the ID is negative or the name is null or empty
         */
        public Category(int id, String name) {
            if (id < 0 || name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Invalid id or name");
            }
            this.id = id;
            this.name = name;
        }

        /**
         * Returns the ID of the category.
         *
         * @return the ID of the category
         */
        public int getId() {
            return id;
        }

        /**
         * Sets the ID of the category.
         *
         * @param id the new ID of the category
         * @throws IllegalArgumentException if the ID is negative
         */
        public void setId(int id) {
            if (id < 0) {
                throw new IllegalArgumentException("Invalid id, cannot be negative");
            }
            this.id = id;
        }

        /**
         * Returns the name of the category.
         *
         * @return the name of the category
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the name of the category.
         *
         * @param name the new name of the category
         * @throws IllegalArgumentException if the name is null or empty
         */
        public void setName(String name) {
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Invalid name, cannot be null or empty");
            }
            this.name = name;
        }
    }