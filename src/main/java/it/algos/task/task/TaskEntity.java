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

    @AFieldList(width = 10)
    @AField(type = TypeField.text)
    public String nome;

    @AFieldList(width = 16)
    @AField(type = TypeField.text)
    public String flag;

    @AFieldList(width = 8)
    @AField(type = TypeField.text)
    public String schedule;

    /**
     * Data dell'evento (obbligatoria, non modificabile)
     */
    @AFieldList(width = 10)
    @AField(type = TypeField.localDateTime, typeDate = TypeDate.normaleOrario)
    private LocalDateTime evento;

    @AFieldList(width = 6)
    @AField(type = TypeField.text)
    public String server;

    @AField(type = TypeField.booleano)
    public boolean status;

    @AFieldList(width = 24)
    @AField(type = TypeField.text)
    public String descrizione;

    @AFieldList(visibile = false)
    @AField(type = TypeField.textArea)
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
