package ca.scamshield.support;

/**
 *
 * @author catherine
 */

public class AccountHackCase extends SupportCase{
    private String platformName;
    private boolean accessRestored;
    
    public AccountHackCase(String caseId, String description,String platformName){
        super(caseId,description);
     
        this.platformName=platformName;
        this.accessRestored=false;
    }
    
    public String getPlatformName(){
        return platformName;
    }
    public boolean isAccessRestored(){
        return accessRestored;
    }
    public void setAccessRestored(boolean accessRestored){
        this.accessRestored=accessRestored;
    }

    @Override
    public String getRecoveryAdvice() {
        return "use the platform's official account recovery process. "
                + "Once access is restored,change your password, "
                + "review active sessions and enable two factor "
                + "authentication.";
    }
    
     @Override
    public String toFileString() {
        return "Account Hack | " + caseId + " | " + description + " | "
                + resolved + " | " + platformName + " | " + accessRestored;
    }       
    @Override
    public String toString() {
        return super.toString()
                + "\nCase Type: Hacked Account"
                + "\nPlatform: "+ platformName
                + "\nAccess Restored: "
                + (accessRestored ? "Yes" : "No")
                + "\nRecovery Advice: " + getRecoveryAdvice(); 
    }
    
   
}
