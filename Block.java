
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Block {
    private int index;
    private String previousHash;
    private String data;
    private long timestamp;
    private String hash;
    private int nonce;
    private long miningTime;
    public Block next; // Pointer to the next block (Linked List)

    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = Instant.now().getEpochSecond();
        this.nonce = 0;
        this.hash = calculateHash();
        this.miningTime = 0;
        this.next = null; // Default, no next block yet
    }

    public String calculateHash() {
        String input = index + previousHash + data + timestamp + nonce;
        return applySHA256(input);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        long startTime = System.currentTimeMillis();

        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }

        long endTime = System.currentTimeMillis();
        miningTime = endTime - startTime;
        System.out.println("Block " + index + " mined in " + miningTime + " ms: " + hash);
    }

    private String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Getters
    public int getIndex() { 
    	return index; 
    	}
    public String getPreviousHash() {
    	return previousHash;
    	}
    public String getData() { 
    	return data; 
    	}
    public long getTimestamp() { 
    	return timestamp; 
    	}
    public String getHash() {
    	return hash; 
    	}
    public long getMiningTime() { 
    	return miningTime;
    	}
}

