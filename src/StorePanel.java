import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StorePanel extends JPanel {

    JList<Store> jList;
    StoreTableListModel stlm;
    JTable table;

    public StorePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel iconLabel = new JLabel("Ikonka: ", JLabel.RIGHT);
        JComboBox<Integer> comboBox = new JComboBox<>(new Integer[]{0, 1, 2, 3, 4});
        comboBox.setRenderer(new ComboBoxRenderer());
        JLabel localizationLabel = new JLabel("Lokalizacja: ", JLabel.RIGHT);
        localizationLabel.setPreferredSize(new Dimension(100,35));
        JLabel areaLabel = new JLabel("Powierzchnia: ", JLabel.RIGHT);
        areaLabel.setPreferredSize(new Dimension(100,35));
        JTextField localizationField = new JTextField();
        localizationField.setPreferredSize(new Dimension(150,35));
        JTextField areaField = new JTextField();
        areaField.setPreferredSize(new Dimension(150,35));
        JButton addButton = new JButton("Dodaj");
        addButton.setPreferredSize(new Dimension(100, 35));

        panel.add(iconLabel);
        panel.add(comboBox);
        panel.add(localizationLabel);
        panel.add(localizationField);
        panel.add(areaLabel);
        panel.add(areaField);
        panel.add(addButton);

        setLayout(new BorderLayout());

        stlm = new StoreTableListModel();
        table = new JTable();
        table.setModel(stlm);
        table.getColumnModel().getColumn(3).setCellRenderer(new ProgressBarRenderer());
        table.setRowHeight(30);

        Store s1 = new Store(4, "Waszyngtona", "icon/store0.png");
        s1.addItem(Item.getWardrobe());
        s1.addItem(Item.getBed());

        Store s2 = new Store(3, "Jaracza","icon/store1.png");
        s2.addItem(Item.getArmchair());
        s2.addItem(Item.getBed());
        s2.addItem(Item.getToolCrib());

        Store s3 = new Store(5, "Sikorskiego", "icon/store2.png");
        s3.addItem(Item.getTumbleDryer());
        s3.addItem(Item.getTV());
        s3.addItem(Item.getBike());

        stlm.addStore(s1);
        stlm.addStore(s2);
        stlm.addStore(s3);

        table.setPreferredSize(new Dimension(1200,800));
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e->{
            if(areaField.getText().matches("^[0-9]+$")){
                stlm.addStore(new Store(Integer.parseInt(areaField.getText()), localizationField.getText(), "icon/store" + comboBox.getSelectedIndex() + ".png"));
                jList.updateUI();
            }

            else areaField.setText("Err");
        });
    }

    public StoreTableListModel getTableModel(){
        return stlm;
    }

    public void setJList(JList<Store> jList){
        this.jList = jList;
    }

    public void loadListModel(ArrayList<Store> list){
        stlm.setStoreTableArrayList(list);
    }

    public JTable getTable() {
        return table;
    }
}
