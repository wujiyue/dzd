package com.markbro.dzd.servlet;

import com.markbro.asoiaf.core.TmConstant;
import com.markbro.asoiaf.utils.validatecode.ValidateCode;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2015/8/1.
 */
public class ValidateCodeServlet extends HttpServlet {

    private int w = 70;
    private int h = 26;

    public ValidateCodeServlet() {
        super();
    }

    public void destroy() {
        super.destroy();
    }

    public static boolean validate(HttpServletRequest request, String validateCode){
        String code = (String)request.getSession().getAttribute(TmConstant.KEY_VERYCODE);
        return validateCode.toUpperCase().equals(code);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String validateCode = request.getParameter(TmConstant.KEY_VERYCODE); // AJAX验证，成功返回true
        if (StringUtils.isNotBlank(validateCode)){
            response.getOutputStream().print(validate(request, validateCode)?"true":"false");
        }else{
            this.doPost(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ValidateCode validateCode=ValidateCode.getInstance();
        validateCode.setLevel(ValidateCode.LEVEL.EASY);
        String code=validateCode.getCodeString();
        BufferedImage image =validateCode.getImage(code);
        request.getSession().setAttribute(TmConstant.KEY_VERYCODE, code);
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.close();
    }
}
