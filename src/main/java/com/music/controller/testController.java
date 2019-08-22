package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.music.util.MyFileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 羊 on 2018/10/31.
 */
@Controller
@RequestMapping("/test")
public class testController {
    private String NOTICE_IMAGE_DIR = "D:/Java/mavenprojects/ssm-new/src/main/webapp/WEB-INF/files";
    private String NOTICE_URL = "http://localhost:8080/ssm-new/src/main/webapp/WEB-INF/files";

    //    private static final ObjectMapper mapper = new ObjectMapper();
    @RequestMapping("/ok")
    public String admindoAddSinger(){
        return "success";
    }

    @RequestMapping(value = "/testFile",method = RequestMethod.GET)
    public String testFile(){
        return  "testFile";
    }

    @RequestMapping(value = "/testss",method = RequestMethod.GET)
    public String testss(HttpSession httpSession){
        if(httpSession.getAttribute("result") == null){
            System.out.println("空");
        }
        else{
            JSONObject object = (JSONObject) httpSession.getAttribute("result");
            System.out.println(object);
        }
        return  "testFile";
    }

    @RequestMapping(value = "/ser",method = RequestMethod.GET)
    public String ser(HttpSession httpSession){

        return "music_search";
    }

//    @RequestMapping(value = "/dotestFile",method = RequestMethod.POST)
//    public String dotestFile(@RequestParam("uploadFile") MultipartFile[] uploadFile , HttpServletRequest request,HttpServletResponse response){
//        REPOSITORY_PATH = request.getSession().getServletContext().getRealPath("upload");
//        MultipartFile multipartFile = null;
//        boolean isLegal = false;
//        List<PicUploadResult> fileUploadResult = new ArrayList<>();
//        PicUploadResult pic = null;
//        ExportExcelConfig eec = new ExportExcelConfig();
//        String urls = "";
//        for (int i = 0; i < uploadFile.length; i++) {
//            multipartFile = uploadFile[i];
//            // 校验图片格式
//            for (String type : IMAGE_TYPE) {
//                if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), type)) {
//                    isLegal = true;
//                    break;
//                }
//            }
//
//            // 封装Result对象，并且将文件的byte数组放置到result对象中
//            pic = new PicUploadResult();
//
//            // 状态
//            pic.setError(isLegal ? 0 : 1);
//
//            // 文件新路径
//            String filePath = getFilePath(multipartFile.getOriginalFilename());
//
//            if (LOGGER.isDebugEnabled()) {
//                LOGGER.debug("Pic file upload .[{}] to [{}] ."+multipartFile.getOriginalFilename());
//            }
//
//            // 生成图片的绝对引用地址
//            String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath,REPOSITORY_PATH), "\\", "/");
//            pic.setUrl(IMAGE_BASE_URL + picUrl);
//
//            File newFile = new File(filePath);
//
//            // 写文件到磁盘
//            multipartFile.transferTo(newFile);
//
//            // 校验图片是否合法
//            isLegal = false;
//            try {
//                BufferedImage image = ImageIO.read(newFile);
//                if (image != null) {
//                    pic.setWidth(image.getWidth() + "");
//                    pic.setHeight(image.getHeight() + "");
//                    isLegal = true;
//                }
//            } catch (IOException e) {
//            }
//
//            // 状态
//            pic.setError(isLegal ? 0 : 1);
//            if(pic.getError()==0){
//                urls+=pic.getUrl();
//                if(i<2)
//                    urls+=",";
//            }
//            if (!isLegal) {
//                // 不合法，将磁盘上的文件删除
//                newFile.delete();
//            }
//            fileUploadResult.add(pic);
//        }
//        eec.setUrl(urls);
//        eec.setCreateTime(new Date());
//        exportService.addConfigInfo(eec);
//        response.setContentType(MediaType.TEXT_HTML_VALUE);
//        return mapper.writeValueAsString(fileUploadResult);
////        return "redirect:/test/testFile";
//    }

    @RequestMapping(value = "/dotestFile",method = RequestMethod.POST)
    public String doTestFile(@RequestParam(value = "uploadfile")MultipartFile upload[],HttpServletRequest httpServletRequest){
        for (MultipartFile file : upload) {
            String newFileName2 = MyFileUtils.fileUpload(file, NOTICE_IMAGE_DIR, NOTICE_URL);
            System.out.println(newFileName2);
        }
        return  "testFile";
    }

    @RequestMapping(value = "/testshowfile",method = RequestMethod.GET)
    public String doTestFile(Model model){
//        String filePath = "d:\\his.txt";
        String filePath = "D:\\Java\\mavenprojects\\ssm-new\\src\\main\\webapp\\WEB-INF\\lrc\\closer_to_me.lrc";
        ArrayList<String[]> text=new ArrayList<String[]>();
        text=readTxtFile(filePath,"utf-8");
//        readTxtFile1(filePath);
//        System.out.println(text.get(0)[0]);
//        System.out.println(text.get(1)[0]);
//        System.out.println(text.get(2)[0]);
//        System.out.println(text.get(3)[0]);
//        System.out.println(text.get(5)[0]);
//        System.out.println(text.get(6)[0]);
        List<String> str = new ArrayList<>();
        for (String[] strings : text) {
            String sss = String.valueOf(strings[0]);
            str.add(sss);
            System.out.println(strings);
        }
        System.out.println(str);
        model.addAttribute("str",str);

//        for (int i = 0; i < text.size(); i++) {
////            List<String> show = text.get(i)[0];
//            String s = String.join("");
////            text[i].toArray();
//            String str = String.join(",",  list.toArray(new String[list.size()]));
//        }
        model.addAttribute("fileshow",text.get(0)[0]);
        model.addAttribute("alltext",text);
//        System.out.println(text);
        return "files";
    }

    public static ArrayList<String[]> readTxtFile(String filePath,String encoding) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 编码格式必须和文件的一致
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!lineTxt.startsWith("--")) {
                        res.add(lineTxt.split("\t"));}}
                read.close();
            } else {
                System.out.println("指定的文件不存在");
            }
        } catch (Exception e){
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return res;
    }

    public static void readTxtFile1(String filePath){
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

}
