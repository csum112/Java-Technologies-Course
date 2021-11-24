package ro.uaic.info.lab3.beans;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Data
@Named
@SessionScoped
public class LocaleBean implements Serializable {
    private Locale locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}
