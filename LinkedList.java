import java.util.Scanner;

public class LinkedList {
    public static void main(String[] args) {

        char cont = 'y';
        LL num1;
        LL num2;
        LL num3;

        
        while((cont == 'y') || (cont == 'Y')){
            // Declare and initialize the objs created
            num1 = new LL();
            num2 = new LL();
            num3 = new LL();

            Scanner s = new Scanner(System.in);

            //prompting the user for list 1
            System.out.println("Enter the number for list 1 : ");
            String number1 = s.next();

            //prompting the user for list 2
            System.out.println("Enter the number for list 2 : ");
            String number2 = s.next();

            String operation;

            //Starting the while loop to continiously prompt the user to enter the correct operation
            int flag = 0;
            while (flag == 0){

                //taking input from the user to choose the operation
                System.out.println("\nEnter the operation (+,-,/,*) : ");
                operation = s.next();
                
                //matching the condition of the operation and calling functions from linked list object
                switch(operation){
                    case "+":
                        for (int i = 0 ; i < number1.length(); i++){
                            num1.insertiononhead(Character.getNumericValue(number1.charAt(i)));
                        }
                        for (int i = 0 ; i < number2.length(); i++){
                            num2.insertiononhead(Character.getNumericValue(number2.charAt(i)));
                        }
                        num3.sumall(num1 , num2);
                        flag = 1;
                        break;
                    case "-":
                        for (int i = 0 ; i < number1.length(); i++){
                            num1.insertiononhead(Character.getNumericValue(number1.charAt(i)));
                        }
                        for (int i = 0 ; i < number2.length(); i++){
                            num2.insertiononhead(Character.getNumericValue(number2.charAt(i)));
                        }
                        num3.subtractall(num2, num1);
                        flag = 1;
                        break;
                    case "*":
                        for (int i = 0 ; i < number1.length(); i++){
                            num1.insertion(Character.getNumericValue(number1.charAt(i)));
                        }
                        for (int i = 0 ; i < number2.length(); i++){
                            num2.insertion(Character.getNumericValue(number2.charAt(i)));
                        }
                        num3.multiplyall(num1 , num2);
                        flag = 1;
                        break;
                    case "/":
                        for (int i = 0 ; i < number1.length(); i++){
                            num1.insertion(Character.getNumericValue(number1.charAt(i)));
                        }
                        for (int i = 0 ; i < number2.length(); i++){
                            num2.insertion(Character.getNumericValue(number2.charAt(i)));
                        }
                        num3.divideall(num1 , num2);
                        flag = 1;
                        break;
                    default:
                        System.out.println("\nInvalid operation!\n");
                        continue;
                }
            }

            //printing the answer
            num3.printfunc();

            System.out.println("\nDo you want to continue (y/n) : ");
            cont = s.next().charAt(0);
            
            num1 = null;
            num2 = null;
            num3 = null;
        }
        
        return;
    }
}

//node class to create the linked list
class node{
    public int data;
    public node next;

    public node(int d){
        this.data = d;
        this.next = null;
    }
}

//linked list class
class LL{
    public node head;

    public LL (){
        head = null;
    }

    //basic insertion at head function of linked list
    public void insertiononhead(int data){
        if (this.head == null){
            this.head = new node(data);
            return;
        }

        // node temp;
        // temp = this.head;
        // while(temp.next != null){
        //     temp = temp.next;
        // }

        node temp1 = new node(data);

        temp1.next = this.head;
        this.head = temp1;
        return;
    }

    //basic insertion function of linked list
    public void insertion(int data){
        if (this.head == null){
            this.head = new node(data);
            return;
        }

        node temp;
        temp = this.head;
        while(temp.next != null){
            temp = temp.next;
        }

        node temp1 = new node(data);
        temp.next = temp1;
        return;
    }

    //function to calculate the sum of all the nodes and store the answer in a new node
    public void sumall(LL obj1 , LL obj2){
        node temp = obj1.head;
        node temp1 = obj2.head;

        int x = 0;

        while(temp1 != null || temp != null){
            if(temp1 == null){
                insertiononhead(temp.data + x);
                temp = temp.next;
                continue;
            }
            if(temp == null){
                insertiononhead(temp1.data + x);
                temp1 = temp1.next;
                continue;
            }

            x += temp.data + temp1.data;
            insertiononhead(x % 10);
            x = x / 10;
            temp = temp.next;
            temp1 = temp1.next;
        }

    }



    //function to calculate the subraction of all the nodes and store the answer in a new node
    public void subtractall(LL obj1, LL obj2){
        Substract ob = new Substract();
        node temp = ob.subtractLinkedList(obj1.head, obj2.head);

        while(temp != null){
            insertion(temp.data);
            temp = temp.next;
        }
        if(ob.borrow == false){
            System.out.print("-");
        }
    }

