package ba.telegroup.apps.gk.controller.genericLogger;

import ba.telegroup.apps.gk.common.CommonController;
import ba.telegroup.apps.gk.dao.repository.LoggerRepository;
import ba.telegroup.apps.gk.model.Logger;
import ba.telegroup.apps.gk.session.KorisnikBean;
import com.rits.cloning.Cloner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.GenericTypeResolver;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by drstjepanovic on 8/28/2017.
 */
public class GenericLogger<T> extends CommonController {

    private Class<T> type;

    private HttpSession httpSession;

    @Autowired
    private LoggerRepository loggerRepository;

    @Value("${loggerConfig.createMessage}")
    private String createMessage;

    @Value("${loggerConfig.updateMessage}")
    private String updateMessage;

    @Value("${loggerConfig.deleteMessage}")
    private String deleteMessage;

    protected Cloner cloner;

    public GenericLogger() {
        cloner = new Cloner();
        this.type = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericLogger.class);
        this.httpSession = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession(true);
    }

    public void logCreateAction(T object) {
        loggerRepository.saveAndFlush(new Logger(((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getId(), Logger.ActionType.CREATE.toString(), createMessage.replace("{entity}", object.toString()), type.getSimpleName(), (byte) 1, ((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getKompanijaId()));
    }

    public void logUpdateAction(T newObject, T oldObject) {
        loggerRepository.saveAndFlush(new Logger(((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getId(), Logger.ActionType.UPDATE.toString(), updateMessage.replace("{oldEntity}", oldObject.toString()).replace("{newEntity}", newObject.toString()), type.getSimpleName(), (byte) 1, ((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getKompanijaId()));
    }

    public void logDeleteAction(T object) {
        loggerRepository.saveAndFlush(new Logger(((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getId(), Logger.ActionType.DELETE.toString(), deleteMessage.replace("{entity}", object.toString()), type.getSimpleName(), (byte) 1, ((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getKompanijaId()));
    }

    public void logSpecificAction(String actionType, String actionDetails, String tableName) {
        loggerRepository.saveAndFlush(new Logger(((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getId(), actionType, actionDetails, tableName, (byte) 0, ((KorisnikBean) httpSession.getAttribute("korisnikBean")).getKorisnik().getKompanijaId()));
    }

}