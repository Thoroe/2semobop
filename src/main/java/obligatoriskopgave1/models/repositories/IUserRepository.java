package obligatoriskopgave1.models.repositories;

import obligatoriskopgave1.models.entities.PwChange;
import obligatoriskopgave1.models.entities.User;

import java.util.ArrayList;

public interface IUserRepository {

    // Create user
    public void create (User u);

    // Read
    public User read(String email);

    // Update user
    public void update (User u);
    public void changePassword(PwChange p, String userId);
    public void setLogInInfo(User u);

    // Match users in database with user input
    public boolean checkUser(User u);
    public boolean checkEmail(String e);
    public boolean checkPassword(String p, String id);

    // Delete user
    public void delete (User u);
}