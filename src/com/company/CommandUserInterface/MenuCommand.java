package com.company.CommandUserInterface;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MenuCommand implements Command {
    private String menu_name;
    private Map<String,Command> map =new TreeMap();
    public MenuCommand(String command){
        this.menu_name=command;
    }
    @Override
    public void execute()
    {
        this.map.keySet().forEach((x)-> System.out.println(x));
    }
    public void addCommand(String description,Command command)
    {
        this.map.put(description,command);
    }
    public List<Command> getCommand()
    {
        return (List) this.map.values().stream().collect(Collectors.toList());

    }
    public String getMenuName()
    {
        return menu_name;
    }
}
