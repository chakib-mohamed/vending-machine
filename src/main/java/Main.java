public class Main {

    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachineImpl();
        vendingMachine.init();

        vendingMachine.insertCoin(Coin.FIFTY_CENT);
        vendingMachine.insertCoin(Coin.FIFTY_CENT);
        vendingMachine.insertCoin(Coin.FIFTY_CENT);
        vendingMachine.insertCoin(Coin.FIFTY_CENT);
        vendingMachine.insertCoin(Coin.FIFTY_CENT);
        vendingMachine.insertCoin(Coin.FIFTY_CENT);

        System.out.println(vendingMachine.order(2));

        System.out.println(vendingMachine);

    }
}
