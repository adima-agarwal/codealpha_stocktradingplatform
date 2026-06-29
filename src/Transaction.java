public class Transaction {
    String symbol; int quantity; double price; String type;

    public Transaction(String symbol, int quantity, double price, String type) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }
    @Override
    public String toString() {
        return type + " " + quantity + " " + symbol + " @ $" + price;
    }
}