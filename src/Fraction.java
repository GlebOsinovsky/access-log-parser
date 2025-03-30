public class Fraction {
   private final int numerator;
   private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator==0) throw new IllegalArgumentException("Знаменатель не может быть 0");

        if (denominator < 0) {
            this.numerator = -numerator;
            this.denominator = -denominator ;
        } else {
            this.numerator =numerator;
            this.denominator =denominator;
        };
    }
    public Fraction sum(Fraction anotherFraction){
        int newNumerator= this.numerator*anotherFraction.denominator + this.denominator*anotherFraction.numerator;
        int newDenominator = anotherFraction.denominator*this.denominator;
        return new Fraction(newNumerator,newDenominator);
    }
    public Fraction sumInt(int number){
        return this.sum(new Fraction(number,1));
    }
    public Fraction minus(Fraction anotherFraction){
        int newNumerator= this.numerator*anotherFraction.denominator - this.denominator*anotherFraction.numerator;
        int newDenominator = anotherFraction.denominator*this.denominator;
        return new Fraction(newNumerator,newDenominator);
    }
    public Fraction minusInt (int number){
        return this.minus(new Fraction(number,1));
    }
    public String toString() {
        return  numerator +"/"+ denominator ;
    }
}
