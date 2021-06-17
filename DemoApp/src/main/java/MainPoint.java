import java.util.List;

public class MainPoint {
    public static void main(String[] args) {
        NetUtils netUtils = new NetUtils();

        List<Stock> stocks = netUtils.getStocks();

        stocks.sort((o1, o2) -> {
            Float val1 = o1.getValue();
            Float val2 = o2.getValue();
            return val1.compareTo(val2);
        });

        System.out.println("Самая дешевая акция была " + stocks.get(0).getDate() + " с ценой " + stocks.get(0).getValue());
        System.out.println("Самая дорогая акция была " + stocks.get(stocks.size() - 1).getDate() + " с ценой " + stocks.get(stocks.size() - 1).getValue());
    }
}
