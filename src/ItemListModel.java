import javax.swing.*;
import java.util.ArrayList;

public class ItemListModel extends DefaultListModel<Item> {

    private ArrayList<Item> itemArrayList;

    public ItemListModel() {
        itemArrayList = new ArrayList<>();
        itemArrayList.addAll(Item.getAllItemsList());
    }

    @Override
    public void addElement(Item element) {
        itemArrayList.add( element );
    }

    public int getIndexOf(Item item){
        return itemArrayList.indexOf(item);
    }

    @Override
    public int getSize() {
        return itemArrayList.size();
    }

    @Override
    public Item getElementAt(int i) {
        if(itemArrayList.size() > 0)
            return itemArrayList.get(i);
        return null;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    @Override
    public void removeElementAt(int index) {
        if(index < itemArrayList.size())
            itemArrayList.remove(index);
    }

    public void setItemArrayList(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }
}
