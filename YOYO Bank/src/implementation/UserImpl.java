package implementation;

import com.fasterxml.jackson.core.JsonParser;
import models.Address;
import models.User;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class UserImpl{
    JSONParser jsonParser=new JSONParser();
 static Scanner input=new Scanner(System.in);
    public User Register(Scanner input)
    {
        JSONObject userJson=new JSONObject();
        User user=new User();
        System.out.println("Please enter your SIN");
        String SIN=input.next();
        user.setSIN(SIN);
        userJson.put("SIN",user.getSIN());
        System.out.println("Please enter your Name");
        String name=input.next();
        user.setName(name);
        userJson.put("Name",user.getName());
        System.out.println("Please enter your Phone Number");
        String phNo=input.next();
        user.setPhoneNumber(phNo);
        userJson.put("PhoneNo",user.getPhoneNumber());
        System.out.println("Please enter your E-Mail");
        String email=input.next();
        user.setEmail(email);
        userJson.put("Email",user.getEmail());
        System.out.println("Please enter your Address");
        Address addr=new Address();
        System.out.println("Enter Address1");
        String addr1=input.next();
        addr.setAddress1(addr1);
        userJson.put("Address1",addr.getAddress1());
        System.out.println("Enter Address2");
        String addr2=input.next();
        addr.setAddress2(addr2);
        userJson.put("Address2",addr.getAddress2());
        System.out.println("Enter State");
        String state=input.next();
        addr.setState(state);
        userJson.put("State",addr.getState());
        System.out.println("Enter City");
        String city=input.next();
        addr.setCity(city);
        userJson.put("City",addr.getCity());
        System.out.println("Enter PinCode");
        String pincode=input.next();
        addr.setPin(pincode);
        userJson.put("Pincode",addr.getPin());
        user.setAddress(addr);
        System.out.println("Please set a Password");
        String pass=input.next();
        user.setPassword(pass);
        userJson.put("Password",user.getPassword());


            String fileLocation="C:/Users/saikrishnaboddu/Documents/New folder/Java-Project-MAD3463/YOYO Bank/Files/";
            JSONObject userObject = new JSONObject();
            userObject.put("userDetails",userJson);

            try (FileWriter file = new FileWriter(fileLocation+SIN+".json")){
                file.write(userObject.toJSONString());
                file.flush();
        }
        catch (IOException e) {
                e.printStackTrace();
            }

        return user;

    }
    public void Login()
    {
        System.out.println("Please enter username");
        String username=input.next();
        System.out.println("Please enter password");
        String pass=input.next();
        try (FileReader reader = new FileReader("C:/Users/saikrishnaboddu/Documents/New folder/Java-Project-MAD3463/YOYO Bank/Files/"+username+".json"))
        {

            //Read JSON file
            JSONObject obj =(JSONObject) jsonParser.parse(reader);
            JSONObject details=(JSONObject)obj.get("userDetails");

            if(pass.equals(details.get("Password"))) {
                System.out.println("Login Successful");
                System.out.println(obj);
            }
            
            else
            {
                System.out.println("Wrong pass");
            }
            //Iterate over employee array


        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("User does not Exist (Please resgister)");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
