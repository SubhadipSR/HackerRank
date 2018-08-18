import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Oleg Cherednik
 * @since 18.08.2018
 */
public class Solution {
    public static double normalizeMean(int[][] stocks, Set<Double> means) {
        double res = Double.MAX_VALUE;

        for (double mean : means) {
            double cur = diffSum(stocks, mean);

            if (cur > res)
                return res;

            res = cur;
        }

        return res;
    }

    private static double diffSum(int[][] stocks, double mean) {
        double res = 0;

        for (int[] stock : stocks)
            for (int price : stock)
                res += Math.abs(price - mean);

        return res;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(in.readLine());
            int[][] stocks = new int[n][];
            Set<Double> means = new TreeSet<>();

            for (int i = 0, start = 0; i < n; i++, start = 0) {
                int m = Integer.parseInt(in.readLine());
                String str = in.readLine();

                stocks[i] = new int[m];
                long sum = 0;

                for (int j = 0; j < m; j++) {
                    int end = str.indexOf(' ', start);
                    int price = Integer.parseInt(str.substring(start, end < 0 ? str.length() : end));
                    stocks[i][j] = price;
                    sum += price;
                    start = end + 1;
                }

                means.add((double)sum / m);
            }

            System.out.format(Locale.US, "%.12f", normalizeMean(stocks, means));
        }
    }

}