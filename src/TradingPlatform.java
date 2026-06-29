import java.io.*;
import java.util.*;

public class TradingPlatform {
    private Map<String, Double> market = new HashMap<>();
    private Map<String, Integer> portfolio = new HashMap<>();
    private List<Transaction> history = new ArrayList<>();
    private double balance = 10000.0;

    public TradingPlatform() {
        market.put("AAPL", 150.0); market.put("GOOG", 2800.0); market.put("TSLA", 700.0);
    }

    public void trade(String symbol, int qty, boolean isBuy) {
        if (!market.containsKey(symbol)) { System.out.println("Invalid Symbol."); return; }
        double price = market.get(symbol);

        if (isBuy && balance >= qty * price) {
            balance -= (qty * price);
            portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + qty);
            history.add(new Transaction(symbol, qty, price, "BUY"));
        } else if (!isBuy && portfolio.getOrDefault(symbol, 0) >= qty) {
            balance += (qty * price);
            portfolio.put(symbol, portfolio.get(symbol) - qty);
            history.add(new Transaction(symbol, qty, price, "SELL"));
        } else {
            System.out.println("Transaction Denied: Insufficient funds or shares.");
        }
    }

    public void savePortfolio() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("portfolio.txt"));
        writer.println("Balance: " + balance);
        for (String sym : portfolio.keySet()) writer.println(sym + ":" + portfolio.get(sym));
        writer.close();
        System.out.println("Portfolio saved to portfolio.txt");
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        Scanner sc = new Scanner(System.in);
        // Menu loop logic here...
        System.out.println("Trading Platform Ready.");
        platform.trade("AAPL", 2, true); // Example transaction
        try { platform.savePortfolio(); } catch (Exception e) { e.printStackTrace(); }
    }
}