package it.algos.task.schedule;

import it.algos.task.boot.*;
import it.algos.task.enumeration.*;
import it.algos.task.task.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.boot.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.backend.pref.*;
import it.algos.vbase.backend.service.*;
import it.algos.vbase.backend.wrapper.*;
import it.sauronsoftware.cron4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;

import javax.inject.*;
import java.time.*;
import java.util.*;

/**
 * Project vaad24
 * Created by Algos
 * User: gac
 * Date: Wed, 28-Dec-2022
 * Time: 20:34
 */
public abstract class BaseTask extends Task {

    @Inject
    protected TaskService taskService;

    @Autowired
    DateService dateService;

    protected long inizio;

    protected TypeSchedule typeSchedule;

    //    protected String descrizioneTask;

    protected IPref flagAttivazione;

    protected IPref flagPrevisione;

    protected boolean usaMail = false;

    /**
     * Istanza di una interfaccia <br>
     * Iniettata automaticamente dal framework SpringBoot con l'Annotation @Autowired <br>
     * Disponibile DOPO il ciclo init() del costruttore di questa classe <br>
     */
    @Inject
    public ApplicationContext appContext;

    /**
     * Istanza unica di una classe @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) di servizio <br>
     * Iniettata automaticamente dal framework SpringBoot/Vaadin con l'Annotation @Autowired <br>
     * Disponibile DOPO il ciclo init() del costruttore di questa classe <br>
     */
    @Inject
    protected LoggerService logger;

    @Inject
    MailService mailService;

    /**
     * Deve essere sovrascritto, invocando PRIMA il metodo della superclasse <br>
     */
    @Override
    public void execute(TaskExecutionContext taskExecutionContext) throws RuntimeException {
    }


    public boolean execute() {
        inizio = System.currentTimeMillis();

        if (flagAttivazione == null || flagAttivazione.is()) {
            this.fixNext();
            return true;
        }
        else {
            this.logTaskNonEseguito();
            this.creaLogNonEseguito();

            return false;
        }
    }


    protected void fixNext() {
        //        int nextDays;
        //        LocalDateTime adesso = LocalDateTime.now();
        //        LocalDateTime prossimo = null;
        //
        //        if (typeSchedule != null) {
        //            nextDays = typeSchedule.getGiorniNext();
        //            prossimo = adesso.plusDays(nextDays);
        //        }
        //
        //        if (flagPrevisione != null && prossimo != null) {
        //            flagPrevisione.setValue(prossimo);
        //        }
    }

    public String getPatternSimple() {
        return typeSchedule.getPatternSimple();
    }

    public String getPatternQuadre() {
        return typeSchedule.getPatternQuadre();
    }


    public String getDescrizioneTask() {
        return flagAttivazione != null ? flagAttivazione.getDescrizione() : "Manca";
    }


    public TypeSchedule getTypeSchedule() {
        return typeSchedule;
    }

    public IPref getFlagAttivazione() {
        return flagAttivazione;
    }

    public void logTaskEseguito(String risultato) {
        if (Pref.usaSendMail.is()) {
            String message = getDescrizioneTask() + CAPO + risultato;
            mailService.send(BaseVar.nameServer, message);
        }
    }

    public void logTaskEseguito() {
        String message = String.format("%s Eseguita in %s", getInfo(), dateService.deltaText(inizio));
        logger.info(new WrapLog().type(TypeLog.task).message(message).usaDb());

        if (Pref.usaSendMail.is() && usaMail) {
            mailService.send(getClass().getSimpleName(), message);
        }
    }

    public void logTaskNonEseguito() {
        String message = String.format("%s Non eseguita per flag disabilitato.", getInfo());
        logger.info(new WrapLog().type(TypeLog.task).message(message).usaDb());

        if (Pref.usaSendMail.is() && usaMail) {
            mailService.send(getClass().getSimpleName(), message);
        }
    }


