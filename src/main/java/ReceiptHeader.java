import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class ReceiptHeader {
    private LocalDateTime dateTime;
    private String storeNumber;
    private String cashRegisterNumber;
    private double totalAmount;

    public ReceiptHeader(LocalDateTime now, String storeNumber, String cashRegisterNumber, double totalAmount) {
    }

    // Асинхронный метод для сохранения данных чека в файловую систему
    public void saveToFile(String filePath) throws IOException {
        try {
            String dataToSave = String.format("{\"dateTime\":\"%s\",\"storeNumber\":\"%s\",\"cashRegisterNumber\":\"%s\",\"totalAmount\":%.2f}",
                    dateTime, storeNumber, cashRegisterNumber, totalAmount);

            Files.write(Path.of(filePath), dataToSave.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("Receipt data saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving receipt data to " + filePath + ": " + e.getMessage());
            throw e; // Перебрасываем исключение для обработки в вызывающем коде
        }
    }
}
