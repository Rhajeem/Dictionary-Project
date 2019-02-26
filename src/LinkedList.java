import javafx.scene.control.TreeItem;

/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */

public class LinkedList 
{
    
    private Node head;
    private Node tail = new Node();
    private int size = 0;
    private static int g =0;
    
    public LinkedList() 
    {
 
    }
    public boolean available()
    {
        
        Node temp = new Node();
        return (temp!=null);
    }
    public boolean isEmpty()
    {
        return(getHead()==null);
    }
    public int getSize()
    {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public Node getHead() {
        return head;
    }
    public void setHead(Node head) 
    {
        this.head = head;
    }

    public long insert(Data A)
    {
        FpTimer timer = new FpTimer();
        if(available()){
            Node temp = new Node(A);
            if(isEmpty()){
                setHead(temp);
                getHead().setPrevNode(null);
            }
            else
            {
                temp.setNextNode(getHead());
                getHead().setPrevNode(temp);
                setHead(temp);
            }
            size++;
        }
        else
        {
            
            System.out.println("Error!");
        }
        return timer.getDiff();
    }
    
    private void swap(Node A, Node B) //switches the position of the words
    {
        if(available())
        {
            
            Node temp = new Node(A);
            A.switchData(B);
            B.switchData(temp);
        }
    }
    
    private int checkWords(String w1, String w2) //checks for the word(s)
    {
        
        int smaller;
        smaller = w1.length() > w2.length() ? w2.length() : w1.length();
        
        for (int x = 0; x < smaller; x++) 
        {
            if (w1.charAt(x) > w2.charAt(x)) 
            {
                return 1;
            }
            
            if (w1.charAt(x) < w2.charAt(x)) 
            {
                return 2;
            }
            
        }
        
        return 2;
    }
    
    public long sort( Node A, int count) //sorts the linked list
    {
        FpTimer g = new FpTimer();
        int f ;
        
        if(available()) 
        {
            Node temp;
            Node temp1;
            temp = A;
            temp1 =temp;
            for(int x=0; x<count;x++)
            {
                temp1 = temp1.getNextNode();
                if(temp.getData().getWord().charAt(0) > temp1.getData().getWord().charAt(0))
                {
                    this.swap(temp, temp1);
                    
                }
                
                if(temp.getData().getWord().charAt(0) == temp1.getData().getWord().charAt(0))
                {
                    
                    f = this.checkWords(temp.getData().getWord(),	temp1.getData().getWord());
                    
                    if(f==1)
                    {
                        this.swap(temp, temp1);
                    }
                }
                
                
            }
            
            if(count!=0)
            {
                sort(temp.getNextNode(), count-1);
            }
            
        }
        else
        {
            System.out.print("Out of memory!");
        }
        return g.getDiff();
    }
    
    
    public TreeItem populateList(TreeItem treeItem)
    {
        
        if(available())
        {
                
            Node temp = new Node();
            temp = head;
            while(true)
            {
                if(head ==null)
                {
                    return new TreeItem();
                }
                TreeItem<String> t = new TreeItem<>(temp.getData().getWord());
                t.getChildren().add(new TreeItem<String>(temp.getData().getPartOfSpeech()));
                t.getChildren().add(new TreeItem<String>(temp.getData().getDefinition()));
                
                treeItem.getChildren().add(t);
                
                if(temp.getNextNode()==null)break;
                temp =temp.getNextNode();
            }
        }
        else
        {
            System.out.println("Error!");
        }
        
        treeItem.isExpanded();
        treeItem.setValue( treeItem.getChildren().size() +" Definitions found");
        
        return treeItem;
    }
    
    public TreeItem<String> searchReturn(String a, TreeItem<String> root)
    {
        
        if (available()) 
        {
            
            if (isEmpty()) 
            {
                System.out.print("No files!");
                
            }
            else
            {
                Node temp = new Node();
                temp.setNextNode(head);
                
                while (temp.getNextNode() != null) 
                {
                    
                    temp = temp.getNextNode();
                    
                    if(temp == tail)
                    {
                        break;
                    }
                    if ( temp.getData().getWord().toLowerCase().contains(a.toLowerCase().trim())  ) 
                    {
                        
                        TreeItem<String> rootItem = new TreeItem<String>(temp.getData().getWord());
                        rootItem.getChildren().add(new TreeItem<String>("Part of Speech : " + temp.getData().getPartOfSpeech()));
                        rootItem.getChildren().add(new TreeItem<String>("Definition : " + temp.getData().getDefinition()));
                        
                        root.getChildren().add(rootItem);
                    }
                    
                }
                
                root.setExpanded(true);
                root.setValue("Found " + root.getChildren().size() + " Results");
                
                if (root.isLeaf()) 
                {
                    root.setValue("Not found!");
                    
                    return root;
                }
            }
            
        }
        
        return root;
    }

    public boolean exist(String a)
    {
        if(available())
        {
            Node temp = new Node();
            temp = head;
            while(true)
            {
               if(temp.getData().getWord().equals(a))
               {
                   return true;
               }
                if(temp.getNextNode()==null)break;
                temp =temp.getNextNode();
            }
        }
        else
        {
            System.out.println("Error!");
        }
    return false;
    }
}
