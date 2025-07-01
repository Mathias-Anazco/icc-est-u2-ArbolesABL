public class AVLTrre {
    
    private Node node;

    public AVLTrre() {
        this.node = null;
    }

    public int height(Node node) {
        if (node == null) return 0;
        return node.getHeight();
    }

    public int getBalance(Node node) {
        if (node == null) return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    public void insert(int value) {
        System.out.println("Nodo a insertar: " + value);
        node = insertRec(node, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            newNode.setHeight(1);
            System.out.println("Nodo insertado = " + newNode.getValue() + 
                                " | Balance = " + getBalance(newNode));
            return newNode;
        }

        if (value < node.getValue()) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRec(node.getRight(), value));
        } else {
            return node;
        }

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        int balance = getBalance(node);

        System.out.println("Nodo actual: " + node.getValue());
        System.out.println("\tAltura = " + node.getHeight());
        System.out.println("\tBalance = " + balance);


        // Caso Izquierda - Izquierda
        if (balance > 1 && value < node.getLeft().getValue()) {
            System.out.println("Rotación Derecha (LL)");
            return rotateRight(node);
        }

        // Caso Derecha - Derecha
        if (balance < -1 && value > node.getRight().getValue()) {
            System.out.println("Rotación Izquierda (RR)");
            return rotateLeft(node);
        }

        // Caso Izquierda - Derecha
        if (balance > 1 && value > node.getLeft().getValue()) {
            System.out.println("Rotación Izquierda + Derecha (LR)");
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        // Caso Derecha - Izquierda
        if (balance < -1 && value < node.getRight().getValue()) {
            System.out.println("Rotación Derecha + Izquierda (RL)");
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));

        return y;
    }
}
