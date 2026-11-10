
package ca.scamshield.support;

/**
 *
 * @author catherine
 */public abstract class SupportCase {
    
    protected String caseId; 
    protected String description;
    protected boolean resolved;
    
    public SupportCase(String caseId,String description){
        this.caseId=caseId;
        this.description=description.trim();
        this.resolved=false;
    }
    public String getCaseId(){
        return caseId;
    }
    public String getDescription(){
        return description;
    }
    public boolean isResolved(){
        return resolved;
    }
    public void markResolved(){
        resolved=true;
    }
    public abstract String getRecoveryAdvice();
    
    public abstract String toFileString();
    
    @Override
    public String toString(){
        return "Case ID:"+ caseId
                +"\nDescription: "+ description
                +"\nStatus: "+ (resolved ? "Resolved" : "Open");
  
        
    }
}
