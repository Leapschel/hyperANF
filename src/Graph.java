import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//author: Martin Aumüller, ITU


public class Graph {

    HashMap<Long, HashSet<Long>> g = new HashMap<Long, HashSet<Long>>();

    void add(Long u, Long v) {
        g.putIfAbsent(u, new HashSet<Long>());
        g.putIfAbsent(v, new HashSet<Long>());
        g.get(u).add(v);
        g.get(v).add(u);
    }

    HashSet<Long> neighbors(Long u) {
        return g.get(u);
    }

    Set<Long> vertices() {
        return g.keySet();
    }
}
