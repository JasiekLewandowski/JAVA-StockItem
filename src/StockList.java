import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new HashMap<>();
    }

    public int addItem (StockItem newItem){
        if (newItem != null){
            StockItem inStockItem = list.getOrDefault(newItem.getName(), newItem);
            if (inStockItem != newItem){
                newItem.adjustStock(inStockItem.quantityInStock());
            }
            list.put(newItem.getName(), newItem);
            return newItem.quantityInStock();
        }
        return 0;
    }
    public int sellStock (String item, int quantity){
        StockItem inStockItem = list.getOrDefault(item, null);
        if((inStockItem != null) && (inStockItem.quantityInStock() >= quantity) && (quantity >0)){
            inStockItem.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }
    public StockItem get(String key){
        return list.get(key);
    }
    public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()){
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();
            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            totalCost += itemValue;
        }
        return s + "Total stock value " + totalCost;
    }
}
