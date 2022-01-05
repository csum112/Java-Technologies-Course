package ro.uaic.info.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private String role = "user";
    @OneToMany
    private Set<Document> files;
}
