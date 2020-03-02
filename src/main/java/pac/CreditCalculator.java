package pac;

public class CreditCalculator {

    private static final int CREDIT_SUM = 85000;
    private static final double PERCENT = 0.12;
    private static final int MONTHLY_PAYMENT = 2800;

    private static final int YEARS = 20;
    private static final int CONSTANT_MONTHLY_PAYMENT_PART = CREDIT_SUM / (YEARS * 12);

    public static void main(String[] args) {
        int months = calculate(CREDIT_SUM, 1);
        print("Минимальная месячная выплата по основному долгу: %d", CONSTANT_MONTHLY_PAYMENT_PART);
        print("Количество месяцев на выплату: %d", months);
        print("Общая уплаченная сумма: %d", MONTHLY_PAYMENT * months);
    }

    private static int calculate(double currentBalance, int months) {
        double percentMonthlyPaymentPart = currentBalance * PERCENT / (12);

        currentBalance = currentBalance - (MONTHLY_PAYMENT - percentMonthlyPaymentPart);

        print("Месяц: %d, "
              + "Выплаты по процентам: %d$, "
              + "Выплаты по основному долгу: %d$, "
              + "Осталось выплатить: %d$",
            months,
            (int) percentMonthlyPaymentPart,
            MONTHLY_PAYMENT - (int) percentMonthlyPaymentPart,
            (int) currentBalance);

        if (currentBalance <= 0) {
            print("Переплата: %d", (int) currentBalance);
            return months;
        } else {
            return calculate(currentBalance, ++months);
        }
    }

    private static void print(String text, Object... args) {
        System.out.println(String.format(text, args));
    }
}
