package pl.b2b.aurzecki.pharmacy.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MainDatabaseModel {

    private Long id;
    private String name;
    private Long governmentNumber;

}
