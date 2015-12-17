package durbodax.commands.customer;

import durbodax.commands.Command;
import durbodax.customers.CustomerData;

/**
 *
 * @author Kirk
 */
public class UpdateNumberOfMothers implements Command {

    public Object execute(String[] input) {

        CustomerData.CUSTOMER.getCustomer().setNumMothersInHouse(Integer.parseInt(input[1]));
        return 0;

    }

}
