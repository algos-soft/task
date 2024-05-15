package it.algos.task.task;

import it.algos.task.boot.*;
import it.algos.task.schedule.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import it.algos.vbase.backend.boot.*;
import it.algos.vbase.backend.logic.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: dom, 28-apr-2024
 * Time: 11:11
 */
@Service
public class TaskService extends ModuloService {

    /**
     * Regola la entityClazz associata a questo Modulo e la passa alla superclasse <br>
     * Regola la viewClazz @Route associata a questo Modulo e la passa alla superclasse <br>
     * Regola la listClazz associata a questo Modulo e la passa alla superclasse <br>
     */
    public TaskService() {
        super(TaskEntity.class, TaskView.class);
    }


    /**
     * Creazione di una nuova entity <br>
     * La entity viene salvata sul mongoDB <br>
     *
     * @return la nuova entity appena creata e inserita nel mongoDB
     */
    public TaskEntity creaAlways(String nome, String flag, String schedule, LocalDateTime evento, String server, boolean status, String descrizione, String risultato) {
        return (TaskEntity) mongoService.insert(newEntity(nome, flag, schedule, evento, server, status, descrizione, risultato));
    }


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    @Override
    public TaskEntity newEntity() {
        return newEntity(VUOTA, VUOTA, VUOTA, null, VUOTA, false, VUOTA, VUOTA);
    }


    /**
     * Creazione in memoria di una nuova entity che NON viene salvata <br>
     *
     * @return la nuova entity appena creata (con keyID ma non salvata)
     */
    public TaskEntity newEntity(String nome, String flag, String schedule, LocalDateTime evento, String server, boolean status, String descrizione, String risultato) {
        TaskEntity newEntityBean = TaskEntity.builder()
                .nome(textService.isValid(nome) ? nome : null)
                .flag(textService.isValid(flag) ? flag : null)
                .schedule(textService.isValid(schedule) ? schedule : null)
                .evento(evento)
                .server(textService.isValid(server) ? server : null)
                .status(status)
                .descrizione(textService.isValid(descrizione) ? descrizione : null)
                .risultato(textService.isValid(risultato) ? risultato : null)
                .build();

        return newEntityBean;
    }

    @Override
    //casting only dalla superclasse
    public List<TaskEntity> findAll() {
        return super.findAll();
    }

//    @Override
//    //casting only dalla superclasse
//    public TaskEntity findByKey(final Object keyPropertyValue) {
//        return (TaskEntity) super.findByKey(keyPropertyValue);
//    }


    public List<String> taskCorrenti() {
        List<BaseTask> lista = TaskVar.taskList;
        return lista != null ? lista.stream().map(task->task.getClass().getSimpleName()).collect(Collectors.toList()):null;
    }

}// end of CrudService class
