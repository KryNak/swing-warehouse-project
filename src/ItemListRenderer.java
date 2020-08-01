import javax.swing.*;
import java.awt.*;

public class ItemListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Item){
            Item item = (Item) value;
            Image img = item.getIcon().getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);

            JPanel innerPanel = new JPanel();
            innerPanel.setLayout(new BorderLayout());

            innerPanel.add(new JLabel(new ImageIcon(img)), BorderLayout.LINE_START);
            innerPanel.add(new JLabel(item.getName()), BorderLayout.BEFORE_FIRST_LINE);
            innerPanel.add(new JTextArea(item.getDescription()), BorderLayout.AFTER_LAST_LINE);
            innerPanel.add(new JLabel(item.getVolume()+""), BorderLayout.AFTER_LINE_ENDS);

            innerPanel.setPreferredSize(new Dimension(200, 90));

            return innerPanel;
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
