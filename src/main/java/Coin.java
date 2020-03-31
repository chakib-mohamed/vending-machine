import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum Coin {

    FIFTY_CENT(50d), TWENTY_CENT(20d) , TEN_CENT(10d);

    @Getter
    @Setter
    private Double value;
}
