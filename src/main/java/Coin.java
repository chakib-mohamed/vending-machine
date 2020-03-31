import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum Coin {

    TWENTY_CENT(20d), FIFTY_CENT(50d), TEN_CENT(10d);

    @Getter
    @Setter
    private Double value;
}
