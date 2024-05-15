package it.algos.task.boot;

import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.task.schedule.*;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Project task
 * Created by Algos
 * User: gac
 * Date: mer, 15-mag-2024
 * Time: 15:25
 */
@Service
public class TaskVar {

    /**
     * Lista delle (eventuali) task per gli eventi Schedule. <br>
     * Deve essere regolato in TaskBoot <br>
     */
    public static List<BaseTask> taskList = new ArrayList<>();

}
