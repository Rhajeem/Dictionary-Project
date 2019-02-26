import javafx.scene.control.TreeItem;

/*Created by:
 * Rhajeem Crawford - 1501389
 * Santana Broderick - 1500711
 * Jahvier Small - 1501224
 */

public class Tree 
{
    
    private Node root;
    private int count =0;
    
    public Tree() 
    {
        root = null;
        count = 0;
    }
    
    public int getCount() 
    {
        return count;
    }
    
    private boolean isEmpty() 
    {
        return (root == null);
    }
    
    private boolean available()
    {
        Node temp = new Node();
        return (temp != null);
    }
    
    public long insert(Data a) 
    {
        FpTimer timer = new FpTimer();
        if (this.available()) 
        {
            Node temp = new Node();
            Node temp1 = new Node(a);
    
            if (isEmpty()) 
            {
        
                root = new Node(a);
                count++;
                return timer.getDiff();
            }
    
    
            temp = root;
            while (true) 
            {
                if (temp.getData().getWord().charAt(0) < temp1.getData().getWord().charAt(0)) 
                {
                    if (temp.getPrevNode() == null) 
                    {
                        temp.setPrevNode(temp1);
                        count++;
                        return timer.getDiff();
                    } 
                    else 
                    {
                        temp = temp.getPrevNode();
                    }
    
                }
                if (temp.getData().getWord().charAt(0) > temp1.getData().getWord().charAt(0))
                {
                    if (temp.getNextNode() == null) 
                    {
                        temp.setNextNode(temp1);
                        count++;
                        return timer.getDiff();
                    }
                    else
                    {
                        temp = temp.getNextNode();
                    }
    
                }
    
                if (temp.getData().getWord().charAt(0) == temp1.getData().getWord().charAt(0))
                {
                    if (this.checkWords(temp.getData().getWord(), temp1.getData().getWord()) == 2) 
                    {
                        if (temp.getPrevNode() == null)
                        {
                            temp.setPrevNode(temp1);
                            count++;
                            return timer.getDiff();
                        }
                        else
                        {
                            temp = temp.getPrevNode();
                        }
    
                    } 
                    else 
                    {
                        if (temp.getNextNode() == null) 
                        {
                            temp.setNextNode(temp1);
                            count++;
                            return timer.getDiff();
                        } 
                        else 
                        {
                            temp = temp.getNextNode();
                        }
    
                    }
                }
            }
    
    
        } 
        else 
        {
            System.out.print("Out of memory!");
        }
        
        return timer.getDiff();
    }
    
    private int checkWords(String w1, String w2) 
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
    
    
    public Node getRoot() 
    {
        return this.root;
    }
    
    public TreeItem searchOrder(Node a, String b, TreeItem root1) 
    {
        
        if (a != null)
        {
            searchOrder(a.getNextNode(), b, root1);
            if (a.getData().getWord().toLowerCase().contains(b.toLowerCase().trim()))
            {
    
                TreeItem<String> rootItem = new TreeItem<String>(a.getData().getWord());
                rootItem.getChildren().add(new TreeItem<String>("Part of Speech : " + a.getData().getPartOfSpeech()));
                rootItem.getChildren().add(new TreeItem<String>("Definition : " + a.getData().getDefinition()));
    
                root1.getChildren().add(rootItem);
    
            }
            searchOrder(a.getPrevNode(), b, root1);
        }
        
        root1.setExpanded(true);
        root1.setValue("Found " + root1.getChildren().size() + " Result(s)");
        
        if (root1.isLeaf()) root1.setValue("Word not found! ");
        return root1;
        
        
    }
    
    
    public boolean exists(Node a, String b, TreeItem root1) //Checks if the word exist
    {
    
        if (a != null) 
        {
            if(exists(a.getPrevNode(), b, root1))
                return true;
            if (a.getData().getWord().toLowerCase().equals(b.toLowerCase().trim()))
            {
                return true;
            }
           if(exists(a.getNextNode(), b, root1))
           {
               return true;
           }
        }
        return false;
    }   
}