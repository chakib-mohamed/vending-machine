import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Builder
    @Data
    class Tuple {
        private Product product;
        private Optional<List<Coin>> coins;
    }