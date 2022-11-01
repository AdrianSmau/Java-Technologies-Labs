package com.example.lab3;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

@ManagedBean
@ApplicationScoped
public class LanguageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Map<String, Object> countries = Map.of("English", Locale.ENGLISH, "French", Locale.FRENCH);

    private String localeCode;

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }


    public String getLocaleCode() {
        return localeCode;
    }


    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void changeLocale() {
        String newLocaleValue = localeCode;

        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {

                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());
            }
        }
    }
}
