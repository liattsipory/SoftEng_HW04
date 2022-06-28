import java.util.ArrayDeque;

public class LevelMostOccurrences {
    public static int getLevelWithMostOccurrences(BinNode<Integer> node, int num) {
        final int NOT_THERE = -1;
        final int FIRST_LEVEL = 0;
        ArrayDeque<BinNode<Integer>> elementsQueue = new ArrayDeque<>();
        ArrayDeque<Integer> levelsQueue = new ArrayDeque<>();
        int mostOccurrences = 0;
        int currCounter = 0;
        int resultLevel = NOT_THERE;
        int currLevel = FIRST_LEVEL;
        elementsQueue.add(node);
        levelsQueue.add(FIRST_LEVEL);
        while (!elementsQueue.isEmpty()) {
            BinNode<Integer> element = elementsQueue.pop();
            int elementLevel = levelsQueue.pop();
            if (element.getLeft() != null) {
                elementsQueue.add(element.getLeft());
                levelsQueue.add(elementLevel + 1);
            }
            if (element.getRight() != null) {
                elementsQueue.add(element.getRight());
                levelsQueue.add(elementLevel + 1);
            }
            if (element.getData() == num) currCounter++;
            if (currCounter > mostOccurrences) {
                mostOccurrences = currCounter;
                resultLevel = currLevel;
            }

            if (!levelsQueue.isEmpty() && levelsQueue.peek()>currLevel) {
                currLevel++;
                currCounter = 0;
            }
        }
        return resultLevel;
    }
}


