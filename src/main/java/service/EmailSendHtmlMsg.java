package service;

public class EmailSendHtmlMsg {

    public static String htmlVerifyAccount(String patient,String code , String websiteTeam) {
        String html;
        html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Verify your Account</title>\n"
                + "<style type=\"text/css\">body{margin:0;padding:0;background-color:#f7f7f7;font-family:Arial,sans-serif;font-size:14px;line-height:1.5;color:#333;}h1{margin-top:0;font-size:28px;font-weight:bold;color:#333;text-align:center;}p{margin-top:0;margin-bottom:1em;}a{color:#337ab7;text-decoration:none;font-weight:bold;}.container{max-width:600px;margin:0 auto;padding:20px;background-color:#fff;border:1px solid #ddd;box-shadow:0 2px 6px rgba(0,0,0,0.1);}.logo{margin-bottom:20px;text-align:center;}.logo img{max-width:200px;}.signature{margin-top:30px;text-align:right;}.signature p{margin:0;font-size:14px;color:#777;}</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "<div class=\"logo\">\n"
                + "<img src=\"https://pbs.twimg.com/media/FumhXnOWcAAnTJi?format=png&name=small\" alt=\"Logo\">\n"
                + "</div>\n"
                + "<h1>Verify your Account</h1>\n"
                + "<p>Dear " + patient + ",</p>\n"
                + "<p>please Verify you account with this code from the application "+ code + ".</p>\n"
                + "<p>Please contact our office if this email is mistaken.</p>\n"
                + "<p>Thank you for choosing AidMe.</p>\n"
                + "<div class=\"signature\">\n"
                + "<p>The " + websiteTeam + " Team</p>\n"
                + //                     "<p><a href=\"%s\">Visit our website</a></p>\n" +
                "</div>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";



        return html;

    }

    public static String htmlResetPassword(String userName,int code, String websiteTeam) {
        String html = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Reinitialiser votre mot de passe</title>\n"
                +"<style type=\"text/css\">body{margin:0;padding:0;background-color:#f7f7f7;font-family:Arial,sans-serif;font-size:14px;line-height:1.5;color:#333;}h1{margin-top:0;font-size:28px;font-weight:bold;color:#333;text-align:center;}p{margin-top:0;margin-bottom:1em;}a{color:#337ab7;text-decoration:none;font-weight:bold;}.container{max-width:600px;margin:0 auto;padding:20px;background-color:#fff;border:1px solid #ddd;box-shadow:0 2px 6px rgba(0,0,0,0.1);}.logo{margin-bottom:20px;text-align:center;}.logo img{max-width:200px;}.signature{margin-top:30px;text-align:right;}.signature p{margin:0;font-size:14px;color:#777;}</style>\\n"
               // + "<style type=\"text/css\">body{margin:0;padding:0;background-color:#f7f7f7;font-family:Arial,sans-serif;font-size:14px;line-height:1.5;color:#333;}h1{margin-top:0;font-size:28px;font-weight:bold;color:#333;text-align:center;}p{margin-top:0;margin-bottom:1em;}a{color:#337ab7;text-decoration:none;font-weight:bold;}.container{max-width:600px;margin:0 auto;padding:20px;background-color:#fff;border:1px solid #ddd;box-shadow:0 2px 6px rgba(0,0,0,0.1);}.logo{margin-bottom:20px;text-align:center;}.logo img{max-width:200px;}.signature{margin-top:30px;text-align:right;}.signature p{margin:0;font-size:14px;color:#777;}</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"container\">\n"
                + "<div class=\"logo\">\n"
                + "<img src=\"https://pbs.twimg.com/media/FumhXnOWcAAnTJi?format=png&name=small\" alt=\"Logo\">\n"
                + "</div>\n"
                + "<h1>Reset Password</h1>\n"
                + "<p>Dear " + userName + ",</p>\n"
                +"<p>Here is your code to reset your password : "+code+"</p>\n"
                + "<p>rewrite it in the application to change your password  .</p>\n"
                + "<p>.</p>\n"
                + "<div class=\"signature\">\n"
                + "<p>The " + websiteTeam + " Team</p>\n"
                + //                     "<p><a href=\"%s\">Visit our website</a></p>\n" +
                "</div>\n"
                + "</div>\n"
                + "</body>\n"
                + "</html>";


        return  html;

    }






}

