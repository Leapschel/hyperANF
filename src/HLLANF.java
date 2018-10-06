

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;



public class HLLANF {

    private static HashMap<Long, ApproxSet> hllCounter = new HashMap<>();

    public static void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);
        Graph g = new Graph();

        readInGraph(sc, g);

        for (Long v : g.vertices()) {
            ApproxSet hll = new ApproxSet();
            hll.add(v);
            hllCounter.put(v, hll);
        }

        int distance = 0;
        long reach = 0;

        while (reach < (Math.pow(g.vertices().size(),2))/2) {
            reach = 0;
            reach = countVertices(reach, g, hllCounter);
            distance++;

        }

        System.out.println(distance);
    }


    private static long countVertices (long reach, Graph g, HashMap<Long, ApproxSet> hllCounter){


        HashMap<Long, ApproxSet>  counter = new HashMap<Long, ApproxSet> () ;

        for (long vertex : g.vertices()){

                ApproxSet hll = new ApproxSet();
                hll.addSet(hllCounter.get(vertex));

            for (long neighbor : g.neighbors(vertex) ){

                hll.addSet(hllCounter.get(neighbor));
            }

            counter.put(vertex, hll);

            reach += hll.estimateSize();

        }

        copy(counter, g);

        return reach;



    }


    private static void copy (HashMap<Long, ApproxSet> m, Graph g){

        for (long v : g.vertices()) { hllCounter.put(v, m.get(v)); }
    }


    private static void readInGraph (Scanner sc, Graph g){

        while (sc.hasNext()){

            String [] verticePair = sc.nextLine().split(" ");

            g.add(Long.parseLong(verticePair[0]), Long.parseLong(verticePair[1]));

        }
    }


}
