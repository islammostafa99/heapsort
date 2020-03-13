package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Predicate;
public class Heap implements IHeap {
    private INode left;
    private INode right;
    private INode largest;
    private INode swap;
    private INode temp;
    private ArrayList<INode> unordered = new ArrayList();
    private int index;
    private int lastPosititon;
    private int a, b,c;

    public Heap(ArrayList unordered) {
        for (int i=0;i<unordered.size();i++){
            INode s=new Node();
            s.setValue((Comparable) unordered.get(i));
            this.unordered.add(i,s);
        }
    }

    @Override
    public INode getRoot() {
        return this.unordered.get(0);
    }

    @Override
    public int size() {
        return this.unordered.size();
    }

    @Override
    public void heapify(INode node) {
        if (node.getLeftChild() == null) {
            return;
        }
        left = node.getLeftChild();
        right = node.getRightChild();
        for (int i = 0; i < Controller.arr.size(); i++) {
            if (Controller.arr.elementAt(i).getValue().equals(node)) {
                index = i;
            }
        }
        if (left.getValue().compareTo(node.getValue()) > 0) {
            largest = left;
        } else {
            largest = node;
        }
        if (right.getValue().compareTo(largest.getValue()) > 0) {
            largest = right;
        }
        for (int i = 0; i < Controller.arr.size(); i++) {
            if (Controller.arr.elementAt(i).getValue().equals(largest)) {
                b = i;
            }
        }
        a = index;
        if (a != b) {
            swap = largest;
            this.unordered.remove(b);
            this.unordered.add(b, node);
            this.unordered.remove(a);
            this.unordered.add(a, swap);
            heapify(largest);
        }
    }
    @Override
    public Comparable extract() {
        build(this.unordered);
        temp = this.unordered.get(0);
        this.unordered.remove(0);
        this.unordered.add(0,this.unordered.get(this.unordered.size()-1));
        this.unordered.remove(this.unordered.get(this.unordered.size()-1));
        heapify(temp);
        return (Comparable) temp;
    }

    /*private void trickleDown(int parent) {
        if (parent * 2 + 1 == lastPosititon && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 1)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 1));
            this.unordered.remove(parent*2+1);
            this.unordered.add(parent*2+1, swap);
            return;
        }
        if (parent * 2 + 2 == lastPosititon && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 2)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 2));
            this.unordered.remove(parent*2+2);
            this.unordered.add(this.unordered.indexOf(parent * 2 + 2), swap);
            return;
        }
        if (parent * 2 + 1 >= lastPosititon || parent * 2 + 2 >= lastPosititon) {
            return;
        }
        if ((int) this.unordered.get(parent * 2 + 1) > (int) this.unordered.get(parent * 2 + 2) && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 1)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 1));
            this.unordered.remove(parent*2+1);
            this.unordered.add(parent * 2 + 1, swap);
            trickleDown(parent * 2 + 1);
        } else if ((int) this.unordered.get(parent * 2 + 2) > (int) this.unordered.get(parent * 2 + 1) && (int) this.unordered.get(parent) < (int) this.unordered.get(parent * 2 + 2)) {
            swap = (Node) this.unordered.get(parent);
            this.unordered.remove(parent);
            this.unordered.add(parent, this.unordered.get(parent * 2 + 2));
            this.unordered.remove(parent*2+2);
            this.unordered.add(parent * 2 + 2, swap);
            trickleDown(parent * 2 + 2);
        }
    }*/

    @Override
    public void insert(Comparable element) {
        build(this.unordered);
        lastPosititon = this.unordered.size() - 1;
        this.unordered.add(++lastPosititon, (INode) element);
        trickleUp(lastPosititon);
    }

    private void trickleUp(int lastPosititon) {
        if (lastPosititon == 0) {
            return;
        }
        INode child = this.unordered.get(lastPosititon);
        INode parent = child.getParent();
        if (child.getValue().compareTo(parent.getValue())>0) {
            swap = child;
            this.unordered.remove(lastPosititon);
            this.unordered.add(lastPosititon, parent );
            for (int i=0;i<this.unordered.size();i++){
                if(this.unordered.get(i).getValue().equals(parent)){
                    c = i;
                }
            }
            this.unordered.remove(c);
            this.unordered.add(c, swap);
            int l=lastPosititon;
            l--;
            trickleUp(l);
        }
    }
    @Override
    public void build(Collection unordered) {

        for (int j = 0; j < unordered.size() ; j++) {
            this.unordered.add(j, (INode) unordered.iterator().next());
        }
        for (int i = (this.unordered.size() / 2)-1; i >= 0; i--) {
            heapify(this.unordered.get(i));
        }
        for(int i = this.unordered.size()-1;i>=0;i--){
            heapify(this.unordered.get(i));
            // MOMKN swap
        }
    }
}