package ro.uaic.info.lab7.config;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/faces/login.xhtml",
                errorPage = "",
                useForwardToLogin = false
        )
)
@DatabaseIdentityStoreDefinition(
        callerQuery = "SELECT password FROM users WHERE username = ?",
        groupsQuery = "SELECT role FROM users WHERE username = ?",
        priority = 100,
        hashAlgorithmParameters = {
                "Pbkdf2PasswordHash.Iterations=3072",
                "${securityConfig.dyna}"
        }
)
@Named
@ApplicationScoped
public class SecurityConfig {
    public String[] getDyna() {
        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }
}
