import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class StoreTableListModel implements TableModel {

    ArrayList<Store> storeTableArrayList;

    public StoreTableListModel(){
        storeTableArrayList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void addStore(Store s){
        storeTableArrayList.add(s);
        inform();
    }

    public int getSize(){
        return storeTableArrayList.size();
    }

    public Store getStore(int i){
        return storeTableArrayList.get(i);
    }

    public int getIndexOf(Store s){
        return storeTableArrayList.indexOf(s);
    }

    public boolean addItemToStore(int index, Item t){
        Store store = storeTableArrayList.get(index);
        boolean result = store.addItem(t);
        if(result){
            storeTableArrayList.set(index, store);
            inform();
        }
        return result;
    }

    public void setStoreTableArrayList(ArrayList<Store> stores){
        storeTableArrayList = stores;
        inform();
    }

    @Override
    public int getRowCount() {
        return storeTableArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int i) {
        switch (i){
            case 0:
                return "Obrazek";
            case 1:
                return "Nr Magazynu";
            case 2:
                return "Lokalizacja";
            case 3:
                return "Zapelnienie (Graficznie)";
            case 4:
                return "Zapelnienie (Procentowo)";
            default:
                return "Zle dane";
        }
    }

    @Override
    public Class<?> getColumnClass(int i) {
        switch (i){
            case 0:
                return ImageIcon.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return JProgressBar.class;
            case 4:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Store store = storeTableArrayList.get(i);
        switch (i1){
            case 0:
                Image image = new ImageIcon(getClass().getResource(store.getIconPath())).getImage().getScaledInstance(50, 30, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(image);
                return icon;
            case 1:
                return "Magazyn " + store.getId();
            case 2:
                return "Lokalizacja: " + store.getLocalization();
            case 3:
                JProgressBar progressBar = new JProgressBar();
                progressBar.setValue((int)(store.getPercentAreaTaken() + 0.5));
                return progressBar;
            case 4:
                return String.format("%2.2f", store.getPercentAreaTaken()) + " %";
            default:
                return null;
        }
    }



    @Override
    public void setValueAt(Object o, int i, int i1) {

    }

    ArrayList<TableModelListener> listeners;

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
        listeners.add(tableModelListener);
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
        listeners.remove(tableModelListener);
    }

    private void inform(){

        TableModelEvent tme = new TableModelEvent(this, 0, getRowCount());

        listeners.forEach(
                (l) -> l.tableChanged(tme)
        );

    }

    public ArrayList<Store> getStoreTableArrayList() {
        return storeTableArrayList;
    }
}
