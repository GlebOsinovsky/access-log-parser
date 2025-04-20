public class Sum {
    public static void main(String[] args){
        double sum =0.0;
        for (String arg:args) {
            double curNumber = 0.0;
            try {
                curNumber = Double.parseDouble(arg);
            } catch (NumberFormatException e) {
                    curNumber = 0.0;
            }
            sum = sum+curNumber;
        }
        System.out.println((int)sum);
    }
}
