package implementation;

import com.fasterxml.jackson.core.JsonParser;
import models.Account;
import models.Address;
import models.User;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Scanner;

public class UserImpl {
    JSONParser jsonParser = new JSONParser();
    static Scanner input = new Scanner(System.in);
    Account userAccount = new Account();
    String fileLocation;

    public User Register(Scanner input) {
        JSONObject userJson = new JSONObject();
        JSONObject Account = new JSONObject();
        JSONObject Transaction = new JSONObject();
        User user = new User();
        System.out.println("Please enter your SIN");
        String SIN = input.next();
        user.setSIN(SIN);
        userJson.put("SIN", user.getSIN());
        System.out.println("Please enter your Name");
        String name = input.next();
        user.setName(name);
        userJson.put("Name", user.getName());
        System.out.println("Please enter your Phone Number");
        String phNo = input.next();
        user.setPhoneNumber(phNo);
        userJson.put("PhoneNo", user.getPhoneNumber());
        System.out.println("Please enter your E-Mail");
        String email = input.next();
        user.setEmail(email);
        userJson.put("Email", user.getEmail());
        System.out.println("Please enter your Address");
        Address addr = new Address();
        System.out.println("Enter Address1");
        String addr1 = input.next();
        addr.setAddress1(addr1);
        userJson.put("Address1", addr.getAddress1());
        System.out.println("Enter Address2");
        String addr2 = input.next();

        addr.setAddress2(addr2);
        userJson.put("Address2", addr.getAddress2());
        System.out.println("Enter State");

        String state = input.next();

        addr.setState(state);
        userJson.put("State", addr.getState());
        System.out.println("Enter City");
        String city = input.next();
        addr.setCity(city);
        userJson.put("City", addr.getCity());
        System.out.println("Enter PinCode");
        String pincode = input.next();
        addr.setPin(pincode);
        userJson.put("Pincode", addr.getPin());
        user.setAddress(addr);
        while (true) {
            System.out.println("Please Select the type of account\n 1) Checking\n 2) Savings");
            int accountType = 0;
            accountType = input.nextInt();
            if (accountType == 1) {
                userAccount.setAccountType("Checking");
                break;
            } else if (accountType == 2) {
                userAccount.setAccountType("Savings");
                break;
            } else {
                System.out.println("Please select a valid Account Type");
            }
        }
        ;
        Account.put("AcountType", userAccount.getAccountType());

        System.out.println("Please set a Password");
        String pass = input.next();
        user.setPassword(pass);
        userJson.put("Password", user.getPassword());


        userAccount.setAccountNo(SIN);
        Account.put("AccountNo", userAccount.getAccountNo());
        userAccount.setBalance(0);
        Account.put("Balance", userAccount.getBalance());

        fileLocation = System.getProperty("user.dir")+"/YOYO Bank/Files/";
        JSONObject userObject = new JSONObject();
        JSONArray accountArray = new JSONArray();
        accountArray.add(Account);
        JSONArray tranxArray = new JSONArray();
        tranxArray.add(Transaction);
        userJson.put("AcountDetails", accountArray);
        userJson.put("TransactionDetails", tranxArray);
        userObject.put("userDetails", userJson);

        try (FileWriter file = new FileWriter(fileLocation + SIN + ".json")) {
            file.write(userObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;

    }

    public String Login() {
        System.out.println("Please enter username");
        String username = input.next();
        System.out.println("Please enter password");
        String pass = input.next();
        String file=System.getProperty("user.dir")+"/YOYO Bank/Files/";
       // String file = System.getProperty("user.dir");
        file += username + ".json";
        try (FileReader reader = new FileReader(file)) {

            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONObject details = (JSONObject) obj.get("userDetails");

            if (pass.equals(details.get("Password"))) {
                System.out.println("Login Successful");
                return username;
            } else {
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
        return null;
    }


    public void editProfile(String file) {

        try (FileReader reader = new FileReader(file)) {
            JSONParser jsonParser = new JSONParser();
            //Read JSON file
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONObject details = (JSONObject) obj.get("userDetails");
            FileWriter fileName = new FileWriter(file);
            int choice;
            do {
                System.out.println("\n Please Select an option: \n 1) Change Phone Number \n 2) Change Email \n 3) Change Password \n 4) Return to Main Screen");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter new Phone Number:");
                        details.put("PhoneNo",input.next());
                        fileName.write(obj.toJSONString());
                        fileName.flush();
                        break;
                    case 2:
                        System.out.println("Enter new Email:");
                        details.put("Email",input.next());
                        fileName.write(obj.toJSONString());
                        fileName.flush();
                        break;
                    case 3:
                        System.out.println("Enter new Password:");
                        details.put("Password",input.next());
                        fileName.write(obj.toJSONString());
                        fileName.flush();
                        break;
                    case 4:
                        break;
                }
            } while (choice != 4);
            choice = 0;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
