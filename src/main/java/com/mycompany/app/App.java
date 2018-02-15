package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App{
	public static int containsAll(Integer[] bigArray,Integer[] smallArray,int minSize,int maxSize){
		if(bigArray.length<=smallArray.length||maxSize<bigArray.length||minSize>smallArray.length||smallArray.length<1)
			return -1;

		boolean flag=true;
		int i;
		for( i=0;i<(bigArray.length-smallArray.length+1);i++){
			flag=true;
			for (int j=0;j<smallArray.length; j++) {
				if(!bigArray[i+j].equals(smallArray[j]))
					flag=false;
			}
			if(flag)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		port(getHerokuAssignedPort());
		get("/", (req, res) -> "Hello, World");

		post("/compute", (req, res) -> {
			//System.out.println(req.queryParams("input1"));
			//System.out.println(req.queryParams("input2"));
			
			
			String input1 = req.queryParams("input1");
			java.util.Scanner sc1 = new java.util.Scanner(input1);
			sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList1 = new java.util.ArrayList<>();
            while (sc1.hasNext())
            {
              int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
              inputList1.add(value);
            }
            System.out.println(inputList1);
            
            String input2 = req.queryParams("input2");
			java.util.Scanner sc2 = new java.util.Scanner(input2);
			sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer> inputList2 = new java.util.ArrayList<>();
            while (sc2.hasNext())
            {
              int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
              inputList2.add(value);
            }
            System.out.println(inputList2);
            
            String input3 = req.queryParams("input3").replaceAll("\\s","");
            int input3AsInt = Integer.parseInt(input3);
            
            String input4 = req.queryParams("input4").replaceAll("\\s","");
            int input4AsInt = Integer.parseInt(input4);

            int result = App.containsAll((Integer[])inputList1.toArray(), (Integer[])inputList2.toArray(),input3AsInt,input4AsInt);

            Map map = new HashMap();
            map.put("result", result);
            return new ModelAndView(map, "compute.mustache");
		}, new MustacheTemplateEngine());


          get("/compute",
              (rq, rs) -> {
                Map map = new HashMap();
                map.put("result", "not computed yet!");
                return new ModelAndView(map, "compute.mustache");
              },
              new MustacheTemplateEngine());
}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}