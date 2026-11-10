
package ca.scamshield.support;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author catherine
 */
public class CaseList {
    private ArrayList<SupportCase>cases;
    
  
    
    public CaseList(){
        cases=new ArrayList<>();
    }
    //Allow adding a case is its caseId is not already used
    public void addCase(SupportCase supportCase){
        if(supportCase != null
            && findById(supportCase.getCaseId())==null){
         
        cases.add(supportCase);
        }
    }
    //Find a case using its ID
    public SupportCase findById(String caseId){
        for(SupportCase supportCase : cases){
            if(supportCase.getCaseId().equals(caseId)){
                return supportCase;
            }
        }
        return null;
    }
    //Remove case using the caseId
    public boolean removeCase(String caseId){
        SupportCase supportCase=findById(caseId);
        
        if(supportCase == null){
            return false;
        }
        cases.remove(supportCase);
        return true;
    }
    //Show a copy of the list
    public ArrayList<SupportCase>getAllCases(){
            return new ArrayList<>(cases);
        }
    //Mark a case as resolved          
    public boolean markCaseResolved(String caseId){
        SupportCase supportCase = findById(caseId);
        
        if(supportCase == null){
            return false;
        }
        supportCase.markResolved();
        return true;
    }
    //Save each case
    public void saveToFile() throws IOException{
        try(PrintWriter output=new PrintWriter("support-cases.txt")){         
             
            for(SupportCase supportCase : cases){
                output.println(supportCase.toFileString());
            }if(output.checkError()){
                throw new IOException("Could not save cases.");
            }
        }
    }
    //Load case from file
    public void loadFromFile() throws IOException {
        File file=new File("support-cases.txt");
        
        if(!file.exists()){
            return;
        }
        //keep current cases until loading succeeds
        ArrayList<SupportCase>loadedCases=new ArrayList<>();
        try(Scanner input=new Scanner(file)){
                
            while(input.hasNextLine()){
                String line=input.nextLine();
                
                if(line.trim().isEmpty()){
                    continue;
                }
                String[] data=line.split("\\|",-1);
                                 
                if(data.length < 5){
                    throw new IOException("Incomplete case Details.");
                }
                String caseId=data[1];
                String description=data[2];
                SupportCase supportCase;
                
            
                if(!caseId.matches("(FL|AH|IT|OT)(0[1-9]|1[0-2])[0-9]{2}")){
                    throw new IOException("Invalid case ID in file.");
                }
                    if(data.equals("Financial Loss")){
                        if(data.length != 6){
                        throw new IOException("Invalid financial loss details.");
                    }
                    double amount=Double.parseDouble(data[4]);
                    
                    supportCase=new FinancialLossCase(caseId,description,amount,data[5]);
                
                }else if(data[0].equals("Identity Theft")){
                    if(data.length !=5){
                        throw new IOException("Invalid identity theft details.");
                    }
                    supportCase=new IdentityTheftCase(caseId,description,data[4]);
                            
                    } else if (data[0].equals("Account Hack")){
                        if(data.length !=6){
                            throw new IOException("Invalid access restored value.");
                        }
                    AccountHackCase accountCase=new AccountHackCase(caseId,description,data[4]);
                            
                    accountCase.setAccessRestored(Boolean.parseBoolean(data[5]));
                          supportCase=accountCase;
                            
                      }else {
                            throw new IOException("Unknown case type.");
                    }   
                    if(!data[3].equalsIgnoreCase("True")&& !data[3].equalsIgnoreCase("false")){
                        throw new IOException("Invalid resolved value");
                    }
                        if(Boolean.parseBoolean(data[3])){
                            supportCase.markResolved();
                        }
                    //check for duplicate IDS in the file
                    for(SupportCase existingCase : loadedCases){
                        if(existingCase.getCaseId().equals(caseId)){
                            throw new IOException("Duplicate case ID in file.");
                        }
                    }
                       loadedCases.add(supportCase);
                    }
                    if(input.ioException() != null){
                        throw new IOException();
                    }
                    }catch (IllegalArgumentException e){
                        throw new IOException("Invalid case details in file.",e);
                    }
                     cases=loadedCases;   
                    
        
    }
}

