import java.io.Serializable;

public class Store implements Serializable {

    private static long nextId = 0;

    private long id = nextId ++;
    private double area;
    private String localization;
    private String iconPath;

    private StoredItemList list;

    public Store(int area, String localization, String iconPath){
        list = new StoredItemList();
        this.area = area;
        this.localization = localization;
        this.iconPath = iconPath;
    }

    public double getArea() {
        return area;
    }

    public long getId() {
        return id;
    }

    public String getLocalization() {
        return localization;
    }

    public boolean addItem(Item item){
        if(area >= list.getTakenArea() + item.getVolume()){
            list.add(item);
            return true;
        }

        return false;
    }

    public double getPercentAreaTaken(){
        return (list.getTakenArea() / area) * 100;
    }

    public double getAreaTaken(){
        return list.getTakenArea();
    }

    public String toString(){
        return "**lokalizacja- " + localization + ", pojemnosc- " + area + ", ikonka- " + iconPath;
    }

    public Item[] getItems() {
        return list.getSimpleTab();
    }

    public String getIconPath() {
        return iconPath;
    }
}
