import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Item implements Serializable {

    private static int nextId = 0;
    private long id = nextId++;
    private String description;
    private ImageIcon icon;

    private final static ArrayList<Item> AllItemsList = new ArrayList<>(Arrays.asList(
            getToolCrib(), getTable(), getArmchair(), getBed(), getChair(), getTableSaw(), getBike(), getCartoonBox(),
            getTumbleDryer(), getTV(), getWardrobe()));

    private String name;
    private double volume;

    public Item(String name, double volume, String description){
        this.name = name;
        this.volume = volume;
        this.description = description;
        this.icon = new ImageIcon(getClass().getResource("icon/none.png"));
    }

    public Item(String name, double volume, String description, ImageIcon icon){
        this.name = name;
        this.volume = volume;
        this.description = description;
        this.icon = icon;
    }

    public static Item getToolCrib(){
        return new Item("Szafka na narzędzia", 0.648, "Mozna w niej przechowywac spory asortyment narzedzi.", new ImageIcon(Item.class.getResource("icon/toolcrib.png")) );
    }

    public static Item getTableSaw(){
        return new Item("Piła stołowa", 0.43, "Nie utnij sobie palca", new ImageIcon(Item.class.getResource("icon/tablesaw.png")));
    }

    public static Item getBike(){
        return new Item("Rower", 0.15, "Szczegolnie lubiany w czasie kwarantany", new ImageIcon(Item.class.getResource("icon/bike.png")));
    }

    public static Item getCartoonBox(){
        return new Item("Karton", 0.1, "Lepiej w nie nie wiezdzac", new ImageIcon(Item.class.getResource("icon/box.png")));
    }

    public static Item getTumbleDryer(){
        return new Item("Suszarka bebnowa", 0.225, "Szybko suszy ubrania", new ImageIcon(Item.class.getResource("icon/tumbledryer.png")));
    }

    public static Item getBed(){
        return new Item("Lozko", 1.5, "Spi sie w nim", new ImageIcon(Item.class.getResource("icon/bed.png")));
    }

    public static Item getWardrobe(){
        return new Item("Szafa", 1.44, "Przechowuje ubrania", new ImageIcon(Item.class.getResource("icon/wardrobe.png")));
    }

    public static Item getTV(){
        return new Item("Telewizor",0.09, "Sluzy do ogladania fajnych filmow", new ImageIcon(Item.class.getResource("icon/tv.png")));
    }

    public static Item getArmchair(){
        return new Item("Fotel", 0.57, "Takie lepsze krzeslo", new ImageIcon(Item.class.getResource("icon/armchair.png")));
    }

    public static Item getTable(){
        return new Item("Stol",0.385, "Mazna czasem na nim cos zjesc", new ImageIcon(Item.class.getResource("icon/table.png")));
    }

    public static Item getChair(){
        return new Item("Krzeslo",0.2, "Taki gorszy fotel", new ImageIcon(Item.class.getResource("icon/chair.png")));
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.volume, volume) == 0 &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, volume);
    }

    @Override
    public String toString() {
        return "--Nazwa: " + getName() + ", Objetosc: " + getVolume() + ", Opis: " + description + ", Ikonka: " + icon.toString();
    }

    public static ArrayList<Item> getAllItemsList() {
        return AllItemsList;
    }
}
