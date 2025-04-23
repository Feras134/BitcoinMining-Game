// This is a program for the second homework of CSC489. Written by 	Feras Alnehabi ID:443101183
// No requirements to run the program except having JDK (Java development kit)
public class Blockchain {
    private Block head; // The first block (Genesis Block)
    private Block tail; // The latest block (Last node in the linked list)
    private int difficulty;

    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        head = createGenesisBlock();
        tail = head; // Genesis block is also the last block initially
    }

    private Block createGenesisBlock() {
        return new Block(0, "0", "Genesis Block");
    }

    public void addBlock(String data) {
        Block newBlock = new Block(tail.getIndex() + 1, tail.getHash(), data);
        newBlock.mineBlock(difficulty);
        
        // Link the new block to the chain
        tail.next = newBlock;
        tail = newBlock; // Update tail to the newly added block
    }

    public boolean verifyChain() {
        Block current = head;
        while (current != null && current.next != null) {
            if (!current.next.getPreviousHash().equals(current.getHash())) {
                return false;
            }
            if (!current.next.getHash().equals(current.next.calculateHash())) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public void displayChain() {
        Block current = head;
        while (current != null) {
            System.out.println("Block " + current.getIndex() + ":");
            System.out.println("  Data: " + current.getData());
            System.out.println("  Previous Hash: " + current.getPreviousHash());
            System.out.println("  Hash: " + current.getHash());
            System.out.println("  Mining Time: " + current.getMiningTime() + " ms");
            System.out.println("----------------------------------");
            current = current.next;
        }
    }
}

