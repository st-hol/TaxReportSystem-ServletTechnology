package ua.training.model.entity;


import java.util.Objects;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ROLE role;
    private User assignedInspector;

    private static User noInspector;

    static {
        noInspector = new User(0);
    }

//    default constructor
    public User() {
    }

    // without assignedInspector constructor
    public User(long id, String firstName, String lastName, String email, String password, ROLE role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.assignedInspector = noInspector;
    }


//    full args constructor
    public User(long id, String firstName, String lastName, String email, String password, ROLE role, User assignedInspector) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.assignedInspector = assignedInspector;
    }

    public User(long id) {
        this.id = id;
    }

    public User getAssignedInspector() {
        return assignedInspector;
    }

    public void setAssignedInspector(User assignedInspector) {
        this.assignedInspector = assignedInspector;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                role == user.role &&
                Objects.equals(assignedInspector, user.assignedInspector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, role, assignedInspector);
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