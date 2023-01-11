package login.LoginSystem.entities;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name="users",
uniqueConstraints={
@UniqueConstraint(columnNames = "username"),
@UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique=true, length=70)
    private String email;
    @Column(name="username", nullable = false, length=50)
    private String username;
    @Column(nullable = false, length=50)
    private String password;
    @Column(name="phonenumber", nullable = false, length=60)
    private Integer phonenumber;

    public User(String username, String email, String encode, String phonenumber) {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="user_roles",
    joinColumns = @JoinColumn(name="user_id"),
inverseJoinColumns =@JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();
}