package org.zerhusen.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThermService {
	private static final List<Person> persons;
	private static final List<Therm> therms;

	
	
    static {
        persons = new ArrayList<>();
        persons.add(new Person("Hello", "therm1"));
        persons.add(new Person("Foo", "therm2"));
        
        therms = new ArrayList<Therm>();
        therms.add(new Therm("W1","001","JYD1"));
        therms.add(new Therm("W2","002","JYD2"));
    }

    @RequestMapping(path = "/therms", method = RequestMethod.GET)
    public static List<Person> getPersons() {
        return persons;
    }

    @RequestMapping(path = "/therms/{name}", method = RequestMethod.GET)
    public static Person getPerson(@PathVariable("name") String name) {
        return persons.stream()
                .filter(person -> name.equalsIgnoreCase(person.getName()))
                .findAny().orElse(null);
    }
    
    /**
     * 查询温度计信息
     * @param thermBarcode，温度计条码
     * @return
     */
    @RequestMapping(path = "/therm/{thermBarcode}", method = RequestMethod.GET)
    public Therm getTherm(@PathVariable("thermBarcode") String thermBarcode) {
    	//TODO 查询温度计信息，调用TMS API，入参温度计条码，返回值：温度计条码，设备号
        return therms.stream()
                .filter(therm -> thermBarcode.equalsIgnoreCase(therm.getThermBarcode()))
                .findAny().orElse(null);
    }
    
    /**
     * 配置温度计
     * @return
     */
    @RequestMapping(path = "/therm", method = RequestMethod.PUT)
    public Therm doConfig(@RequestBody String body) {
    	System.out.println("body = " + body);
    	//TODO 
        return therms.get(0);
    }
    
}
