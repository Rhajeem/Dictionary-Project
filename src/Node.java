/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */

public class Node 
{
    private Data data;
    private Node nextNode;
    private Node prevNode;

    public Node() 
    {
        data = new Data("h ", "m", "h");
    }

    public Node(Data data)
    {
        this.data = data;
    }
    
    public Node(Node a) 
    {
        this.data = a.data;
        this.prevNode = a.prevNode;
        this.nextNode = a.nextNode;
    }
    
    public Data getData() 
    {
        return data;
    }

    public void setData(Data data) 
    {
        this.data = data;
    }

    public Node getNextNode() 
    {
        return nextNode;
    }

    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }

    public Node getPrevNode() 
    {
        return prevNode;
    }

    public void setPrevNode(Node prevNode)
    {
        this.prevNode = prevNode;
    }
    
    public void switchData(Node b) 
    {
        
        this.data =b.data;
        
    }
}
