package com.huawei.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/jnlp")
public class JnlpController {
    @RequestMapping("/make")
    public void makeJnlp(@RequestParam("url") String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(url);
        String protocol = request.getScheme();
        String ip = request.getServerName();
        int port = request.getServerPort();
        String app = request.getContextPath();
        String context = request.getServletPath();

        System.out.println(context);
        System.out.println(protocol);
        System.out.println(ip);
        System.out.println(port);
        System.out.println(app);
        String href = "rmsInterface";

        response.setContentType("application/x-java-jnlp-file");
        PrintWriter out = new PrintWriter(new File("/home/sun/Documents/test.jnlp"));
        //PrintWriter out = response.getWriter();
        out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        out.println("<jnlp spec=\"1.0+\" codebase=\"" + protocol + "://" + ip
                + ":" + port + "/" + app + "\" href=\""+href+"\">");

        out.println("<information>");
        out.println("  <title>rms</title>");
        out.println("  <vendor>cattsoft</vendor>");
        out.println("  <homepage href=\"index.html\"/>");
        out.println("  <description>Web Start Version</description>");
        out.println("  <description kind=\"short\">Web Start Version</description>");
        out.println("</information>");
        out.println("<security>");
        out.println("  <all-permissions/>");
        out.println("</security>");
        out.println("<resources>");
        out.println("  <j2se version=\"1.5+\"/>");
        out.println("  <jar href=\"trmsEJBClient-genkey.jar\"/>");
        out.println("  <jar href=\"rms_Lib-genkey.jar\"/>");
        out.println("  <jar href=\"weblogic-genkey.jar\"/>");
        out.println("</resources>");

        out.println("<application-desc main-class=\"oss.rms.app.provision.rm.swing.view.main.SoView\">");
        out.println("<argument>"+ url +"</argument>");
        out.println("</application-desc>");

        out.println("</jnlp>");
        out.flush();
        out.close();

    }
}
