package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    private static final double currentMinBalance = 5000.0;

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance, currentMinBalance);
        if(balance < currentMinBalance) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        tradeLicenseId = this.validateLicenseId(tradeLicenseId);
        this.tradeLicenseId = tradeLicenseId;
    }

    public String validateLicenseId(String tradeLicenseId) throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        char[] charArray = tradeLicenseId.toCharArray();
        for (int i = 0; i < charArray.length-1; i++) {
            if(charArray[i] == charArray[i+1]) {
                // right subarray
                boolean isRearangeChar = false;
                for(int k = i+2; k < charArray.length; k++) {
                    if(charArray[k] != charArray[i]) {
                        charArray[i+1] = charArray[k];
                        charArray[k] = charArray[i];
                        isRearangeChar = true;
                        break;
                    }
                }
                if(!isRearangeChar) {
                    for (int k = 0; k < i; k++) {
                        if(!(charArray[k] == charArray[i] || (k > 0 && charArray[k-1] == charArray[i]) || (k < i-1 && charArray[k+1] == charArray[i]))) {
                            charArray[i+1] = charArray[k];
                            charArray[k] = charArray[i];
                            isRearangeChar = true;
                            break;
                        }
                    }
                }
                if(!isRearangeChar) {
                    throw new InvalidLicenseIdException("Valid License can not be generated");
                }
            }
        }
        return new String(charArray);
    }

}
