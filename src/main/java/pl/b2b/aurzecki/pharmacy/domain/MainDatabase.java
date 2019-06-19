package pl.b2b.aurzecki.pharmacy.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MainDatabase {

    private Long id;
    private String name;
    private Long governmentNumber;

}
