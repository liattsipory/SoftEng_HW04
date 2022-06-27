import java.util.ArrayDeque;

public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        final  int NOT_THERE= -1;
        ArrayDeque<Integer> COUNTER_PER_LEVEL = new ArrayDeque<>();
        if (node.getData() == num);
        getLevelWithMostOccurrences(node.getLeft(), num);
        getLevelWithMostOccurrences(node.getRight(), num);


    }
}
