/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanjeevaniapp.utility;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

/**
 *
 * @author hp
 */
public class OTPSender implements Sender{

    @Override
    public boolean send(String number, String data) throws Exception {
         Unirest.setTimeouts(0, 0);
         String url = "https://2factor.in/API/V1/ae84ffaa-64e5-11ed-9c12-0200cd936042/SMS/"+number+"/"+data+"/OTP1";
         System.out.println(data);
         GetRequest gr = Unirest.get(url);
         HttpResponse<String> response = gr.asString();
         String result = response.getBody();
         return result.contains("Success");
    }
    
}