    public String info() {
        String message;
        String clazzName = this.getClass().getSimpleName();
        String desc = this.getDescrizioneTask();
        TypeSchedule type = this.getTypeSchedule();
        String pattern = type.getPatternQuadre();
        String nota = type.getNota();

        message = String.format("%s %s %s %s %s", clazzName, pattern, FORWARD, desc, nota);
        return message;
    }

    public String infoFlag() {
        String message;
        String flagText = TASK_NO_FLAG + TASK_FLAG_SEMPRE_ATTIVA;
        String clazzName = this.getClass().getSimpleName();
        String desc = this.getDescrizioneTask();
        TypeSchedule type = this.getTypeSchedule();
        String pattern = type.getPatternQuadre();
        String nota = type.getNota();
        //        int nextDays = this.getTypeSchedule().getGiorniNext();
        IPref flagTask = this.getFlagAttivazione();
        if (flagTask != null) {
            if (flagTask.is()) {
                flagText = flagTask.getKeyCode() + TASK_FLAG_ATTIVA;
            }
            else {
                flagText = flagTask.getKeyCode() + TASK_FLAG_DISATTIVA;
            }
        }

        message = String.format("%s %s %s%s%s %s", clazzName, pattern, flagText, FORWARD, nota, desc);
        return message;
    }

    public static String infoFlag(Class taskNonIstanziata) {
        BaseTask task = getTask(taskNonIstanziata);
        return task != null ? task.infoFlag() : VUOTA;
    }


    public static BaseTask getTask(Class taskNonIstanziata) {
        List<BaseTask> listaTasks = TaskVar.taskList;

        if (listaTasks != null) {
            for (BaseTask task : listaTasks) {
                if (task.getClass().getSimpleName().equals(taskNonIstanziata.getSimpleName())) {
                    return task;
                }
            }
        }

        return null;
    }

    public void creaLog(String risultatoBreve) {
        creaLog(risultatoBreve, "Eventuale dettaglio da regolare");
    }

    public void creaLogNonEseguito() {
        String risultatoBreve = "Non eseguita per flag disabilitato";
        String nome = this.getClass().getSimpleName();
        String flag = flagAttivazione != null ? flagAttivazione.getKeyCode() : "Nessun flag";
        String schedule = this.typeSchedule.getPatternQuadre();
        LocalDateTime evento = LocalDateTime.now();
        String server = "Pippo";
        taskService.creaAlways(nome, flag, schedule, evento, server, false, risultatoBreve, VUOTA);
    }

    public void creaLog(String risultatoBreve, String risultatoEsteso) {
        String nome = this.getClass().getSimpleName();
        String flag = flagAttivazione != null ? flagAttivazione.getKeyCode() : "Nessun flag";
        String schedule = this.typeSchedule.getPatternQuadre();
        LocalDateTime evento = LocalDateTime.now();
        String server = "Pippo";

        taskService.creaAlways(nome, flag, schedule, evento, server, true, risultatoBreve, risultatoEsteso);
        //            TaskEntity entity= TaskEntity.newEntity(nome, flag, schedule, evento, server, descrizione, risultato);
        //            mongoService.save(entity);
    }


    public String getInfoNota() {
        return String.format("%s %s", getInfo(), getTypeSchedule().getNota());
    }


    public String getInfo() {
        String clazz;
        String schedule;
        String flag;
        String descrizione;

        clazz = this.getClass().getSimpleName();
        schedule = this.getPatternQuadre();
        if (this.getFlagAttivazione() != null) {
            if (this.getFlagAttivazione().is()) {
                flag = String.format("%s=acceso", this.getFlagAttivazione().getKeyCode());
            }
            else {
                flag = String.format("%s=spento", this.getFlagAttivazione().getKeyCode());
            }
        }
        else {
            flag = String.format("Flag attivazione=non previsto");
        }
        descrizione = this.getDescrizioneTask();

        return String.format("%s %s %s%s%s", clazz, schedule, flag, FORWARD, descrizione);
    }


}