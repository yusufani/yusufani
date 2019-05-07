package Vize2017;

public class Person {
    private  int id;
    private String name;
    private Passport passport;
    public Person ( String name , int id){
        this.id= id;
        this.name= name;
    }
    public void setPassport( Passport passport) {
        this.passport = passport;
    }

    public Passport getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return name+passport.getCountryName();
    }
}
