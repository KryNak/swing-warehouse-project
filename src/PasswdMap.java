import java.util.HashMap;

public class PasswdMap{

    private HashMap<String, String> pswd;

    public PasswdMap(){

        pswd= new HashMap<>();

        pswd.put("krystian","12345");
        pswd.put("Michal","eloeloelo");
        pswd.put("Marcin","szybki");

    }

    public HashMap<String, String> getObj(){
        return pswd;
    }

}
