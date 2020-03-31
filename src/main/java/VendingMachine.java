import java.util.List;
import java.util.Optional;

public interface VendingMachine {

    /**
     * Select a product.
     * @return Tuple with the product and change.
     */
    Optional<Tuple> order(Integer bagNumber);

    /**
     * Insert Coin.
     * @param coin to insert
     */
    void insertCoin(Coin coin);

    /**
     * Initialize the vending machine with a product.
     * @param product The product to add.
     * @param shelfNum Shelf to select product from
     */
    void addProduct(Product product, Integer shelfNum);


    default VendingMachine create() {
        return new VendingMachineImpl();
    }

    /**
     * Cancel after inserting coins.
     * @return inserted coins
     */
    List<Coin> cancel();

    /**
     * Reset state.
     */
    void reset();

    /**
     * Init with products
     */
    void init();


}
