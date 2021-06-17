import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

@RunWith(MockitoJUnitRunner.class)
public class NetUtilsTest {

    @Test
    @DisplayName("Ожидается: List<Stock>, не пустой")
    public void getStocksTest() throws IOException {
        NetUtils netUtils = new NetUtils();
        String url = "https://raw.githubusercontent.com/therealadespina/testCsv/main/biddingProcess.csv";

        BufferedInputStream is = new BufferedInputStream(new URL(url).openStream());
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";
        Assertions.assertEquals(netUtils.convertCsvToStocks(result), netUtils.getStocks());
        Assertions.assertNotNull(netUtils.getStocks());
    }

    @Test
    @DisplayName("Ожидается: IndexOutOfBoundsException")
    public void convertCsvToStocksExceptionTest() {
        NetUtils netUtils = new NetUtils();
        String emptyString = null;
        Assertions.assertThrows(NullPointerException.class, () ->
                netUtils.convertCsvToStocks(emptyString));
    }

    @Test
    @DisplayName("Не ожидается: Пустая строка")
    public void convertLineToStockTest() {
        NetUtils netUtils = new NetUtils();
        String[] tokens;
        String line = "";
        String line2 = "2020-10-05,122.3600,123.3000,118.8200,122.0100,9187838";
        Assertions.assertNotEquals(tokens = line.split(","), tokens = line2.split(","));
    }
}