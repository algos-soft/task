package it.algos.task.boot;

import it.algos.vbase.backend.boot.*;
import org.springframework.stereotype.*;

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



    protected void creaTask() {
//        TaskVar.taskList.add(appContext.getBean(TaskDownloadBioServer.class));
//        TaskVar.taskList.add(appContext.getBean(TaskDeleteBioServer.class));
//        TaskVar.taskList.add(appContext.getBean(TaskElaboraBioMongo.class));
//        TaskVar.taskList.add(appContext.getBean(TaskUploadMortiAnnoCorrente.class));
//        TaskVar.taskList.add(appContext.getBean(TaskUploadGiorni.class));
//        TaskVar.taskList.add(appContext.getBean(TaskUploadAnni.class));
    }


}



