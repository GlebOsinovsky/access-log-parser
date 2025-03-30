public class A {
    String str ="";
    private static final A nullA = new A(null);
    private static final A emptyA = new A("");

    private A(String str) {
        this.str = str;
    }
    static A of(String str) {
        if(str==null) return emptyA;
        return new A(str);
    }
    static A ofnul(String str){
        if (str==null) return nullA;
        return new A(str);
    }

    @Override
    public String toString() {
        return "A{" + "str=" + str + '}';
    }
}
