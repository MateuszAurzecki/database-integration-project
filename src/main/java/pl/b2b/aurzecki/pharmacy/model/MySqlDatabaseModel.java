package pl.b2b.aurzecki.pharmacy.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SqlDatabase {

    private Long ident;
    private String nazwa;
    private Long ministerstwo;

}
