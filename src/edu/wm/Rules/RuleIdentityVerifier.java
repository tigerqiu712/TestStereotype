package edu.wm.Rules;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodInvocation;

import edu.wm.constants.Assertions;
import edu.wm.constants.TestStereotype;
import edu.wm.core.TestUnderAnalysis;




/**
 * Rule for identity verifier
 * @author Boyang
 *
 */
public class RuleIdentityVerifier extends StereotypeRule{

	/**
	 * Creates a new RuleConditionVerifier using default values for data sets.
	 */
	public RuleIdentityVerifier()  {super(); }


	/**
	 * Classifies the given method. Returns true if the node meets the conditions for this rule, false otherwise.
	 */
	@Override
	protected boolean MakeClassification(TestUnderAnalysis mAnalyzer) {

		ArrayList<MethodInvocation> assertions = mAnalyzer.getAssertionStmts();
		int numOfMatches = 0;
		for(MethodInvocation assertion : assertions){
			String assertionName = getAssertionName(assertion);
			if(assertionName.equals(Assertions.assertSame.name()) || assertionName.equals(Assertions.assertNotSame.name())){
				numOfMatches++;
			}
		}
		if(assertions.size() > 0 && numOfMatches > 0){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public TestStereotype GetMethodStereotype() {
		// TODO Auto-generated method stub
		return TestStereotype.Identity;
	}
}
