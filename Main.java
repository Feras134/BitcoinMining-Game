
public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(4); // Set difficulty level

        // Add blocks
        blockchain.addBlock("Transaction 1");
        blockchain.addBlock("Transaction 2");
        blockchain.addBlock("Transaction 3");

        // Display the blockchain
        System.out.println("\nBlockchain:");
        blockchain.displayChain();

        // Verify blockchain integrity
        System.out.println("Blockchain verification: " + (blockchain.verifyChain() ? "Valid" : "Invalid"));
    }
}
