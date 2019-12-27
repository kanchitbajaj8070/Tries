import java.util.HashMap;
import java.util.Map;

//Time Coplexity ADD()-> O( length of the word)
// Search() -> O( length of word)
// Remove() -> O( length of word)
public class Tries {
    public Node root;
    int numWords=0;
    Tries()
    {
this.root= new Node('\0',false);//null character symbol
        numWords=0;

    }
    public int noOfWords()
    {
        return this.numWords;
    }
    public class Node
    {
        char data;
        HashMap<Character, Node> childrens;
        boolean isTerminal;
        Node ( char data, boolean isTerminal)
        {
            this.data=data;
            this.isTerminal=isTerminal;
            childrens= new HashMap<>();
        }

    }
    public void addWord( String word)
    {
        addWord(root,word);
    }

    private void addWord(Node parent, String word) {
        if(word.length()==0)
        {
           if( parent.isTerminal)
           {
               //already exists

           }
           else
               { parent.isTerminal=true;
               this.numWords++;
           }
           return;
        }
    char cc=word.charAt(0);
    String ros=word.substring(1);
    Node children=parent.childrens.get(cc);
    if(children==null) {
        children = new Node(cc, false);
        parent.childrens.put(cc, children);
    }
        addWord(children,ros);

    }
    public void display()
    {
        display(root,"");
    }

    private void display(Node root, String outputSoFar)
    {
        if(root.isTerminal==true)
            System.out.println(outputSoFar);
        HashMap<Character, Node> trav=root.childrens;
        for(Map.Entry<Character,Node> entry: trav.entrySet())
        {
            display(entry.getValue(),outputSoFar+entry.getKey());
        }



    }
    public void isPresent( String word)
    {
        System.out.println(isPresent(root,word));
    }

    private boolean isPresent(Node root, String word) {
            if(root.isTerminal==true&& word.length()==0)
                return true;
            if(root.isTerminal==false&&word.length()==0)
                return false;
            char cc= word.charAt(0);
            String ros=word.substring(1);
            Node children=root.childrens.get(cc);
            if(children==null)
                return false;
            else
                return isPresent(children,ros);
    }
    public void remove( String word)
    {
        remove(root,word);
    }

    private void remove(Node parent, String word) {
        if(word.length()==0) {
            if (parent.isTerminal)
            {
                parent.isTerminal=false;
                this.numWords--;
            }
            else
            {
                // word is a part of sme other words so do nothing
                return;
            }
            return;
        }

        char cc=word.charAt(0);
        String ros=word.substring(1);
        Node children=parent.childrens.get(cc);
        if(children==null)
            return;
            remove(children,ros);
            //tricky part to remove unnecessary nodes to save memory
        // if child is last node at each level then remove it as it is not useful
        if(children.isTerminal==false&& children.childrens.size()==0)
        {
            parent.childrens.remove(cc);
        }

    }
}

