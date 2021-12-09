package ro.uaic.info.lab7.beans;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped
@PermitAll
public class LoginBean {
    @Inject
    private SecurityContext securityContext;
    @NotNull
    @Getter
    @Setter
    private String username;
    @NotNull
    @Getter
    @Setter
    private String password;

    public void login() {
        final Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext
                .authenticate(
                        getReq(),
                        getRes(),
                        AuthenticationParameters
                                .withParams()
                                .credential(credential)
                );
        System.out.println(status);
    }

    private HttpServletResponse getRes() {
        return (HttpServletResponse) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getResponse();
    }

    private HttpServletRequest getReq() {
        return (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
    }


}
