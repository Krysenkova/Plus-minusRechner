public interface ICalculator {

    /**
     * adds number to number in memory
     */
    void addNumber(String newOperation);

    /**
     * subtracts number from number in memory
     */
    void subtractNumber(String newOperation);

    /**
     * sets a memory value to previous step
     */
    void doOperationBack();
}
