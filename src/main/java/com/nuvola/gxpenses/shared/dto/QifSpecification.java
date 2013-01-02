package com.nuvola.gxpenses.shared.dto;

public class QifSpecification {

    public enum Identifier {
        ACCT_SECTION("[^\\!A(\\S+)]"),
        TRX_END("[^\\^]");

        private String regExp;

        Identifier(String regExp) {
            this.regExp = regExp;
        }
    }

    public enum Account {
        ACCT_NUMBER("[^N(.+)]"),
        ACCT_TYPE("[^T(.+)]");

        private String regExp;

        Account(String regExp) {
            this.regExp = regExp;
        }
    }

    public enum Transaction {
        TRX_NUMBER("[^N(.+)]"),
        TRX_AMOUNT("[^T(.+)]"),
        TRX_DATE("[^D(.+)]"),
        TRX_MEMO("[^M(.+)]"),
        TRX_CLEARED("[^C(.+)]"),
        TRX_PAYEE("[^P(.+)]"),
        TRX_ADDRESS("[^A(.+)]"),
        TRX_CATEGORY("[^L(.+)]"),
        TRX_FLAG("[^F(.+)]"),
        TRX_INVESTMENT("[^N(.+)]"),
        TRX_INVESTMENT_SECURITY("[^Y(.+)]"),
        TRX_INVESTMENT_PRICE("[^I(.+)]"),
        TRX_INVESTMENT_SHARES_QUANTITY("[^Q(.+)]"),
        TRX_INVESTMENT_COMMISSION("[^O(.+)]"),
        TRX_SPLIT_CATEGORY("[^S(.+)]"),
        TRX_SPLIT_MEMO("[^E(.+)]"),
        TRX_SPLIT_PERCENTAGE("[^\\%(.+)]"),
        TRX_SPLIT_OR_INVESTMENT_AMOUNT("[^\\$(.+)]");

        private String regExp;

        Transaction(String regExp) {
            this.regExp = regExp;
        }
    }

}
