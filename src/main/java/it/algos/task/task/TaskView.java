package it.algos.task.task;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import it.algos.vbase.backend.annotation.IView;
import it.algos.vbase.backend.annotation.IView;
import it.algos.vbase.backend.constant.Gruppo;
import it.algos.vbase.ui.view.AView;
import it.algos.vbase.ui.view.AView;
import it.algos.vbase.ui.view.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Project base24
 * Created by Algos
 * User: gac
 * Date: dom, 28-apr-2024
 * Time: 11:11
 *
 * @Route chiamata dal menu generale o dalla barra del browser <br>
 */
@Route(value = "task", layout = MainLayout.class)
@IView(menuGroup = Gruppo.TASK, menuName = "Task", vaadin = VaadinIcon.STOPWATCH)
public class TaskView extends AView {


    TaskView(@Autowired TaskService moduloCrudService) {
        super(moduloCrudService, TaskList.class, TaskForm.class);
    }


}// end of @Route CrudView class
