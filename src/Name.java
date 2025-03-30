public class Name {
    String personalName="Pete";
    String familyName="";
    String patronomic="";

    public Name(String personalName, String familyName, String patronomic) {
        if (personalName!=null) this.personalName = personalName;
        if (familyName!=null) this.familyName = familyName;
        if (patronomic!=null) this.patronomic = patronomic;
    }

    public static Name ofPersonalNameAndFamilyname(String personalName,String familyName){
        return new Name(personalName,familyName,"");
    };
    public static Name ofPersonalNameAndPatronomic(String personalName,String patronomic){
        return new Name(personalName,"",patronomic);
    };

    public Name (String personalName){
        this(personalName,"","");
    };


    @Override
    public String toString() {
        return "Name{" +
                "personalName='" + personalName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", patronomic='" + patronomic + '\'' +
                '}';
    }
}
