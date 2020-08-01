import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class WarehouseFrame extends JFrame {

    final int WIDTH = 1200;
    final int HEIGHT = 800;

    public WarehouseFrame() {
        setTitle("Magazyn");
        setIconImage(new ImageIcon(getClass().getResource("icon/mainicon.png")).getImage());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        
        Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screen.width / 2 - WIDTH / 2, screen.height / 2 - HEIGHT / 2);

        setLayout(new BorderLayout());

        JList<Store> jList = new JList<>();

        StorePanel storePanel = new StorePanel();
        ItemsPanel itemsPanel = new ItemsPanel();
        AddItemToStorePanel addItemToSorePanel = new AddItemToStorePanel(storePanel.getTableModel(), itemsPanel.getModelList());
        ComparisonPanel comparisonPanel = new ComparisonPanel(storePanel.getTableModel(), jList);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Magazyn", storePanel);
        tabbedPane.addTab("Przedmioty", itemsPanel);
        tabbedPane.addTab("Dodaj przedmiot do magazynu", addItemToSorePanel);
        tabbedPane.addTab("Zestawienie", comparisonPanel);

        add(tabbedPane);

        StoreTableListModel tableListModel = storePanel.getTableModel();

        storePanel.setJList(jList);
        DefaultListModel<Store> listModel = new DefaultListModel<>() {

            @Override
            public int getSize() {
                return tableListModel.getSize();
            }

            @Override
            public Store getElementAt(int i) {
                return tableListModel.getStore(i);
            }

        };
        jList.setModel(listModel);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(1200, 25));
        JMenu menu = new JMenu("Plik");
        menuBar.add(menu);

        JFileChooser fileChooser = new JFileChooser();

        JMenuItem saveMenuItem = new JMenuItem("Zapisz");
        saveMenuItem.addActionListener(e->{
            int retrieval = fileChooser.showSaveDialog(this);
            if(retrieval == JFileChooser.APPROVE_OPTION){

                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));
                    Object[] objects = new Object[]{storePanel.getTableModel().storeTableArrayList, itemsPanel.getModelList().getItemArrayList()};
                    oos.writeObject(objects);
                    oos.close();

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        JMenuItem loadMenuItem = new JMenuItem("Wczytaj");
        loadMenuItem.addActionListener(e->{

            int retrieval = fileChooser.showDialog(this, "Wczytaj");
            if(retrieval == JFileChooser.APPROVE_OPTION){

                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
                    Object[] objects = (Object[]) ois.readObject();

                    storePanel.loadListModel((ArrayList<Store>) objects[0]);
                    itemsPanel.loadListModel((ArrayList<Item>) objects[1]);

                    ois.close();

                    addItemToSorePanel.getBoxModel1().setList((ArrayList<Store>) objects[0]);
                    if(addItemToSorePanel.getStoreBox().getItemCount() > 0)
                        addItemToSorePanel.getStoreBox().setSelectedIndex(0);
                    addItemToSorePanel.getBoxModel2().setList((ArrayList<Item>) objects[1]);
                    if(addItemToSorePanel.getItemBox().getItemCount() > 0)
                        addItemToSorePanel.getItemBox().setSelectedIndex(0);
                    jList.updateUI();
                    storePanel.getTable().updateUI();
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }

        });

        menu.add(saveMenuItem);
        menu.add(loadMenuItem);

        setJMenuBar(menuBar);
    }

}
