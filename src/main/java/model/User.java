package model;

/**
 * Model class for User entity
 */
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;

    public User(){};

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String role) {
        this.email = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof User) && (id != null)
                ? id.equals(((User) other).id)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (this.getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("User[id=%d,email=%s,username=%s]",
                id, email, name);
    }
}
