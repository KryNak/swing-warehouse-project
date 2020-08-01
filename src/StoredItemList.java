import java.util.ArrayList;

public class StoredItemList extends ArrayList<Item> {

        public double getTakenArea(){
            double temp = 0;

            for(int i = 0; i < this.size(); i++){
                temp+=this.get(i).getVolume();
            }

            return temp;
        }

        public Item[] getSimpleTab(){

            Item[] items = new Item[this.size()];

            for(int i = 0; i < this.size(); i++){
                items[i] = this.get(i);
            }

            return items;
        }

}
