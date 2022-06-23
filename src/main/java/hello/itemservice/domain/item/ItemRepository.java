package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // ComponentScan의 대상이 된다.
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static 사용함 // 실무에서는 HashMap 대신 ConcurrentHashMap 사용
    private static long sequence = 0L; // static 사용함 // 실무에서는 atomic 등등 다른 것 사용

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);

        /**
         * 사실은 ItemParamDto 등의 객체를 따로 만들어서
         * itemName, price, quantity 세 개의 파라미터만 넣어 놓는게 더 좋은 방법이다.
         */
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
