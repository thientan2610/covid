package com.thientan.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.thientan.login.ChangePwd;
import com.thientan.login.CreateAdmin;
import com.thientan.login.LoginForm;
import com.thientan.service.AccountService;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ManagementCovid {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            int init = AccountService.countAccount();

            if (init == 0) {
                CreateAdmin.main();
            } else {
                LoginForm.main();
            }

//            Admin_Main.main();
//            Manager_Main.main();
//            User_Main.main("BN0001");           

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
