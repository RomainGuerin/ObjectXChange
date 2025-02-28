package org.esiee.dao;

            import org.esiee.model.User;

            import java.sql.*;

            /**
             * Implementation of the UserDao interface for interacting with the User data in a SQLite database.
             */
            public class UserDaoImpl implements UserDao {

                // URL of the SQLite database
                private static final String DB_URL = "jdbc:sqlite:database.db";

                /**
                 * Saves a user to the database.
                 *
                 * @param user The user to be saved
                 * @throws RuntimeException if there is an error saving the user
                 */
                @Override
                public void save(User user) {
                    String sql = "INSERT INTO User(name, email, password) VALUES(?, ?, ?)";

                    try (Connection conn = DriverManager.getConnection(DB_URL);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {

                        pstmt.setString(1, user.getName());
                        pstmt.setString(3, user.getEmail());
                        pstmt.setString(2, user.getPassword());

                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException("Error saving user", e);
                    }
                }

                /**
                 * Finds a user by their name.
                 *
                 * @param name The name of the user to find
                 * @return The user with the specified name, or null if not found
                 */
                @Override
                public User findByName(String name) {
                    String sql = "SELECT * FROM User WHERE name = ?";

                    try (Connection conn = DriverManager.getConnection(DB_URL);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {

                        pstmt.setString(1, name);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            return new User(
                                    rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("password")
                            );
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                }

                /**
                 * Finds a user by their email.
                 *
                 * @param email The email of the user to find
                 * @return The user with the specified email, or null if not found
                 */
                @Override
                public User findByEmail(String email) {
                    String sql = "SELECT * FROM User WHERE email = ?";

                    try (Connection conn = DriverManager.getConnection(DB_URL);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {

                        pstmt.setString(1, email);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            return new User(
                                    rs.getString("name"),
                                    rs.getString("password"),
                                    rs.getString("email")
                            );
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                }
            }