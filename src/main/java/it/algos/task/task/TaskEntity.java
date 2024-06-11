package it.algos.task.task;

import it.algos.vbase.backend.annotation.AEntity;
import it.algos.vbase.backend.annotation.AFieldList;
import it.algos.vbase.backend.entity.AbstractEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "task")
@AEntity(sort = "evento", sortDiscendente = true)
public class TaskEntity extends AbstractEntity {

    @AFieldList(width = 10)
    public String nome;

    @AFieldList(width = 16)
    public String flag;

    @AFieldList(width = 8)
    public String schedule;

    /**
     * Data dell'evento (obbligatoria, non modificabile)
     */
    @AFieldList(width = 10)
//    @AField(type = TypeField.localDateTime, typeDate = TypeDate.normaleOrario)
    private LocalDateTime evento;

    @AFieldList(width = 6)
    public String server;

    @AFieldList()
//    @AField(type = TypeField.booleano)
    public boolean status;

    @AFieldList(width = 24)
    public String descrizione;

    @AFieldList(visible = false)
//    @AField(type = TypeField.textArea)
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
