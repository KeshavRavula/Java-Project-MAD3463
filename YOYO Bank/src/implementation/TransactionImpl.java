package implementation;


import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

public class TransactionImpl {
    UserImpl userImpl = new UserImpl();
    Scanner input = new Scanner(System.in);
    public static AccountImpl accountImpl = new AccountImpl();
    double oldBalance;
    double newBalance;
    String transactions;

    public void withDraw( double amount, String file,String transactionType) {
        oldBalance = accountImpl.DisplayBalance(file);
        if (oldBalance > 0) {

            if (amount < oldBalance) {
                newBalance = oldBalance - amount;
                oldBalance = newBalance;
                //String file="C:/Users/saikrishnaboddu/Documents/New folder/Java-Project-MAD3463/YOYO Bank/Files/"+username+".json";
                try (FileReader reader = new FileReader(file)) {
                    JSONParser jsonParser = new JSONParser();
                    //Read JSON file
                    JSONObject obj = (JSONObject) jsonParser.parse(reader);
                    JSONObject details = (JSONObject) obj.get("userDetails");
                    JSONArray accountDetails = (JSONArray) details.get("AcountDetails");
                    JSONObject accountObj = (JSONObject) accountDetails.get(0);
                    accountObj.put("Balance", newBalance);

                    JSONArray transactionDetails = (JSONArray) details.get("TransactionDetails");
                    JSONObject transactionObject=new JSONObject();
                    transactionObject.put("TransactionID", UUID.randomUUID().toString());
                    transactionObject.put("TransactionType", transactionType);
                    transactionObject.put("TransactionAmount",amount);
                    transactionObject.put("TransactionDate", new Date().toString());
                    transactionDetails.add(transactionObject);

                    FileWriter fileName = new FileWriter(file);
                    fileName.write(obj.toJSONString());
                    fileName.flush();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else
                System.out.println("Insufficient Balance");
            } else
                System.out.println("No balance");
        }

        public void deposit(double amount,String file,String trnsactionType)
        {
                    oldBalance = accountImpl.DisplayBalance(file);
                    newBalance = oldBalance + amount;
                    oldBalance = newBalance;
                    //String file="C:/Users/saikrishnaboddu/Documents/New folder/Java-Project-MAD3463/YOYO Bank/Files/"+username+".json";
                    try (FileReader reader = new FileReader(file)) {
                        JSONParser jsonParser = new JSONParser();
                        //Read JSON file
                        JSONObject obj = (JSONObject) jsonParser.parse(reader);
                        JSONObject details = (JSONObject) obj.get("userDetails");
                        JSONArray accountDetails = (JSONArray) details.get("AcountDetails");
                        JSONObject accountObj = (JSONObject) accountDetails.get(0);
                        accountObj.put("Balance", newBalance);

                        JSONArray transactionDetails = (JSONArray) details.get("TransactionDetails");
                        JSONObject transactionObject=new JSONObject();
                        transactionObject.put("TransactionAmount",amount);
                        transactionObject.put("TransactionID", UUID.randomUUID().toString());
                        transactionObject.put("TransactionType", trnsactionType);
                        transactionObject.put("TransactionDate", new Date().toString());
                        transactionDetails.add(transactionObject);

                        FileWriter fileName = new FileWriter(file);
                        fileName.write(obj.toJSONString());
                        fileName.flush();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

        }
        public void transfer(String fromfile)
        {
            System.out.println("Please enter the receiver's account number");
            String receiver=input.next();
            System.out.println("Please enter amount to transfer");
            double amountToTransfer=input.nextDouble();
            withDraw(amountToTransfer,fromfile,"Transfer");
            String tofile = System.getProperty("user.dir")+"/Files/"+receiver+".json";
           //String tofile="C:/Users/saikrishnaboddu/Documents/New folder/Java-Project-MAD3463/YOYO Bank/Files/"+receiver+".json";
            deposit(amountToTransfer,tofile,"Transfer");
        }


        public String displayTransactions(String file)
        {
            try (FileReader reader = new FileReader(file)) {
                JSONParser jsonParser = new JSONParser();
                //Read JSON file
                JSONObject obj = (JSONObject) jsonParser.parse(reader);
                JSONObject details = (JSONObject) obj.get("userDetails");
                JSONArray accountDetails = (JSONArray) details.get("AcountDetails");
                JSONObject accountObj = (JSONObject) accountDetails.get(0);
                accountObj.put("Balance", newBalance);

                JSONArray transactionDetails = (JSONArray) details.get("TransactionDetails");
                for (int i = 0; i < transactionDetails.size(); i++) {
                    JSONObject trandetails = (JSONObject) transactionDetails.get(i);
                    if(trandetails.size()!=0)
                    {
                        trandetails.get("TransactionID");
                        trandetails.get("TransactionAmount");
                        trandetails.get("TransactionType");
                        trandetails.get("TransactionDate");
                        System.out.println(trandetails.toString());
                    }

                }
                return transactionDetails.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return transactions;
        }

    }