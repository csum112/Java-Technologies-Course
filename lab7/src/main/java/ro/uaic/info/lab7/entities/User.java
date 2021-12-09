package ro.uaic.info.lab7.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @OneToMany
    @NotNull
    private Collection<UserFile> files;
}
