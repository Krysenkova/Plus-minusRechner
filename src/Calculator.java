import java.io.*;
import java.util.ArrayList;

public class Calculator implements ICalculator {

    int memory = 0;
    ArrayList<Operation> operations = new ArrayList<>();

    String filename = "operations.txt";
    String memoryfile = "memory.txt";
    //for writing into a file
    private OutputStream os = new FileOutputStream(filename, true);
    private DataOutputStream dos = new DataOutputStream(os);
    private OutputStream os2 = new FileOutputStream(memoryfile, true);
    private DataOutputStream dos2 = new DataOutputStream(os2);

    //for reading from file
    private InputStream is = new FileInputStream(filename);
    private DataInputStream dis = new DataInputStream(is);
    private InputStream is2 = new FileInputStream(memoryfile);
    private DataInputStream dis2 = new DataInputStream(is2);


    public Calculator() throws FileNotFoundException {

    }

    public Operation saveOperation(String newOperation) {
        Operation operation = divide(newOperation);
        operations.add(operation);
        return operation;
    }

    @Override
    public void addNumber(String newOperation) {
        Operation operation = saveOperation(newOperation);
        memory = memory + operation.getNumber();
        printTotal();
    }

    @Override
    public void subtractNumber(String newOperation) {
        Operation operation = saveOperation(newOperation);
        memory = memory - operation.getNumber();
        printTotal();
    }

    public Operation divide(String stringToDivide) {
        String workingString = stringToDivide.replaceAll("\\s", "");
        String[] parts = workingString.split("(?<=\\D)(?=\\d)");
        String newSign = parts[0];
        int newNumber = Integer.parseInt(parts[1]);
        return new Operation(newSign, newNumber);

    }

    public int getMemory() {
        return memory;
    }

    @Override
    public void doOperationBack() {
        Operation toGoBack = operations.get(operations.size() - 1);
        if (toGoBack.getSign().equals("+")) {
            memory = memory - toGoBack.getNumber();
        } else{
            memory = memory + toGoBack.getNumber();}
        operations.remove(operations.size() - 1);
        printTotal();
    }

    public void printTotal() {
        System.out.println("Total: " + memory);
    }

    public void saveAllOperations() throws IOException {
        new FileOutputStream(filename).close();
        new FileOutputStream(memoryfile).close();
        dos2.writeInt(memory);
        dos.writeInt(operations.size());
        for (Operation operation : operations) {
            dos.writeUTF(operation.getSign());
            dos.writeInt(operation.getNumber());
        }
    }

    public boolean checkIfFileIsEmpty() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        return br.readLine() == null;
    }

    public void fillOperations() throws IOException {
        memory = dis2.readInt();
        int numberOfOperations = dis.readInt();
        for (int i = 0; i < numberOfOperations; i++) {
            String oldSign = dis.readUTF();
            int oldNumber = dis.readInt();
            var oldOperation = new Operation(oldSign, oldNumber);
            operations.add(oldOperation);
        }
    }
}
