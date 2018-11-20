package ngaleev;

import java.util.HashMap;
import java.util.Map;

public class CategoryNamesMap {

    static private Map categoryNamesMap = createMap();

    static private HashMap<String,String> createMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Компьютеры","Компьютерная техника");
        map.put("Зоотовары","Товары для животных");
        map.put("Дом, дача, ремонт","Товары для дома");
        map.put("Красота и здоровье","Товары для красоты и здоровья");
        map.put("Авто","Товары для авто- и мототехники");
        return map;
    }

    static public boolean compareCategoryName(String categoryInList, String categoryOnPage){
        if (categoryInList.compareTo(categoryOnPage)==0){
            return true;
        }
        return categoryNamesMap.get(categoryInList).toString().compareTo(categoryOnPage)==0;
    }
}
