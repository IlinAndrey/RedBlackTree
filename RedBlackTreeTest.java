package red.black.tree;

import java.util.Scanner;

public class RedBlackTreeTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        RBTree rbt = new RBTree(Integer.MIN_VALUE);
        System.out.println("Red Black Tree Test\n");
        char ch;
        do {
            System.out.println("\nОперации над красно-черным деревом:\n");
            System.out.println("1. Ввод элемента ");
            System.out.println("2. Поиск");
            System.out.println("3. Колличество узлов");
            System.out.println("4. Проверить на пустоту");
            System.out.println("5. Отчистить дерево ");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите целочисленный элемент для вставки");
                    rbt.insert(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Введите целочисленный элемент для поиска");
                    System.out.println("Результат поиска : " + rbt.search(scan.nextInt()));
                    break;
                case 3:
                    System.out.println("Узлы = " + rbt.countNodes());
                    break;
                case 4:
                    System.out.println("Пустой статус = " + rbt.isEmpty());
                    break;
                case 5:
                    System.out.println("\nДерево отчищено");
                    rbt.makeEmpty();
                    break;
                default:
                    System.out.println("Не верный ввод \n ");
                    break;
            }
            System.out.print("\nВведеная последовательность изначально : ");
            rbt.preorder();
            System.out.print("\nПолученное дерево : ");
            rbt.inorder();

            System.out.println("\nХотите продолжить (нажмите y или n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
