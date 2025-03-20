package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.DatabaseConnection;
import model.Users;

public class UsersDAO {
    public Users validateUsers(String usersId, String password) {
        Users users = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM USERS WHERE LOGINID = ? AND LOGINPASS = ?")) { 
            preparedStatement.setString(1, usersId);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                users = new Users();
                users.setUsersId(resultSet.getString("LOGINID"));
                users.setPassword(resultSet.getString("LOGINPASS"));
                users.setUsersName(resultSet.getString("USERSNAME")); 
                users.setUsersPostcode(resultSet.getString("USERSPOSTCODE")); 
                users.setUsersAddress(resultSet.getString("USERSADDRESS")); 
                users.setUsersTel(resultSet.getString("USERSTEL")); 
                users.setUsersMail(resultSet.getString("USERSMAIL"));
                users.setUsersPay(resultSet.getString("USERSPAY")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public Users findUsersById(String usersId) {
        Users users = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM USERS WHERE LOGINID = ?")) { 
            preparedStatement.setString(1, usersId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                users = new Users();
                users.setUsersId(resultSet.getString("LOGINID"));
                users.setPassword(resultSet.getString("LOGINPASS"));
                users.setUsersName(resultSet.getString("USERSNAME"));
                users.setUsersPostcode(resultSet.getString("USERSPOSTCODE"));
                users.setUsersAddress(resultSet.getString("USERSADDRESS"));
                users.setUsersTel(resultSet.getString("USERSTEL"));
                users.setUsersMail(resultSet.getString("USERSMAIL"));
                users.setUsersPay(resultSet.getString("USERSPAY"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public Users getUsersById(String usersId) {
        Users users = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT * FROM USERS WHERE LOGINID = ?")) { 
            preparedStatement.setString(1, usersId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                users = new Users();
                users.setUsersId(resultSet.getString("LOGIN_ID"));
                users.setPassword(resultSet.getString("LOGIN_PASS"));
                users.setUsersName(resultSet.getString("USERSNAME")); 
                users.setUsersPostcode(resultSet.getString("USERSPOSTCODE")); 
                users.setUsersAddress(resultSet.getString("USERSADDRESS")); 
                users.setUsersTel(resultSet.getString("USERSTEL")); 
                users.setUsersMail(resultSet.getString("USERSMAIL")); 
                users.setUsersPay(resultSet.getString("USERSPAY")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean isUsersIdExists(String usersId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("SELECT COUNT(*) FROM USERS WHERE LOGINID = ?")) { 
            preparedStatement.setString(1, usersId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertUsers(Users users) {
        if (isUsersIdExists(users.getUsersId())) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT INTO USERS (LOGINID, LOGINPASS, USERSNAME, USERSPOSTCODE, USERSADDRESS, USERSTEL, USERSMAIL, USERSPAY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) { 
            preparedStatement.setString(1, users.getUsersId());
            preparedStatement.setString(2, users.getPassword());
            preparedStatement.setString(3, users.getUsersName()); 
            preparedStatement.setString(4, users.getUsersPostcode()); 
            preparedStatement.setString(5, users.getUsersAddress()); 
            preparedStatement.setString(6, users.getUsersTel()); 
            preparedStatement.setString(7, users.getUsersMail()); 
            preparedStatement.setString(8, users.getUsersPay()); 
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUsers(Users users) {
        if (!isUsersIdExists(users.getUsersId())) {
            return false;
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("UPDATE USERS SET LOGINPASS = ?, USERSNAME = ?, USERSPOSTCODE = ?, USERSADDRESS = ?, USERSTEL = ?, USERSMAIL = ?, USERSPAY = ? WHERE LOGINID = ?")) { 
            preparedStatement.setString(1, users.getPassword());
            preparedStatement.setString(2, users.getUsersName());
            preparedStatement.setString(3, users.getUsersPostcode()); 
            preparedStatement.setString(4, users.getUsersAddress()); 
            preparedStatement.setString(5, users.getUsersTel()); 
            preparedStatement.setString(6, users.getUsersMail()); 
            preparedStatement.setString(7, users.getUsersPay()); 
            preparedStatement.setString(8, users.getUsersId());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
