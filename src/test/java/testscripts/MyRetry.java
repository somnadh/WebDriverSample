package testscripts;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetry {

	public class RetryAnalyzer implements IRetryAnalyzer {

	    private int retryCount = 0;       // Current retry attempt
	    private static final int maxRetryCount = 3; // Maximum retry attempts

	    /**
	     * This method is invoked by TestNG after a test fails.
	     * @param result The result of the test method that just ran.
	     * @return true if TestNG should retry the test, false otherwise.
	     */
	    @Override
	    public boolean retry(ITestResult result) {
	    	if(result.getStatus()==ITestResult.FAILURE) {
	        if (retryCount < maxRetryCount) {
	            retryCount++;
	            System.out.println("Retrying test: " + result.getName() +
	                               " | Attempt " + retryCount + " of " + maxRetryCount);
	            return true; // Retry the test
	        }
	    	}
	        return false; // No more retries
	    }
	}
}
