import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please, enter number of spots and links in the graph");
        System.out.println("And then enter links in format: 'x y', where x and y are linked spots");
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int vVal = in.nextInt(), lineVal = in.nextInt(), valOfCol = 1;
        int[] order = new int[vVal];
        int[] color = new int[vVal];
        int begin, end;
        for (int i = 0; i < vVal; i++) { //
            graph.add(new ArrayList<>());
            order[i] = i;
            color[i] = 0;
        } //добавлние вершин в граф (формируем список смежности)
        for (int i = 0; i < lineVal; i++) {
            begin = in.nextInt();
            end = in.nextInt();
            graph.get(begin).add(end);
            graph.get(end).add(begin);
        } //добавление ребер в граф

        setOrder(graph, order);

        for (int i = 0; i < order.length; i++) {
            int k = order[i];
            if(color[k] == 0) {
                color[k] = 1;
            }
            for(int spot:graph.get(k)){
                if(color[spot] == 0) {
                    if(color[k] > 1) {
                        color[spot] = color[k]-1;
                    } else {
                        color[spot] = color[k]+1;
                        if(valOfCol < color[spot]) valOfCol = color[spot];
                    }
                }
                if(color[spot] == color[k]){
                    color[spot] = color[k] + 1;
                    valOfCol = color[spot];
                }
            }
        }
        System.out.println(valOfCol+" (number of colors)");

        for (int i = 0; i < vVal; i++) {
            System.out.print(order[i]+" ");
        }
        System.out.println( "(indexes of spots)");

        for (int i = 0; i < vVal; i++) {
            System.out.print(color[i]+" ");
        }
        System.out.println("(colors)");

    }

    public static void setOrder(ArrayList<ArrayList<Integer>> graph, int[] order) {
        int help;
        for (int i = 0; i < order.length; i++) {
            for (int j = 0; j < order.length-1; j++) {
                if(graph.get(order[j]).size() < graph.get(order[j+1]).size()) {
                    help = order[j];
                    order[j] = order[j+1];
                    order[j+1] = help;
                }
            }
        }

    } // сортировка графа по количеству связей
}
