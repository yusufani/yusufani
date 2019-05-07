package Vize2017;

public class Test {
    String[] a = {"16", "24", "34"};
    String[] b = {"Bursa ", " NERESI", "ISTANBUL"};
    Person x = new Person("Yusuf", 16);
    Person y = new Person("Ahmet", 24);
    Person z = new Person("Mehmet ", 34);
    try{
    Mapper mapper = new Mapper(a, b);
    Passport pas1 = new Passport("16", 111, x, mapper);
    Passport pas2 = new Passport("24", 234, y, mapper);
        x.setPassport(pas1);
        y.setPassport(pas2);
}catch(CountryException e ){
        e.printStackTrace();
    }
    Thread t ;
    t = new Thread( new IntroductionTask(x));


}
class IntroductionTask implements  Runnable{
private  final Person person;
    public IntroductionTask(Person person){
        this.person = person;
    }

    public void run() {
        System.out.println(person);
    }
}