package obligatoriskopgave1.models.repositories;

import obligatoriskopgave1.models.entities.PwChange;
import obligatoriskopgave1.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public void create(User u){
        jdbc.update("INSERT INTO user(name, password, email, address) VALUES('" + u.getName() + "','" + u.getPassword() + "','" + u.getEmail() + "','" + u.getAddress() + "')");
    }

    @Override
    public void update(User u){
        jdbc.update("UPDATE user SET name='"+u.getName()+"', password='"+u.getPassword()+"', address='"+u.getAddress()+"', email='"+u.getEmail()+"' WHERE user_id = "+u.getUserId());

    }

    @Override
    public User read(String email) {
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT * FROM user WHERE email='" + email + "'");
        if(sqlRowSet.next()){
            return new User(sqlRowSet.getInt("user_id"), sqlRowSet.getString("name"), sqlRowSet.getString("address"), sqlRowSet.getString("email"), sqlRowSet.getString("password"));
        }
        return new User();
    }

    @Override
    public boolean checkUser(User u){
        // Insert the given user with matching email and password from db into a SqlRowSet if there is one
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT email, password FROM user WHERE email='"+u.getEmail()+"' AND password='"+u.getPassword()+"'");
        // returns true or false if there was anything put into our sqlRowSet
        return sqlRowSet.next(); // (true/false)
    }

    @Override
    public boolean checkEmail(String e){
        // Insert the given email that matches the email from db into a SqlRowSet if there is one
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT email FROM user WHERE email='"+e+"'");
        // returns true or false if there was anything put into our sqlRowSet
        return sqlRowSet.next(); // (true/false)
    }

    @Override
    public boolean checkPassword(String p, String id){
        // Insert the given email that matches the email from db into a SqlRowSet if there is one
        SqlRowSet sqlRowSet = jdbc.queryForRowSet("SELECT password FROM user WHERE password='"+p+"'"+" AND user_id="+id);
        // returns true or false if there was anything put into our sqlRowSet
        return sqlRowSet.next(); // (true/false)
    }

    @Override
    public void setLogInInfo(User u) {
        User tempU = read(u.getEmail());
        u.setUserId(tempU.getUserId());
        u.setAddress(tempU.getAddress());
        u.setName(tempU.getName());
    }

    @Override
    public void changePassword(PwChange p, String userId){
        if (checkPassword(p.getOldpw(), userId)){
            if(p.getPw1().equals(p.getPw2())){
                //System.out.println("changePassword if statement all true");
                jdbc.update("UPDATE user SET password='"+p.getPw1()+"' WHERE user_id="+userId);
            }
        }
    }

    @Override
    public void delete(User u){
        jdbc.update("DELETE FROM user WHERE email='" + u.getEmail()+"'");
    }

}