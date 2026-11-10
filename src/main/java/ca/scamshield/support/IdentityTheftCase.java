
package ca.scamshield.support;

/**
 *
 * @author catherine
 */
public class IdentityTheftCase extends SupportCase{
    private String documentType;
    public IdentityTheftCase(String caseId,String description,String documentType){
        super(caseId,description);
        this.documentType=documentType;
    }
    public String getDocumentType(){
        return documentType;
    }
    @Override
    public String getRecoveryAdvice(){
        return "Report the suspected misuse to the organisation "
                + "that issued your document.Keep copies of "
                + "suspicious messages and records of the incident.";
    }
     @Override
    public String toFileString() {
        return "Identity Theft | " + caseId + " | " + description + " | "
                + resolved + " | " + documentType;
    }       
    @Override
    public String toString() {
        return super.toString()
                + "\nCase Type: Identity Theft"
                + "\nDocument Type: "+ documentType
                + "\nRecovery Advice: " + getRecoveryAdvice();
    }
    
}
