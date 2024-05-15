package it.algos.task.schedule;


import com.vaadin.flow.spring.annotation.*;
import it.algos.task.boot.*;
import it.algos.vbase.backend.boot.*;
import it.sauronsoftware.cron4j.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.Scope;

/**
 * Project vaad24
 * Created by Algos
 * User: gac
 * Date: Wed, 28-Dec-2022
 * Time: 19:07
 * Istanza prototype per crearla in BaseBoot quando è stata riempita la variabile BaseVar.taskList <br>
 */
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public  class BaseSchedule extends Scheduler {


    /**
     * Aggiunge i task alla superclasse per l'esecuzione <br>
     */
    public void start() throws IllegalStateException {
        if (!isStarted()) {
            super.start();
        }

        if (TaskVar.taskList != null && TaskVar.taskList.size() > 0) {
            for (BaseTask task : TaskVar.taskList) {
                schedule(task.getPatternSimple(), task);
            }
        }
    }


}