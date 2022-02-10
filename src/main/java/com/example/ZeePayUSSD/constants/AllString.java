package com.example.ZeePayUSSD.constants;

public enum AllString {
    menuUnregistered("Welcome to C-PAy Wallet. Please press-:\n1.To Register\n2.To Exit"),
    registered("Welcome to C-PAy Wallet. " +
            "-:\n1.Bill Payment\n2.Airtime Purchase\n3.Securities " +
            "payments\n4.Transfer Funds\n5.Loan Payments\n6.Wallet services\n7.My profile"),
    billPayments("Please Select an Option. " +
            "-:\n1.ZESA(Prepaid)\n2.Edgars\n3.DSTV " +
            "payments\n4.Loans Funds\n5. Exit"),
    zesa("Select an Option. " +
            "-:\n1.Purchase token \n2. Retrieve last token"),
    zesaChoice("If select option 1 (purchase token)\n" +
            "1. Self\n" +
            "2. other"),
    entername("Enter your name"),
    entersurname("Enter your Surname"),
    enterdob("Enter your DOB"),
    enterIDNO("Enter your idNo"),
    ConfirmRegDetails("Confirm Registration Details:\n  "),
    airtime("Select an Option\n 1.Econet \n 2.Netone \n 3.Telecel"),
    security("Select an Option \n 1.C-TRADE \n 2.ZSE Direct"),
    loan_payments("Select an Option\n 1.Bank/MFI \n" + "2.Back menu"),
    wallet_service("Select an Option \n 1. My C-PAY Wallet balance \n" +
            "2. Last 5 transactions"),
    transferes("Select an Option \n 1.C-PAY Wallet to Wallet \n 2.C-PAY Wallet to bank \n 3.Bank to C-PAY Wallet");


    public static String[] zesa_self_menus = new String[]
            {"Enter meter number",  //0
                    "Enter Amount",  //1
                    "Enter recipient phone number",//2
                    "Display (display recipients details)- integration",//3
                    "To Confirm details Press \n 1.Confirm \n2.Exit", //4
                    "Send token to recipient number’s SMS",//5
                    "Display token details on screen", //6
                    "Successfully Purchased Zesa Token",//7
                    " Purchase Cancelled", //8
                    "Wrong Input Entry",//9
                    "Exit" //10

            };





    private String txt;

    AllString(String txt) {
        this.txt = txt;
    }

    public String getUrl() {
        return txt;
    }
}
