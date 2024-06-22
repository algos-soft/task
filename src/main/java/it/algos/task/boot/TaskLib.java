package it.algos.task.boot;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.vbase.backend.annotation.ALib;
import it.algos.vbase.backend.boot.BaseLib;

/**
 * Project crono
 * Created by Algos
 * User: gac
 * Date: mer, 19-giu-2024
 * Time: 07:27
 */
@SpringComponent
@ALib()
public class TaskLib extends BaseLib {

    public TaskLib() {
        super.usaMenuLibreria = true;
        super.menuGroup = "Task";
        super.icon = new Icon(VaadinIcon.DOWNLOAD);
    }
}
