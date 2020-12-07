import java.util.*;
import java.text.*; 

public class MyClass {
    public static void main(String args[]) throws Exception{
       
       //test case 1
        Map<String,Integer> dic = new HashMap<>();
        dic.put("2020-01-01",4);
        dic.put("2020-01-02",4);
        dic.put("2020-01-03",6);
        dic.put("2020-01-04",8);
        dic.put("2020-01-05",2);
        dic.put("2020-01-06",-6);
        dic.put("2020-01-07",2);
        dic.put("2020-01-08",-2);
        
        //test case 2
         Map<String,Integer> dic1 = new HashMap<>();
        dic1.put("2020-01-01",6);
        dic1.put("2020-01-04",12);
        dic1.put("2020-01-05",14);
        dic1.put("2020-01-06",2);
        dic1.put("2020-01-07",4);
        
        // call the solution function
       
        Map<String,Integer> sol = solution(dic);  //for test case 1
        //Map<String,Integer> sol = solution(dic1); //for test case 2
        
        
        //print the solution dictionary
          String[] weekday = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
          for(String s : weekday){
              System.out.println(s + " : "+ sol.get(s));
          }
          
    /** 
        This code satisfy all the condition mention in the assignment except in those cases in which the difference is not
        divisable by the count and due to integer division the resulting dictionary might not satisfy the second condition.
    */
    }
    
    static Map<String,Integer> solution(Map<String,Integer> dic) throws ParseException{
       
        String[] weekday = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        Map<String, Integer> newDic = new HashMap<>();  // new dictionary
        
        int[] sum = new int[7]; //stores the total sum of the day
        boolean[] flag = new boolean[7];    // to track if any day is not in dictionary
       
       //calculate the sum value for the available dates / day  in the dictionary
       
        for(String date: dic.keySet()){
          int day = new SimpleDateFormat("yyyy-MM-dd").parse(date).getDay();    //day of the week
          day=(day==0)?6:day-1;
          //System.out.println(day);
          if(flag[day])
          sum[day]+=dic.get(date);
          else
          {
              flag[day]=true;   // mark the day with the flag when atleast one date found with on that day
              sum[day]=dic.get(date);
          }
        }
        
       
        // Calculate the sum value for the days which are not flagged
        // We found the length of continuos unflagged days
        // calculate the difference of next and prev flagged days.
        // calculate the step size by dividing the  diffrence with (count+1 )intervals 
        // after that we assign the unflagged days by increase of step size in each iteration using the formula written below code
        
        for(int i=1; i<6; i++){
            if(flag[i])
            continue;
            
            int count=1;
           
            int j=i;
            while(j<6 && flag[j]==false)
            {   j++;
                count++;
            }
            int diff=sum[j]-sum[i-1];
            
            int step = diff/count;
            
            while(i<j)
            {
                sum[i]=sum[i-1]+step;
                i++;
            }
        }
        
        // we create our new dictionary by assiging sum value to its corresponding day
        
        for(int i=0; i<7; i++){
            newDic.put(weekday[i],sum[i]);
        }
        return newDic;
    }
    
   
}
