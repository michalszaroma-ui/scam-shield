
package ca.scamshield.support;

/**
 *
 * @author catherine
 */
    
public class FinancialLossCase extends SupportCase{
    private double amountLost;
    private String paymentMethod;
    
    public FinancialLossCase(String caseId,String description,double amountLost,String paymentmethod){
        super(caseId,description);
          
        this.amountLost=amountLost;
        this.paymentMethod=paymentMethod.trim();
    }
    public double getAmountLost(){
        return amountLost;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }

    @Override
    public String getRecoveryAdvice() {
        return "Contact your payment provider to report the transaction"
                + "and ask about recovery options.Keep payment records "
                + "and copies of messages as evidence.";
    }
    @Override
    public String toFileString() {
        return "Financial Loss | " + caseId + " | " + description + " | "
                + resolved + " | " + amountLost + " | " + paymentMethod;
    }       
    @Override
    public String toString() {
        return super.toString() 
                + "\nCase Type: Financial Loss"
                + "\nAmount Lost:EUR " + amountLost
                + "\nPayment Method: " + paymentMethod
                + "\nRecovery Advice: " + getRecoveryAdvice();
    }             
    
}
