import java.util.ArrayList;
import java.util.List;

public class ReceiptFacade {
    private final ReceiptHeader receiptHeader;
    private final List<ReceiptItem> receiptItems;

    public ReceiptFacade(ReceiptHeader receiptHeader, List<ReceiptItem> receiptItems) {
        this.receiptHeader = receiptHeader;
        this.receiptItems = new ArrayList<>(receiptItems);
    }

    // Метод для чтения чека с позициями
    public ReceiptData getReceipt() {
        return new ReceiptData(receiptHeader, new ArrayList<>(receiptItems));
    }

    // Метод для добавления позиции в чек
    public void addItem(ReceiptItem item) {
        receiptItems.add(item);
    }

    // Метод для удаления позиции из чека
    public void removeItem(int index) {
        if (index >= 0 && index < receiptItems.size()) {
            receiptItems.remove(index);
        }
    }

    // Вспомогательный класс для обертки данных чека
    public static class ReceiptData {
        private ReceiptHeader header;
        private List<ReceiptItem> items;

        // Конструктор
        public ReceiptData(ReceiptHeader header, List<ReceiptItem> items) {
            this.header = header;
            this.items = items;
        }

        // Геттеры
        public ReceiptHeader getHeader() {
            return header;
        }

        public List<ReceiptItem> getItems() {
            return items;
        }
    }
}
