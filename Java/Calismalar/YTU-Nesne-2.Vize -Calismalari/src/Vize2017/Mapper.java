package Vize2017;

import java.io.*;
import java.util.HashMap;

public class Mapper {
    private HashMap<String , String > countries ;
    public Mapper (String []countryCodes, String[] names) throws CountryException{
        countries = new HashMap<>();
        int i=0;
        for ( String x : countryCodes){
            addCountry(x,names[i]);
            i++;
        }
    }
    public String getCountryName (String countryCode) throws CountryException{

       if( null != countries.get(countryCode)) return countries.get(countryCode);
       throw new CountryException("Bu sehir yok");
    }
    public void addCountry(String countryCode , String countryName) throws CountryException {
            if(null==getCountryName(countryCode))
            countries.put(countryCode,countryName);
            else throw new CountryException("Code aldready exists:");
    }
    public void loadCountries ( String path){
        try{
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(path));
            countries = (HashMap <String , String> )reader.readObject();
reader.close();
        }catch ( IOException e){
            e.printStackTrace();
        }catch ( ClassNotFoundException y){
            y.printStackTrace();
        }

    }
    public void saveCountries ( String path){
        try{
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(path, true));
            writer.writeObject(countries);
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
