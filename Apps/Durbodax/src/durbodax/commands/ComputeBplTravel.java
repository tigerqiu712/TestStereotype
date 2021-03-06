package durbodax.commands;

import durbodax.customers.*;
import durbodax.daos.*;
/**
 * ComputeBplTravel calculates a travel amount based on BirthPlace
 * @author gbeckenbaugh
 */
public class ComputeBplTravel implements Command {

    /**
     * default constructor
     */
    public ComputeBplTravel() {}

    /**
     * execute - main processing for the Command
     * @param params - String[] from command line
     * @return retObj - Object containing the results
     */
    public Object execute(String[] params) {

        int customerId = 0;
        int travelAmt = 0;
        Object retObj = new Object();

        Customer c;
        String bpl = null;

        customerId = Integer.parseInt(params[1]);
        CustomerDAO custDAO = DAOFactory.getDAOFactory().getCustomerDAO();

        c = custDAO.selectCustomer(customerId);
        if (c != null){
            bpl = c.getBirthplace().toLowerCase();
            if (bpl.contains("europe")){
                travelAmt = 500;
            }else if (bpl.contains("south america")){
                travelAmt = 300;
            }else if (bpl.contains("africa")){
                travelAmt = 1000;
            }else if (bpl.contains("asia")){
                travelAmt = 888;
            }
            retObj = travelAmt;
             System.out.println("ComputeBplTravel amount for customer id: " +
                Integer.toString(c.getId()) + " = " + Integer.toString(travelAmt));
        } else {
             System.out.println("Customer: " + customerId + " not Found");
             retObj = null;
        }
        return retObj;
    }
}
