package it.algos.task.task;

import ch.carnet.kasparscherrer.IndeterminateCheckbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import it.algos.task.boot.TaskVar;
import it.algos.task.schedule.BaseTask;
import it.algos.vbase.backend.annotation.AList;
import it.algos.vbase.backend.boot.BaseCost;
import it.algos.vbase.backend.list.CrudList;
import it.algos.vbase.ui.wrapper.ASpan;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@SpringComponent
@Scope(value = SCOPE_PROTOTYPE)
@AList()
public class TaskList extends CrudList {

    @Inject
    TaskService taskService;

    static final String FIELD_NOME = "nome";

    static final String FIELD_FLAG = "flag";

    static final String FIELD_SERVER = "server";

    static final String FIELD_STATUS = "status";

    //--comboBox locale per selezionare la property nome
    ComboBox comboNome;

    //--searchField locale per selezionare la property flag
    TextField searchFlag;

    //--searchField locale per selezionare la property server
    TextField searchServer;

    //--checkBox locale per selezionare la property booleana
    IndeterminateCheckbox checkStatus;


    //--non utilizzato. Serve SOLO per evitare un bug di IntelliJIDEA che segnala errore.
    public TaskList() {
        super();
    }

    /**
     * @param parentCrudView che crea questa istanza
     */
    public TaskList(final TaskView parentCrudView) {
        super(parentCrudView);
    }

    protected void fixPreferenze() {
        super.fixPreferenze();

//        super.usaBottoneDeleteAll = true;
//        super.usaBottoneNew = false;
        super.usaSelettoreColonne = true;
        super.usaBottoneEdit = false;
        super.usaBottoneShow = true;
//        super.usaBottoneSearchField = false;
    }

    @PostConstruct
    private void init() {
        this.topPlaceHolder();
    }

    private void headerPlaceHolder() {
        addH(ASpan.text("La classe base è [Schedule.BaseSchedule]").verde());
        addH(ASpan.text("Ogni task ha una sua classe derivata dalla classe astratta [Schedule.BaseTask]").verde());
        addH(ASpan.text("Crea le istanze di task nel metodo della sottoclasse [xxxBoot.creaTask()]").verde());
        addH(ASpan.text("Le task vengono elencate nella variabile globale [BaseVar.taskList]").verde());
        addH(ASpan.text("Lancia l'esecuzione di tutte le task programmate creando l'istanza [Schedule.BaseSchedule]").verde());
        addH(ASpan.text("Può esserci un flag di preferenza acceso/spento per attivare la task. Se manca, la task è attiva di default").verde());
        addH(ASpan.text("Tavola ordinata per evento decrescente").verde());

        addH(ASpan.text(BaseCost.SPAZIO_NON_BREAKING));
        if (TaskVar.taskList == null || TaskVar.taskList.isEmpty()) {
            addH(ASpan.text("Non ci sono task previste per questa applicazione").blue());
        }
        else {
            addH(ASpan.text("Task previste per questa applicazione:").blue());
            for (BaseTask task : TaskVar.taskList) {
                addH(ASpan.text(task.infoFlag()).rosso());
            }
        }
    }

    private void topPlaceHolder() {
        //--creazione 'ad hoc' di un comboBox (semistandard) per selezionare la property nome
        comboNome = super.creaFiltroCombo(FIELD_NOME, taskService.taskCorrenti(), 12);

        //--creazione 'ad hoc' di un textSearch (semistandard) per selezionare il contenuto (non l'inizio) del testo della property -> flag
        searchFlag = super.creaFiltroText(FIELD_FLAG);

        //--creazione 'ad hoc' di un textSearch (semistandard) per selezionare il contenuto (non l'inizio) del testo della property -> server
        searchServer = super.creaFiltroText(FIELD_SERVER);

        //--creazione 'ad hoc' di un checkBox (semistandard) per selezionare la property booleana
        checkStatus = this.creaFiltroCheckBox(FIELD_STATUS);

    }


    @Override
    public void syncHeader() {
        this.headerPlaceHolder();
    }


    @Override
    protected void syncFiltri() {
        //--filtraggio del database in funzione del valore della property
        super.filtroCombo(comboNome, FIELD_NOME);

        //--filtraggio del database in funzione del valore della property (contenuto del testo e non inizio)
        super.filtroContenutoText(searchFlag, FIELD_FLAG);

        //--filtraggio del database in funzione del valore della property
        super.filtroContenutoText(searchServer, FIELD_SERVER);

        //--filtraggio del database in funzione del valore della property
        super.filtroCheckBox(checkStatus, FIELD_STATUS);
    }


}// end of CrudList class
