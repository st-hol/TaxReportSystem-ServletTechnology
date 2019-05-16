package ua.training.model.entity;


public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ROLE role;



    public User() {
    }


    public User(long id, String email, String password, ROLE role) {
        this.id = id;

        this.firstName = "Unknown";
        this.lastName = "Unknown";

        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(long id, String firstName, String lastName, String email, String password, ROLE role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }


    public enum ROLE {
        INSPECTOR, CLIENT, UNKNOWN;

        public int getRoleID(){
            return ordinal() + 1;
        }

        public static ROLE getRoleById(int id){
            int index = id - 1;
            return values()[index];
        }


    }
}