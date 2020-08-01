import javafx.scene.control.ComboBox;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.util.ArrayList;

public class AddItemToStorePanel extends JPanel {

    private JComboBox<Item> itemBox;
    private JComboBox<Store> storeBox;
    private ComboBoxModel1 boxModel1;
    private ComboBoxModel2 boxModel2;

    public AddItemToStorePanel(StoreTableListModel tableModel, ItemListModel listModel){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        storeBox = new JComboBox<>();
        boxModel1 = new ComboBoxModel1(tableModel);
        storeBox.setModel(boxModel1);
        storeBox.setRenderer(new ListCellRenderer<Store>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Store> jList, Store store, int i, boolean b, boolean b1) {
                if(store != null){
                    JPanel panel = new JPanel();
                    panel.setLayout(new BorderLayout());
                    ImageIcon imageIcon = new ImageIcon(getClass().getResource(store.getIconPath()));
                    Image img = imageIcon.getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT);

                    panel.add(new JLabel(new ImageIcon(img)), BorderLayout.WEST);
                    panel.add(new JLabel("Magazyn " + store.getId()), BorderLayout.CENTER);
                    JProgressBar progressBar = new JProgressBar();
                    progressBar.setValue((int)(store.getPercentAreaTaken() + 0.5));
                    CustomProgressBar.set(progressBar);
                    panel.add(progressBar, BorderLayout.EAST);

                    return panel;
                }
                return null;
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        storeBox.setPreferredSize(new Dimension(200, 100));
        add(storeBox, gbc);

        itemBox = new JComboBox<>();
        boxModel2 = new ComboBoxModel2(listModel);
        itemBox.setModel(boxModel2);
        itemBox.setRenderer(new ListCellRenderer<Item>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Item> jList, Item item, int i, boolean b, boolean b1) {
                if(item != null){
                    JPanel panel = new JPanel();

                    panel.setLayout(new BorderLayout());
                    ImageIcon icon = item.getIcon();
                    Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
                    panel.add(new JLabel(new ImageIcon(img)), BorderLayout.WEST);

                    panel.add(new JLabel(item.getName()), BorderLayout.CENTER);

                    panel.add(new JLabel(item.getVolume() + ""), BorderLayout.EAST);

                    return panel;
                }
                return null;
            }
        });
        gbc.gridx = 1;
        itemBox.setPreferredSize(new Dimension(200, 100));
        add(itemBox, gbc);

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(e->{
            itemBox.updateUI();
            storeBox.updateUI();
            if(itemBox.getItemCount() > 0){
                boolean result;
                result = tableModel.addItemToStore(tableModel.getIndexOf((Store)storeBox.getSelectedItem()), (Item)itemBox.getSelectedItem());
                if(result){
                    listModel.removeElementAt(listModel.getIndexOf((Item)itemBox.getSelectedItem()));
                    if(itemBox.getItemCount() > 0){
                        itemBox.setSelectedIndex(0);
                    }
                    else
                        itemBox.setSelectedItem(null);
                }
            }
            itemBox.repaint();
            storeBox.repaint();
        });
        gbc.gridx = 2;
        addButton.setPreferredSize(new Dimension(100, 100));

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("icon/add.png"));
        Image addImg = imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        addButton.setIcon(new ImageIcon(addImg));
        addButton.setBackground(Color.WHITE);
        add(addButton, gbc);

    }

    public ComboBoxModel1 getBoxModel1() {
        return boxModel1;
    }

    public ComboBoxModel2 getBoxModel2() {
        return boxModel2;
    }

    public JComboBox<Item> getItemBox() {
        return itemBox;
    }

    public JComboBox<Store> getStoreBox() {
        return storeBox;
    }
}

class ComboBoxModel1 implements ComboBoxModel<Store>{

    private Store selectedItem;
    private ArrayList<Store> list;

    public ComboBoxModel1(StoreTableListModel listModel){
        list = listModel.getStoreTableArrayList();
        selectedItem = list.get(0);
    }
    @Override
    public void setSelectedItem(Object o) {
        for(Store s : list){
            if(s.equals(o))
                selectedItem = s;
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Store getElementAt(int i) {
        return list.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {

    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

    public void setList(ArrayList<Store> list) {
        this.list = list;
    }
}

class ComboBoxModel2 implements ComboBoxModel<Item>{

    private Item selectedItem;
    private ArrayList<Item> list;

    public ComboBoxModel2(ItemListModel listModel){
        list = listModel.getItemArrayList();
        selectedItem = list.get(0);
    }

    @Override
    public void setSelectedItem(Object o) {
        for(Item s : list){
            if(s.equals(o))
                selectedItem = s;
        }
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Item getElementAt(int i) {
        return list.get(i);
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {

    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {

    }

    public void setList(ArrayList<Item> list) {
        this.list = list;
    }
}
