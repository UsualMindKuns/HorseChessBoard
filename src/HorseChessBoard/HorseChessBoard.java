package HorseChessBoard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessBoard {
    private static int X = 6; //表示column
    private static int Y = 6; //表示rows
    private static int[][] chessBoard = new int[Y][X]; //棋盘
    private static boolean[] visited = new boolean[X * Y]; //记录棋盘位置是否走过
    private static boolean finished = false; //记录马儿是否遍历走完棋盘

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        travelChessBoard(chessBoard, 5, 3, 1);
        long end = System.currentTimeMillis();
        System.out.println("执行时间=" + (end - start));
        //显示棋盘
        for (int[] row : chessBoard) {
            for (int column : row) {
                System.out.print(column + "\t");
            }
            System.out.println();
        }

    }
    //写一个方法，对list的各个位置，可以走的下一个位置的次数进行排序，把可能走的下一个位置从小到大排序
    public static void sort(ArrayList<Point> list) {
        list.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }

    //编写最核心的算法(递归+回溯)，遍历棋盘，如果遍历成功，就将finished 设置为true
    //并且将马走的每一步step记录到chessBoard中
    public static void travelChessBoard(int[][] chessBoard, int row, int column, int step) {
        //记录当前位置是第几步
        chessBoard[row][column] = step;
        //将当前的位置设置为已走过
        visited[row * X + column] = true;
        //得到当前位置的所有可以走的点
        ArrayList<Point> list = next(new Point(column,row));
        sort(list);
        //遍历
        while (!list.isEmpty()) {
            //得到当前位置的可以走的第一个点
            Point point = list.remove(0);
            //得到该点后要判断该点是否被走过了
            //如果没有走过，就进行递归
            if (!visited[point.y * X + point.x]) {
                travelChessBoard(chessBoard, point.y, point.x, step + 1);
            }
        }

        //退出while循环后就应该判断遍历是否成功，判断的条件是finished是否完成和step是否走了36步
        //如果不成功，就要重置相应的值
        if (step < 36 && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    //编写方法，可以得到当前位置可以走的所有点
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList存放当前位置可以走的所有位置
        ArrayList<Point> list = new ArrayList<>();
        //创建一个Point对象(点/位置)，准备放入到list中
        Point point = new Point();

        //判断curPoint是否可以走如下位置，如果可以走，就将改点放入到list中存储

        //判断是否可以走5
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0 ) {
            list.add(new Point(point));
        }
        //判断是否可以走6
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0 ) {
            list.add(new Point(point));
        }
        //判断是否可以走7
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0 ) {
            list.add(new Point(point));
        }
        //判断是否可以走0
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0 ) {
            list.add(new Point(point));
        }
        //判断是否可以走1
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y ) {
            list.add(new Point(point));
        }
        //判断是否可以走2
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y ) {
            list.add(new Point(point));
        }
        //判断是否可以走3
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y ) {
            list.add(new Point(point));
        }
        //判断是否可以走4
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y ) {
            list.add(new Point(point));
        }

        return list;
    }

}
