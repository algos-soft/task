package it.algos.task.task;

import com.vaadin.flow.router.*;
import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.enumeration.*;
import it.algos.vbase.ui.view.*;
import org.springframework.beans.factory.annotation.*;

/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: dom, 28-apr-2024
 * Time: 11:11
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@PageTitle("Task")
@Route(value = "task", layout = MainLayout.class)
@AView(menuGroup = MenuGroup.task)
public class TaskView extends CrudView {


    TaskView(@Autowired TaskService moduloCrudService) {
        super(moduloCrudService, TaskList.class, TaskForm.class);
    }


}// end of @Route CrudView class
