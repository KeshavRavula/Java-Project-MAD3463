package implementation;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class TransactionImpl {
    UserImpl userImpl = new UserImpl();
    Scanner input = new Scanner(System.in);
    public static AccountImpl accountImpl = new AccountImpl();
    double oldBalance;
    double newBalance;
    String transactions;

    public void withDraw(String accountNo, double amount, String file) {
        oldBalance = accountImpl.DisplayBalance(file);
        if (oldBalance > 0) {
            System.out.println("Enter Amount to withdraw");
            double withdrawAmount = input.nextDouble();
            if (withdrawAmount < oldBalance) {
                newBalance = oldBalance - withdrawAmount;
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
                    transactionObject.put("TransactionType", "Withdrawal");
                    transactionObject.put("TransactionDate", new Date().toString());
                    transactionDetails.add(transactionObject);



                    //JSONObject transactionObject = (JSONObject) transactionDetails.get(transactionDetails.size() - 1);


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

        public void deposit(String accountNo,String file)
        {
            oldBalance = accountImpl.DisplayBalance(file);

                System.out.println("Enter Amount to Deposit");
                double depositAmount = input.nextDouble();

                    newBalance = oldBalance + depositAmount;
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
                        transactionObject.put("TransactionType", "Withdrawal");
                        transactionObject.put("TransactionDate", new Date().toString());
                        transactionDetails.add(transactionObject);



                        //JSONObject transactionObject = (JSONObject) transactionDetails.get(transactionDetails.size() - 1);


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

