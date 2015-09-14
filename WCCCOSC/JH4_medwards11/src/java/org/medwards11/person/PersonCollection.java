
package org.medwards11.person;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

public class PersonCollection {
    private ArrayList<Person> personCollection = new ArrayList();
    String errorMsg="";
    public int size(){
        return personCollection.size();
    }
    
    private void clear() {
        personCollection.clear();
    }
    
    
    public static PersonCollection update(PageContext context) {
        HttpSession session = context.getSession();
        PersonCollection pc = (PersonCollection) session.getAttribute("PersonCollection");
        if(pc == null)
        {
            pc = new PersonCollection();
            session.setAttribute("PersonCollection", pc);
        }
        
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        String action = request.getParameter("action");
        if(action != null) {
            String name = request.getParameter("name");
            String eyeColor = request.getParameter("eyeColor");
            String hairColor = request.getParameter("hairColor");
            String height = request.getParameter("height");
            String weight = request.getParameter("weight");
            Person person = new Person(name, eyeColor, hairColor, height, weight);
            
            if(action.equals("Clear List")) 
                   pc.clear();
            else if(action.equals("add")) {
                if(!pc.personCollection.contains(person)) 
                    pc.personCollection.add(person);
                 else 
                    pc.errorMsg="Book already exists!";
                
            } else if(action.equals("remove")) 
                    pc.personCollection.remove(Integer.parseInt(request.getParameter("index")));
            
            else if(action.equals("update")) {
                Person updatedPerson = pc.personCollection.get(Integer.parseInt(request.getParameter("index")));
                        updatedPerson.setName(name);
                        updatedPerson.setEyeColor(eyeColor);
                        updatedPerson.setHairColor(hairColor);
                        updatedPerson.setHeight(height);
                        updatedPerson.setWeight(weight);
                                
            }
            
        }
        return pc;
    }
    
    public String getErrorMessage() {
        return errorMsg;
    }
    
    public Person getPerson(int index) {
        return personCollection.get(index);
    }
}
