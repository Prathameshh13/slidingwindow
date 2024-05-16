import java.util.Scanner;
import java.util.Random;

public class SlidingWindow {

    static final int WINDOW_SIZE = 4;  // Size of the window
    static final int TOTAL_PACKETS = 10;  // Total packets to send
    static final int TIMEOUT = 3000;  // Timeout duration in milliseconds

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int base = 0;
        int nextSeqNum = 0;

        while (base < TOTAL_PACKETS) {
            // Send packets within the window
            while (nextSeqNum < base + WINDOW_SIZE && nextSeqNum < TOTAL_PACKETS) {
                System.out.println("Sending packet " + nextSeqNum);
                nextSeqNum++;
            }

            // Simulate waiting for ACKs
            long startTime = System.currentTimeMillis();
            boolean timeout = false;

            while (System.currentTimeMillis() - startTime < TIMEOUT) {
                // Simulate ACK reception with a random success rate
                if (random.nextBoolean()) {
                    System.out.println("Received ACK for packet " + base);
                    base++;
                    if (base == nextSeqNum) {
                        break; // All packets in the window are acknowledged
                    }
                }
            }

            // Check for timeout
            if (System.currentTimeMillis() - startTime >= TIMEOUT) {
                System.out.println("Timeout occurred. Resending packets from " + base);
                nextSeqNum = base;
            }
        }

        System.out.println("All packets have been successfully sent and acknowledged.");
        scanner.close();
    }
}