    //function to calculate the multiplication of all the nodes and store the answer in a new node
    public void multiplyall(LL obj1, LL obj2)
    {
        node first = obj1.head;
        node second = obj2.head;
        long N = 1000000007;
        long num1 = 0, num2 = 0;
        while (first != null || second != null){
            
            if(first != null){
                num1 = ((num1)*10)%N + first.data;
                first = first.next;
            }
            
            if(second != null)
            {
                num2 = ((num2)*10)%N + second.data;
                second = second.next;
            }
            
        }
        String y = Long.toString(((num1%N)*(num2%N))%N);

        for (int i = 0 ; i < y.length(); i++){
            insertion(Character.getNumericValue(y.charAt(i)));
        }

    }


    //function to calculate the division of all the nodes and store the answer in a new node
    public void divideall(LL obj1 , LL obj2){
        node temp = obj1.head;
        node temp1 = obj2.head;

        long x = 0 , y = 0;
        while(temp != null){
            x += temp.data;
            if(temp.next != null){
                x *= 10;
            }
            temp = temp.next;   
        }

        while(temp1 != null){
            y += temp1.data;
            if(temp1.next != null){
                y *= 10;
            }
            temp1 = temp1.next;   
        }

        if(x < y){
            insertion(0);
            return;
        }
        else if(x == y){
            insertion(1);
        }
        else{
            long n =  x / y;
            int m = (int) n;
            while(m != 0){
                insertion(m % 10);
                m = m/10;
            }

        }
        
    }

    public void printfunc(){
        if(this.head == null){
            System.out.println("\nThe list is empty.\n");
            return;
        }

        node temp = this.head;

        while(temp != null){
            System.out.print(temp.data);
            temp = temp.next;
        }

    }

    public void printing(node head){
        if(head == null){
            return;
        }
        printing(head.next);
        System.out.println(head.data);
    }


}



class Substract 
{
    static node head; 
    boolean borrow;

    /* A utility function to get length of linked list */
    public int getLength(node node)
    {
        int size = 0;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }
    /* A Utility that padds zeros in front
    of the Node, with the given diff */
    public node paddZeros(node sNode, int diff)
    {
        if (sNode == null)
            return null;

        node zHead = new node(0);
        diff--;
        node temp = zHead;
        while ((diff--) != 0) {
            temp.next = new node(0);
            temp = temp.next;
        }
        temp.next = sNode;
        return zHead;
    }
    /* Subtract LinkedList Helper is a recursive
    function, move till the last Node, and
    subtract the digits and create the Node and
    return the Node. If d1 < d2, we borrow the
    number from previous digit. */
    public node subtractLinkedListHelper(node l1, node l2)
    {
        if (l1 == null && l2 == null && borrow == false)
            return null;

        node previous
            = subtractLinkedListHelper(
                (l1 != null) ? l1.next
                            : null,
                (l2 != null) ? l2.next : null);

        int d1 = l1.data;
        int d2 = l2.data;
        int sub = 0;

        /* if you have given the value to
        next digit then reduce the d1 by 1 */
        if (borrow) {
            d1--;
            borrow = false;
        }

        /* If d1 < d2, then borrow the number from
        previous digit. Add 10 to d1 and set
        borrow = true; */
        if (d1 < d2) {
            borrow = true;
            d1 = d1 + 10;
        }

        /* subtract the digits */
        sub = d1 - d2;

        /* Create a Node with sub value */
        node current = new node(sub);

        /* Set the Next pointer as Previous */
        current.next = previous;

        return current;
    }
    /* This API subtracts two linked lists and
    returns the linked list which shall have the
    subtracted result. */
    public node subtractLinkedList(node l1, node l2)
    {
        // Base Case.
        if (l1 == null && l2 == null)
            return null;

        // In either of the case, get the lengths
        // of both Linked list.
        int len1 = getLength(l1);
        int len2 = getLength(l2);

        node lNode = null, sNode = null;

        node temp1 = l1;
        node temp2 = l2;

        // If lengths differ, calculate the smaller
        // Node and padd zeros for smaller Node and
        // ensure both larger Node and smaller Node
        // has equal length.
        if (len1 != len2) {
            lNode = len1 > len2 ? l1 : l2;
            sNode = len1 > len2 ? l2 : l1;
            sNode = paddZeros(sNode, Math.abs(len1 - len2));
        }

        else {
            // If both list lengths are equal, then
            // calculate the larger and smaller list.
            // If 5-6-7 & 5-6-8 are linked list, then
            // walk through linked list at last Node
            // as 7 < 8, larger Node is 5-6-8 and
            // smaller Node is 5-6-7.
            while (l1 != null && l2 != null) {
                if (l1.data != l2.data) {
                    lNode = l1.data > l2.data ? temp1 : temp2;
                    sNode = l1.data > l2.data ? temp2 : temp1;
                    break;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        // After calculating larger and smaller Node,
        // call subtractLinkedListHelper which returns
        // the subtracted linked list.
        borrow = false;
        return subtractLinkedListHelper(lNode, sNode);
    }
}
