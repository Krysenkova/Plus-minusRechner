public class Operation {
    String sign;
    int number;

    public Operation(String sign, int number) {
        this.sign = sign;
        this.number = number;
    }

    public String getSign() {
        return sign;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return sign + " " + number;
    }
}
