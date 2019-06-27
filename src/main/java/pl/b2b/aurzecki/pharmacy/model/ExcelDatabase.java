package pl.b2b.aurzecki.pharmacy.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ExcelDatabase {

    private Long lp;
    private String nazwa;
    private Long id_w_ministerstwie;
}
