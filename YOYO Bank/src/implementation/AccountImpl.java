package implementation;
import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AccountImpl {
    public double DisplayBalance(String file)
    {
        JSONParser jsonParser=new JSONParser();
        try (FileReader reader=new FileReader(file))
        {
            Object obj=jsonParser.parse(reader);
            JSONObject userObject=(JSONObject)obj;
            JSONObject userDetails=(JSONObject)userObject.get("userDetails");
            //System.out.println(userDetails);

            JSONArray AccountDetails=(JSONArray)userDetails.get("AcountDetails");
            //System.out.println(AccountDetails.toString());
            JSONObject AccountObj=(JSONObject)AccountDetails.get(0);
            double balance=(double)AccountObj.get("Balance");
            System.out.println("Your Account Balance is : "+balance);
            return balance;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
