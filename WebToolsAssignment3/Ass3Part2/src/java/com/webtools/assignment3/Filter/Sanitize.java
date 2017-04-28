/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtools.assignment3.Filter;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author Nimi
 */
public class Sanitize extends HttpServletRequestWrapper{
    private static Pattern[] patterns = new Pattern[]{
      
        Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
        Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
        Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),         
        Pattern.compile("<", Pattern.CASE_INSENSITIVE),
        Pattern.compile(">", Pattern.CASE_INSENSITIVE),
        Pattern.compile("%", Pattern.CASE_INSENSITIVE),
        
    };

    public Sanitize(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

   
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return sanitize(value);
    }    

    private String sanitize(String val) {
        if (val != null) {           
            val = val.replaceAll("\0", "");          
            for (Pattern scp : patterns){
                val = scp.matcher(val).replaceAll("");
            }
        }
        return val;
    }
}
