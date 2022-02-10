package com.example.ZeePayUSSD.constants;

public enum Pages {
    Reg0("0"),
    Reg1("1"),
    Reg2("2"),
    Reg3("3"),
    Reg4("4");
    private String s;

    Pages(String s) {
        this.s = s;
    }
    public String getUrl() {
        return s;
    }
}
