public class AvlTree {
    class Node{
        int val;
        Node right;
        Node left;
        Node head;
        void setVal(int val)
        {
            this.val=val;
            this.right=null;
            this.left=null;
        }

        void setRight(Node node)
        {
            this.right=node;
        }

        void setLeft(Node node)
        {
            this.right=node;
        }

        void print()
        {
            if(head==null)
            {
                System.out.println("null");
            }
            else
            {
                Node n=head;
                while (true)
                {
                    System.out.println(n.val);
                    if(n.right!=null)
                    {
                        n=n.right;
                    }
                    else if(n.left!=null)
                    {
                        n=n.left;
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }

        void rotate(Node node,char rotate)
        {
            Node node1=node;
            if(rotate=='l')
            {
                node=node.left;
                node.right=node1;
            }
            else
            {
                node=node.right;
                node.left=node1;
            }
        }
    }

    public static void main(String[] args) {
        AvlTree avlTree=new AvlTree();
        Node n=avlTree.new Node();
        n.setVal(10);
        Node n1=avlTree.new Node();
        n1.setVal(12);
        n.setLeft(n1);
        Node n2=avlTree.new Node();
        n2.setVal(13);
        n.setLeft(n2);
        n.print();
    }
}
