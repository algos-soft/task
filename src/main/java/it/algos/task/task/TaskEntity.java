package it.algos.task.task;

import it.algos.vbase.backend.annotation.*;
import it.algos.vbase.backend.entity.*;
import it.algos.vbase.backend.enumeration.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "task")
@AEntity(sortPropertyName = "evento", sortDiscendente = true)
public class TaskEntity extends AbstractEntity {

    @AField(type = TypeField.text, widthList = 10)
    public String nome;

    @AField(type = TypeField.text, widthList = 16)
    public String flag;

    @AField(type = TypeField.text, widthList = 8)
    public String schedule;

    /**
     * Data dell'evento (obbligatoria, non modificabile)
     */
    @AField(type = TypeField.localDateTime, typeDate = TypeDate.normaleOrario, widthList = 10)
    private LocalDateTime evento;

    @AField(type = TypeField.text, widthList = 6)
    public String server;

    @AField(type = TypeField.booleano)
    public boolean status;

    @AField(type = TypeField.text, widthList = 24)
    public String descrizione;

    @AField(type = TypeField.textArea, visibileList = false)
    public String risultato;

    @Override
    public String toString() {
        return nome;
    }

    public static TaskEntity newEntity(String nome, String flag, String schedule, LocalDateTime evento, String server, String descrizione, String risultato) {
        TaskEntity newEntityBean = TaskEntity.builder()
                .nome( nome )
                .flag(flag )
                .schedule(schedule )
                .evento(evento)
                .server(server )
                .descrizione(descrizione)
                .risultato( risultato )
                .build();

        return newEntityBean;
    }

}// end of Entity class
