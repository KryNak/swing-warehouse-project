import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ComboBoxRenderer extends DefaultListCellRenderer {

    private HashMap<String, ImageIcon> imagaHashMap;

    public ComboBoxRenderer(){

        imagaHashMap = new HashMap<>();

        imagaHashMap.put("armchair", new ImageIcon(getClass().getResource("icon/armchair.png")));
        imagaHashMap.put("table", new ImageIcon(getClass().getResource("icon/table.png")));
        imagaHashMap.put("tablesaw", new ImageIcon(getClass().getResource("icon/tablesaw.png")));
        imagaHashMap.put("chair", new ImageIcon(getClass().getResource("icon/chair.png")));
        imagaHashMap.put("toolcrib", new ImageIcon(getClass().getResource("icon/toolcrib.png")));
        imagaHashMap.put("tv", new ImageIcon(getClass().getResource("icon/tv.png")));
        imagaHashMap.put("wardrobe", new ImageIcon(getClass().getResource("icon/wardrobe.png")));
        imagaHashMap.put("tumbledryer", new ImageIcon(getClass().getResource("icon/tumbledryer.png")));
        imagaHashMap.put("bike", new ImageIcon(getClass().getResource("icon/bike.png")));
        imagaHashMap.put("box", new ImageIcon(getClass().getResource("icon/box.png")));
        imagaHashMap.put("bed", new ImageIcon(getClass().getResource("icon/bed.png")));
        imagaHashMap.put("none",new ImageIcon(getClass().getResource("icon/none.png")));

    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Integer){
            ImageIcon icon = new ImageIcon(getClass().getResource("icon/store" + value + ".png"));
            Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            return new JLabel(new ImageIcon(img));
        }
        if(value instanceof String){
            ImageIcon icon = imagaHashMap.get(value);
            if(icon != null){
                Image img = icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT);
                return new JLabel(new ImageIcon(img));
            }
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
