import lombok.ToString;

import java.util.*;

@ToString
public class VendingMachineImpl implements VendingMachine {

    Map<Integer, Stack<Product>> shelfs;

    {
        shelfs = new HashMap<>();
        shelfs.put(1, new Stack<>());
        shelfs.put(2, new Stack<>());
        shelfs.put(3, new Stack<>());
        shelfs.put(4, new Stack<>());
        shelfs.put(5, new Stack<>());

    }

    private List<Coin> currentBalance = new ArrayList<>();
    private List<Coin> insertedCoins = new ArrayList<>();

    private Double getCoinsValue() {
        return this.insertedCoins.stream().map(Coin::getValue).reduce(Double::sum).orElse(0d);
    }

    @Override
    public Optional<Tuple> order(Integer shelfNumber) {
        if (!shelfs.containsKey(shelfNumber)) {
            System.err.println("Error : shelf doesnt exists");
            return Optional.empty();
        }

        if (shelfs.get(shelfNumber).isEmpty()) {
            System.err.println("Error : NO product available");
            return Optional.empty();
        }

        if (this.insertedCoins == null) {
            System.err.println("Error : No money, please enter coins");
            return Optional.empty();
        }

        if (shelfs.get(shelfNumber).peek().getPrice() > this.getCoinsValue()) {
            System.err.println("Error : please enter correct price " + shelfs.get(shelfNumber).peek().getPrice());
            return Optional.empty();
        } else if (shelfs.get(shelfNumber).peek().getPrice() <= this.getCoinsValue()) {

            List<Coin> change = coinFromChange(this.getCoinsValue() - shelfs.get(shelfNumber).peek().getPrice());

            // this.deductBalance(change);
            this.currentBalance.addAll(this.insertedCoins);
            this.insertedCoins.clear();
            return Optional.of(Tuple.builder().product(shelfs.get(shelfNumber).pop()).coins(Optional.of(change)).build());
        }


        return Optional.empty();
    }

    private List<Coin> coinFromChange(Double change) {
        return this.coinFromChange(change, null);
    }

    private List<Coin> coinFromChange(Double change, List<Coin> coins) {

        if (coins == null) {
            coins = new ArrayList<>();
        }
        if (change == 0d) {
            return coins;
        }

        for (Coin coin : Coin.values()) {
            if (change - coin.getValue() >= 0 && this.currentBalance.contains(coin)) {
                coins.add(coin);
                this.deductBalance(Collections.singletonList(coin));
                return coinFromChange(change - coin.getValue(), coins);
            }
        }

        System.out.println("Error : Not  enough change coins available");
        this.currentBalance.addAll(coins); // RollBack
        return new ArrayList<>();


    }

    /**
     * Deduct change from current balance
     * @param coin
     */
    private void deductBalance(List<Coin> coin) {

        coin.forEach(
                c -> {
                    Iterator<Coin> iterator = this.currentBalance.iterator();
                    while (iterator.hasNext()) {
                        Coin e = iterator.next();
                        if (e == c) {
                            iterator.remove();
                            break;
                        }
                    }
                }
        );

    }


    @Override
    public void insertCoin(Coin coin) {
        this.insertedCoins.add(coin);
    }

    @Override
    public void addProduct(Product p, Integer shelfNum) {
        if (!shelfs.containsKey(shelfNum)) {
            var listOfProducts = new Stack<Product>();
            listOfProducts.add(p);
            shelfs.put(shelfNum, listOfProducts);
        } else {
            shelfs.get(shelfNum).add(p);
        }


    }

    @Override
    public List<Coin> cancel() {
        return this.insertedCoins;
    }

    @Override
    public void reset() {
        this.insertedCoins.clear();
        this.shelfs.clear();
    }

    @Override
    public void init() {
        this.addProduct(new BareChocolat(), 1);
        this.addProduct(new BareChocolat(), 1);

        this.addProduct(new Drink(), 2);
        this.addProduct(new Drink(), 2);
        this.addProduct(new Drink(), 2);
        this.addProduct(new Drink(), 2);
        this.addProduct(new Drink(), 2);

        this.currentBalance = new ArrayList<>();

        currentBalance.addAll(List.of(Coin.FIFTY_CENT, Coin.TWENTY_CENT, Coin.FIFTY_CENT, Coin.TWENTY_CENT, Coin.FIFTY_CENT,
                Coin.TWENTY_CENT, Coin.FIFTY_CENT, Coin.TWENTY_CENT, Coin.TEN_CENT, Coin.TEN_CENT));

        System.out.println(this);
    }


}
