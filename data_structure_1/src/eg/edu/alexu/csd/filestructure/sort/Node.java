package eg.edu.alexu.csd.filestructure.sort;
import java.util.Vector;
public class Node implements INode {
    private Object node;
    private int index;
    private int f;
    public Node(Object object){
        for (int i=0;i<Controller.arr.size();i++){
            if(Controller.arr.elementAt(i).getValue().equals(object)){
                index = i;
            }
        }
    }
    public Node(){
        node=null;
    }
    @Override
    public INode getLeftChild() {
        if(index*2+1>Controller.arr.size()){
            return null;
        }
        return (INode) Controller.arr.elementAt(index*2+1);
    }
    @Override
    public INode getRightChild() {
        if(index*2+2>Controller.arr.size()){
            return null;
        }
        return Controller.arr.elementAt(index*2+2);
    }
    @Override
    public INode getParent() {
        if(index==0){
            return null;
        }
        return Controller.arr.elementAt((index-1)/2);
    }
    @Override
    public Comparable getValue() {
        return (Comparable) node;
    }
    @Override
    public void setValue(Comparable value) {
        node=value;
    }
}