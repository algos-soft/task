package it.algos.task.boot;

import it.algos.vbase.backend.boot.*;
import static it.algos.vbase.backend.boot.BaseCost.*;
import static it.algos.vbase.backend.boot.BaseVar.*;
import it.algos.vbase.backend.logic.*;
import it.algos.vbase.backend.pref.*;
import it.algos.vbase.backend.wrapper.*;
import it.algos.vbase.ui.view.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Project wiki24
 * Created by Algos
 * User: gac
 * Date: Thu, 16-Nov-2023
 * Time: 13:55
 */
@Service
@Component("taskBoot")
public class TaskBoot extends BaseBoot {


    public TaskBoot() {
    }

    public void inizia() {
        super.inizia();
        this.creaTask();
    }

    /**
     * Aggiunge al menu le @Route (view) standard e specifiche <br>
     * Questa classe viene invocata PRIMA della chiamata del browser <br>
     * <p>
     * Nella sottoclasse che invoca questo metodo, aggiunge le @Route (view) specifiche dell' applicazione <br>
     * Le @Route vengono aggiunte ad una Lista statica mantenuta in BaseVar <br>
     * Verranno lette da MainLayout la prima volta che il browser 'chiama' una view <br>
     * Può essere sovrascritto, invocando PRIMA il metodo della superclasse <br>
     */
    @Override
    protected void fixRoutes() {
        super.fixRoutes();

        List<Class> listaViewsProject;
        String message;
        String viewName;
        String pathView="it.algos.task";

        if (Pref.usaMenuAutomatici.is()) {
            listaViewsProject = reflectionService.getSubClazz(CrudView.class,pathView);
            if (listaViewsProject != null) {
                for (Class clazz : listaViewsProject) {
                    if (annotationService.usaMenuAutomatico(clazz)) {
                        viewClazzListProject.add(clazz);
                        viewName = clazz.getSimpleName();
                        viewName = textService.levaCoda(viewName, SUFFIX_VIEW);
                        nameViewListProject.add(viewName);
                    }
                }
            }
            else {
                message = String.format("Non esiste nessuna view/route nel progetto [%s]", projectCurrent);
                logger.warn(new WrapLog().exception(new Exception(message)));
            }
        }
        else {
        }
    }


    protected void creaTask() {
//        TaskVar.taskList.add(appContext.getBean(TaskDownloadBioServer.class));
//        TaskVar.taskList.add(appContext.getBean(TaskDeleteBioServer.class));
//        TaskVar.taskList.add(appContext.getBean(TaskElaboraBioMongo.class));
//        TaskVar.taskList.add(appContext.getBean(TaskUploadMortiAnnoCorrente.class));
//        TaskVar.taskList.add(appContext.getBean(TaskUploadGiorni.class));
//        TaskVar.taskList.add(appContext.getBean(TaskUploadAnni.class));
    }


}



