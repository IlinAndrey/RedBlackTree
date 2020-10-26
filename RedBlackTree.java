package red.black.tree;

class RedBlackNode
{
    RedBlackNode left, right;
    int element;
    int color;

    public RedBlackNode(int theElement)
    {
        this( theElement, null, null );
    }
    public RedBlackNode(int theElement, RedBlackNode lt, RedBlackNode rt)
    {
        left = lt;
        right = rt;
        element = theElement;
        color = 1;
    }
}

class RBTree
{
    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode grand;
    private RedBlackNode great;
    private RedBlackNode header;
    private static RedBlackNode nullNode;
    static
    {
        nullNode = new RedBlackNode(0);
        nullNode.left = nullNode;
        nullNode.right = nullNode;
    }
    static final int BLACK = 1;
    static final int RED   = 0;

    /* Конструктор */
    public RBTree(int negInf)
    {
        header = new RedBlackNode(negInf);
        header.left = nullNode;
        header.right = nullNode;
    }
    /* Функция на проверку пустоты дерева */
    public boolean isEmpty()
    {
        return header.right == nullNode;
    }
    /* Здесь дерево делается логически пустым */
    public void makeEmpty()
    {
        header.right = nullNode;
    }
    /* Функция для вставки элемента */
    public void insert(int item )
    {
        current = parent = grand = header;
        nullNode.element = item;
        while (current.element != item)
        {
            great = grand;
            grand = parent;
            parent = current;
            current = item < current.element ? current.left : current.right;
            // Проверка на два красных узла, если такие есть, то исправляем
            if (current.left.color == RED && current.right.color == RED)
                handleReorient( item );
        }
        if (current != nullNode)
            return;
        current = new RedBlackNode(item, nullNode, nullNode);
        // Прикрепление к главному элементу
        if (item < parent.element)
            parent.left = current;
        else
            parent.right = current;
        handleReorient( item );
    }
    private void handleReorient(int item)
    {
        // Окрашивание
        current.color = RED;
        current.left.color = BLACK;
        current.right.color = BLACK;

        if (parent.color == RED)
        {
            grand.color = RED;
            if (item < grand.element != item < parent.element)
                parent = rotate( item, grand );
            current = rotate(item, great );
            current.color = BLACK;
        }
        // Здесь делается главный элемент черным
        header.right.color = BLACK;
    }
    private RedBlackNode rotate(int item, RedBlackNode parent)
    {
        if(item < parent.element)
            return parent.left = item < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;
        else
            return parent.right = item < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
    }
    /* Поворот дерева относительно левого узла */
    private RedBlackNode rotateWithLeftChild(RedBlackNode k2)
    {
        RedBlackNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    /* Поворот дерева относительно правого узла */
    private RedBlackNode rotateWithRightChild(RedBlackNode k1)
    {
        RedBlackNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
    /* Функция для подсчета количества узлов */
    public int countNodes()
    {
        return countNodes(header.right);
    }
    private int countNodes(RedBlackNode r)
    {
        if (r == nullNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Функция поиска элемента*/
    public boolean search(int val)
    {
        return search(header.right, val);
    }
    private boolean search(RedBlackNode r, int val)
    {
        boolean found = false;
        while ((r != nullNode) && !found)
        {
            int rval = r.element;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    /* Функция для обхода по порядку */
    public void inorder()
    {
        inorder(header.right);
    }
    private void inorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            inorder(r.left);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" ");
            inorder(r.right);
        }
    }
    public void preorder()
    {
        preorder(header.right);
    }
    private void preorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    public void postorder()
    {
        postorder(header.right);
    }
    private void postorder(RedBlackNode r)
    {
        if (r != nullNode)
        {
            postorder(r.left);
            postorder(r.right);
            char c = 'B';
            if (r.color == 0)
                c = 'R';
            System.out.print(r.element +""+c+" ");
        }
    }
}

