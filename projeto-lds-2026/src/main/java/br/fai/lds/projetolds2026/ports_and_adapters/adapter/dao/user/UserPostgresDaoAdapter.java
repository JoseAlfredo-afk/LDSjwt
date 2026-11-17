package br.fai.lds.projetolds2026.ports_and_adapters.adapter.dao.user;

import br.fai.lds.projetolds2026.domain.UserModel;
import br.fai.lds.projetolds2026.ports_and_adapters.port.dao.user.UserDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserPostgresDaoAdapter implements UserDao {

    private final Connection connection;

    public UserPostgresDaoAdapter(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(UserModel entity) {

        String sql = " INSERT INTO user_model(password, fullname, email, role) ";
        sql += " VALUES(?, ?, ?, ?);";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setString(2, entity.getFullname());
            preparedStatement.setString(3, entity.getEmail());
            preparedStatement.setString(4, entity.getRole().name());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
            resultSet.close();
            return id;

        } catch (Exception e) {

            try {
                connection.rollback();
            } catch (Exception ex) {

                throw new RuntimeException(ex);
            }

            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(int id) {
        String sql = " DELETE FROM user_model ";
        sql += " WHERE id = ?; ";

        //final StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("DELETE FROM user_model ");
        //stringBuilder.append("WHERE id =? ;" );
        //final String sql2=stringBuilder.toString();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public UserModel readyById(int id) {
        final String sql = "SELECT * FROM user_model WHERE id = ? ;";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final int entityId = resultSet.getInt("id");
                final String fullname = resultSet.getString("fullname");
                final String email = resultSet.getString("email");
                final String password = resultSet.getString("password");

                final String auxRole = resultSet.getString("role");
                final UserModel.UserRole role = UserModel.UserRole.valueOf(auxRole);


                final UserModel userModel = new UserModel();
                userModel.setId(entityId);
                userModel.setFullname(fullname);
                userModel.setEmail(email);
                userModel.setPassword(password);
                userModel.setRole(role);

                preparedStatement.close();
                resultSet.close();

                return userModel;
            }

            return null;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<UserModel> readAll() {
        final List<UserModel> entities = new ArrayList<>();

        final String sql = "SELECT * FROM user_model ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int entityId = resultSet.getInt("id");
                String fullname = resultSet.getString("fullname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                String auxRole = resultSet.getString("role");
                UserModel.UserRole role = UserModel.UserRole.valueOf(auxRole);


                UserModel data = new UserModel();
                data.setId(entityId);
                data.setFullname(fullname);
                data.setEmail(email);
                data.setPassword(password);
                data.setRole(role);

                entities.add(data);
            }

            resultSet.close();
            preparedStatement.close();

            return entities;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        
    }

    @Override
    public void updateInformation(int id, UserModel entity) {
        String sql = "UPDATE user_model SET fullname =? ";
        sql +=" WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getFullname());
            preparedStatement.setInt(2,entity.getId());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public UserModel readByEmail(String email) {
        final String sql = "SELECT * FROM user_model WHERE email = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                int entityId = resultSet.getInt("id");
                String fullname = resultSet.getString("fullname");
                String password = resultSet.getString("password");

                String auxRole = resultSet.getString("role");
                UserModel.UserRole role = UserModel.UserRole.valueOf(auxRole);


                UserModel entity = new UserModel();
                entity.setId(entityId);
                entity.setFullname(fullname);
                entity.setEmail(email);
                entity.setPassword(password);
                entity.setRole(role);

                preparedStatement.close();
                resultSet.close();

                return entity;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return null;
    }




    @Override
    public boolean updatePassword(int id, String password) {

        String sql = "UPDATE user_model set password = ? ";
        sql += " WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,password);
            preparedStatement.setInt(2,id);

            preparedStatement.execute();
            preparedStatement.close();

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
