package com.telco.bnb.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.mail.imap.protocol.Item;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@Controller
public class SearchController {
	
	//
	@GetMapping("/search")
	public String search(HttpServletRequest request, Model model) throws IOException, Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("dto")==null) {
			return "redirect:/login/login";
		}
		
		URL url = null;
		
		int i = 1;
		while(i<11) {
		if("경상".equals(String.valueOf(request.getParameter("des"))) || "경상남".equals(String.valueOf(request.getParameter("des")))||
				"경남".equals(String.valueOf(request.getParameter("des"))) || "경상남도".equals(String.valueOf(request.getParameter("des")))){
			url = new URL("http://apis.data.go.kr/6480000/gyeongnamlodgeinfo/gyeongnamlodgeinfolist?ServiceKey=LymnrNWuKh%2Fy7EZcYycf4cfSzKDriE38ICY5nsnLuPZbphsaOHGDaWAO%2F%2B45yD1EkQkH79tD6C%2FLjMCHXAfk0A%3D%3D&pageNo="+i+"&numOfRows=10&resultType=json");
		}
		else if("충청".equals(String.valueOf(request.getParameter("des"))) || "충청북".equals(String.valueOf(request.getParameter("des")))||
				"충북".equals(String.valueOf(request.getParameter("des"))) || "충청북도".equals(String.valueOf(request.getParameter("des")))) {
			url = new URL("https://api.odcloud.kr/api/15053066/v1/uddi:eb0c728a-7fe7-4a22-80c3-eb4df0825bbd_201911061419?page="+i+"&perPage=10&returnType=json&serviceKey=LymnrNWuKh%2Fy7EZcYycf4cfSzKDriE38ICY5nsnLuPZbphsaOHGDaWAO%2F%2B45yD1EkQkH79tD6C%2FLjMCHXAfk0A%3D%3D");
		}
        
			
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        
        JSONObject jsonObject = new JSONObject(); 
        
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
		model.addAttribute("res"+i,sb.toString());
		
		i++;
		}
		
		return "test";
		
	}
}
