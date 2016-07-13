package entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity(name="roles")
@Table(name="roles")
public class Role
{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="userID", nullable = false)
    private int userId;
    @Column(name="role", nullable = false, length = 20)
    private String role;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userID", insertable = false, updatable = false)
    User user;

    public Role(){}

    public Role(int userId, String role)
    {
        this.userId = userId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
