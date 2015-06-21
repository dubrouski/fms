package net.dubrouski.fams.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class ControllerHelper {

    public static void addMessage(Severity severity, String summary, String detail,
            boolean keepAfterRedirect) {

        FacesMessage msg = new FacesMessage(severity, summary, detail);

        FacesContext.getCurrentInstance().addMessage(null, msg);

        if (keepAfterRedirect) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash()
                    .setKeepMessages(true);
        }
    }

    public static void addMessage(Severity severity, String message,
            boolean keepAfterRedirect) {

        addMessage(severity, message, message, keepAfterRedirect);
    }

    public static void addInfoMessage(String summary, String detail, boolean keepAfterRedirect) {
        addMessage(FacesMessage.SEVERITY_INFO, summary, detail, keepAfterRedirect);
    }
    
    public static void addWarnMessage(String summary, String detail, boolean keepAfterRedirect) {
        addMessage(FacesMessage.SEVERITY_WARN, summary, detail, keepAfterRedirect);
    }
    
    public static void addErrorMessage(String summary, String detail, boolean keepAfterRedirect) {
        addMessage(FacesMessage.SEVERITY_ERROR, summary, detail, keepAfterRedirect);
    }
}
