import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ItemsPanel extends JPanel {

    ItemListModel ilm;
    JList list;

    public ItemsPanel(){

        setLayout(new BorderLayout());

        ilm = new ItemListModel();
        list = new JList();
        list.setModel(ilm);
        JScrollPane scrollPane = new JScrollPane(list);
        list.setCellRenderer(new ItemListRenderer());

        scrollPane.setPreferredSize(new Dimension(1200, 600));
        add(scrollPane, BorderLayout.NORTH);

        JPanel addItemPanel = new JPanel();

        JLabel iconLabel = new JLabel("Ikonka: ", JLabel.RIGHT);

        JComboBox<String> comboBox = new JComboBox<>(new String[]{"armchair", "bed", "bike", "box", "tv", "wardrobe", "tumbledryer", "toolcrib", "chair", "table", "none"});
        comboBox.setRenderer(new ComboBoxRenderer());

        JLabel nameLabel = new JLabel("Nazwa: ", JLabel.RIGHT);
        nameLabel.setPreferredSize(new Dimension(100,35));

        JLabel descriptionLabel = new JLabel("Opis: ", JLabel.RIGHT);
        descriptionLabel.setPreferredSize(new Dimension(100,35));

        JLabel volumeLabel = new JLabel("Powierzchnia: ");
        volumeLabel.setPreferredSize(new Dimension(100,35));

        JTextField volumeField = new JTextField();
        volumeField.setPreferredSize(new Dimension(150,35));

        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150,35));

        JTextArea descriptionTextArea = new JTextArea();
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setPreferredSize(new Dimension(200, 35));

        JButton addButton = new JButton("Dodaj");
        addButton.setPreferredSize(new Dimension(100, 35));

        addItemPanel.add(iconLabel);
        addItemPanel.add(comboBox);
        addItemPanel.add(nameLabel);
        addItemPanel.add(nameField);
        addItemPanel.add(volumeLabel);
        addItemPanel.add(volumeField);
        addItemPanel.add(descriptionLabel);
        addItemPanel.add(descriptionTextArea);
        addItemPanel.add(addButton);

        add(addItemPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e->{

            if(volumeField.getText().matches("^[0-9]\\.[0-9]+$")){

                ilm.addElement(
                        new Item(
                                nameField.getText(), Double.parseDouble(volumeField.getText()), descriptionTextArea.getText(),
                                new ImageIcon(getClass().getResource("icon/" + comboBox.getSelectedItem() + ".png"))
                        )
                );
                list.updateUI();
            }
            else volumeField.setText("Err");

        });
    }

    public ItemListModel getModelList(){
        return ilm;
    }

    public void loadListModel(ArrayList<Item> items){
        ilm.setItemArrayList(items);
        list.updateUI();
    }
}
