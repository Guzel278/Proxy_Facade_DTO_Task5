import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    @Test
    public void testSaveReceiptHeaderToFile() {
        ReceiptHeader receiptHeader = new ReceiptHeader(LocalDateTime.now(), "123", "456", 100.0);
        String filePath = "test_receipt_header.json";

        try {
            receiptHeader.saveToFile(filePath);
            assertTrue(Files.exists(Path.of(filePath)));
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Clean up: Delete the test file
            deleteTestFile(filePath);
        }
    }

    @Test
    public void testSaveReceiptItemToFile() {
        ReceiptItem receiptItem = new ReceiptItem("ABC123", 2, 10.0, 20.0);
        String filePath = "test_receipt_item.json";

        try {
            receiptItem.saveToFile(filePath);
            assertTrue(Files.exists(Path.of(filePath)));
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        } finally {
            // Clean up: Delete the test file
            deleteTestFile(filePath);
        }
    }

    @Test
    public void testReceiptFacade() {
        ReceiptHeader receiptHeader = new ReceiptHeader(LocalDateTime.now(), "123", "456", 100.0);
        List<ReceiptItem> receiptItems = List.of(
                new ReceiptItem("ABC123", 2, 10.0, 20.0),
                new ReceiptItem("XYZ789", 3, 15.0, 45.0)
        );

        ReceiptFacade receiptFacade = new ReceiptFacade(receiptHeader, receiptItems);

        // Test getReceipt method
        ReceiptFacade.ReceiptData receiptData = receiptFacade.getReceipt();
        assertNotNull(receiptData);
        assertEquals(receiptHeader, receiptData.getHeader());
        assertEquals(receiptItems, receiptData.getItems());

        // Test addItem method
        ReceiptItem newItem = new ReceiptItem("123XYZ", 1, 5.0, 5.0);
        receiptFacade.addItem(newItem);
        assertEquals(3, receiptFacade.getReceipt().getItems().size());
        assertTrue(receiptFacade.getReceipt().getItems().contains(newItem));

        // Test removeItem method
        receiptFacade.removeItem(0);
        assertEquals(2, receiptFacade.getReceipt().getItems().size());
    }

    private void deleteTestFile(String filePath) {
        try {
            Files.deleteIfExists(Path.of(filePath));
        } catch (IOException e) {
            fail("Exception thrown while deleting test file: " + e.getMessage());
        }
    }
}

