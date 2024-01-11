import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReceiptItem {
    private String productId;
    private int quantity;
    private double unitPrice;
    private double totalAmount;

    public ReceiptItem(String productId, int quantity, double unitPrice, double totalAmount) {
    }

    // Асинхронный метод для сохранения данных позиции чека в файловую систему
    public void saveToFile(String filePath) throws IOException {
        try {
            String dataToSave = String.format("{\"productId\":\"%s\",\"quantity\":%d,\"unitPrice\":%.2f,\"totalAmount\":%.2f}",
                    productId, quantity, unitPrice, totalAmount);

            Files.write(Path.of(filePath), dataToSave.getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.WRITE);
            System.out.println("Receipt item data saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving receipt item data to " + filePath + ": " + e.getMessage());
            throw e; // Перебрасываем исключение для обработки в вызывающем коде
        }
    }
}

