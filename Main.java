import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        try {
            double purchasePrice = Double.parseDouble(in.readLine());
            double cash = Double.parseDouble(in.readLine());
            Main.calculateChange(purchasePrice, cash);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // A class of coins/notes which holds their value and name:
    public static class MoneyType{
        public BigDecimal MoneyAmount;
        public String MoneyName;

        public MoneyType(BigDecimal MoneyAmount, String MoneyName){
            this.MoneyAmount = MoneyAmount;
            this.MoneyName = MoneyName;
        }

        public BigDecimal getMoneyAmount(){
            return MoneyAmount;
        }

        public String getMoneyName() {
            return MoneyName;
        }
    }

    public static void calculateChange(double purchasePrice, double cash) {
        // Convert to string to input as a BigDecimal as this is generally
        // preferred when dealing with money and converting to BigDecimal:
        String purchasePriceStr = Double.toString(purchasePrice);
        String cashStr = Double.toString(cash);
        BigDecimal purchasePriceBD = new BigDecimal(purchasePriceStr);
        BigDecimal cashBD = new BigDecimal(cashStr);
        BigDecimal change = cashBD.subtract(purchasePriceBD);

        // Check if not enough cash or perfect amount:
        BigDecimal zero = new BigDecimal("0");
        if(change.compareTo(zero) == -1){
            System.out.println("ERROR");
        }
        else if(change.compareTo(zero) == 0){
            System.out.println("Zero");
        }

        // Otherwise calculate change to be given:
        else{
            // Create an array for all of our cash types here:
            MoneyType[] register = {
                    new MoneyType(new BigDecimal("50"), "Fifty Pounds"),
                    new MoneyType(new BigDecimal("20"), "Twenty Pounds"),
                    new MoneyType(new BigDecimal("10"), "Ten Pounds"),
                    new MoneyType(new BigDecimal("5"), "Five Pounds"),
                    new MoneyType(new BigDecimal("2"), "Two Pounds"),
                    new MoneyType(new BigDecimal("1"), "One Pound"),
                    new MoneyType(new BigDecimal("0.50"), "Fifty Pence"),
                    new MoneyType(new BigDecimal("0.20"), "Twenty Pence"),
                    new MoneyType(new BigDecimal("0.10"), "Ten Pence"),
                    new MoneyType(new BigDecimal("0.05"), "Five Pence"),
                    new MoneyType(new BigDecimal("0.02"), "Two Pence"),
                    new MoneyType(new BigDecimal("0.01"), "One Pence")
            };

            // Simple loop iterating through the array and change to be given:
            int count = 0;
            StringBuilder output = new StringBuilder();
            while(change.compareTo(zero) == 1){
                while(change.compareTo(register[count].getMoneyAmount()) == 1){
                    change = change.subtract(register[count].getMoneyAmount());
                    output.append(register[count].getMoneyName());
                    output.append(", ");
                }
                if(change.compareTo(register[count].getMoneyAmount()) == 0){
                    change = change.subtract(register[count].getMoneyAmount());
                    output.append(register[count].getMoneyName());
                }
                else {
                    count += 1;
                }
            }
            System.out.println(output);
        }
    }
}
