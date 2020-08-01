import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ComparisonPanel extends JPanel {

    private StoreTableListModel tableListModel;

    public ComparisonPanel(StoreTableListModel tableListModel, JList<Store> list){
        this.tableListModel = tableListModel;

        JList<Store> jList = list;

        ComparisonListCellRenderer listCellRenderer = new ComparisonListCellRenderer();
        jList.setCellRenderer(listCellRenderer);

        setLayout(new BorderLayout());
        JScrollPane listScroll = new JScrollPane(jList);
        listScroll.setPreferredSize(new Dimension(500, 600));
        add(listScroll, BorderLayout.CENTER);
    }

}

class ComparisonListCellRenderer implements ListCellRenderer<Store> {

    private JPanel mainPanel;

    public Component getListCellRendererComponent(JList<? extends Store> jList, Store store, int i, boolean b, boolean b1) {
        ArrayList<Item> itemArrayList = new ArrayList<>(Arrays.asList(store.getItems()));

        mainPanel = new JPanel();
        JPanel upPanel = new JPanel();

        upPanel.setLayout(new BorderLayout());
        Image image = new ImageIcon(getClass().getResource(store.getIconPath())).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        upPanel.add(new JLabel(new ImageIcon(image)), BorderLayout.WEST);
        upPanel.add(new JLabel("Magazyn " + store.getId()), BorderLayout.NORTH);
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue((int) (store.getPercentAreaTaken() + 0.5));
        CustomProgressBar.set(progressBar);
        upPanel.add(progressBar, BorderLayout.CENTER);


        JTable itemJTable = new JTable();
        MyTableViewModel tableViewModel = new MyTableViewModel(itemArrayList);
        tableViewModel.fireTableStructureChanged();
        itemJTable.setModel(tableViewModel);

        itemJTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        itemJTable.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        JScrollPane scrollPane = new JScrollPane(itemJTable);
        scrollPane.setPreferredSize(new Dimension(1000, 500));
        scrollPane.setSize(new Dimension(1000, 500));

        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(upPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(scrollPane, gbc);

        return mainPanel;
    }
}

class MyTableViewModel extends AbstractTableModel {
    ArrayList<Item> itemArrayList;

    public MyTableViewModel(ArrayList<Item> itemArrayList){
        this.itemArrayList = itemArrayList;
        inform();
    }


    @Override
    public int getRowCount() {
        return itemArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Ikona";
            case 1:
                return "Nazwa";
            case 2:
                return "Opis";
            case 3:
                return "Zajmowana powierzchnia";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int i) {
        switch (i) {
            case 0:
                return ImageIcon.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Item item = itemArrayList.get(i);
        switch (i1) {
            case 0:
                return new ImageIcon(item.getIcon().getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            case 1:
                return item.getName();
            case 2:
                return item.getDescription();
            case 3:
                return item.getVolume();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        inform();
    }

    ArrayList<TableModelListener> listeners = new ArrayList<>();

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {
        listeners.add(tableModelListener);
    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {
        listeners.remove(tableModelListener);
    }

    public void inform() {

        TableModelEvent tme = new TableModelEvent(this, 0, getRowCount());

        listeners.forEach(l -> l.tableChanged(tme));

    }



}
