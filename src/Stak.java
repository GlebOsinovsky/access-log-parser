public class Stak {

    int[] stack = new int[10];
    int cur;
     Stak( int size){
        stack = new int[size];
    };
    public void push( int x) {
        stack[cur++] = x;
    }

    public int pop() {
        return stack[--cur];

    }
}
