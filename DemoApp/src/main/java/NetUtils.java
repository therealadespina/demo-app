import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NetUtils {
    public List<Stock> getStocks() {
        String url = "https://raw.githubusercontent.com/therealadespina/testCsv/main/biddingProcess.csv";
        String urlEmpty = "https://raw.githubusercontent.com/therealadespina/testCsv/main/biddingProcessEmpty.csv";

        try (BufferedInputStream is = new BufferedInputStream(new URL(url).openStream())){
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            String result = scanner.hasNext() ? scanner.next() : "";
            return convertCsvToStocks(result);
        } catch (Exception e) {
            System.out.println("Unable to get data from the Api " + e);
        }
        return Collections.emptyList();
    }

    public List<Stock> convertCsvToStocks(String inputData) {
        List<Stock> stocks = new ArrayList<>();
        String[] lines = inputData.split("\n");
        for (int i = 1; i < lines.length; i++) {
            stocks.add(convertLineToStock(lines[i]));
        }
        return stocks;
    }

    public Stock convertLineToStock(String line) {
        String[] tokens = line.split(",");
        return new Stock(tokens[0], Float.parseFloat(tokens[4]));
    }
}
