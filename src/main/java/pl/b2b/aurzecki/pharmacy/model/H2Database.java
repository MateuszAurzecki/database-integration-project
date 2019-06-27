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
public class H2Database {

    private Long identyfikator;
    private String nazwaLeku;
    private Long min;
}
