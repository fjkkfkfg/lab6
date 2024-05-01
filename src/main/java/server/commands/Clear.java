package server.commands;

import global.facility.Response;
import global.facility.Route;
import global.tools.Console;
import server.rulers.CollectionManager;

import java.util.Collection;
import java.util.Stack;

/**
 * Команда очищает коллекцию.
 */
public class Clear extends Command {

    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console= console;
    }
    /**
     * Метод выполняет команду
     *
     * @return возвращает сообщение об успешности выполнения команды
     */
    @Override
    public Response apply (String[] arguments , Route route){
        if(!arguments[1].isEmpty()){
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return new Response("ага");
        }



        var isFirst = true;
        Stack<Route> stack = collectionManager.getCollection();
        while (!stack.isEmpty()) {
            var route1 = stack.pop();
            collectionManager.remove(route1.getId());
            collectionManager.addLog("remove " + route1.getId(), isFirst);
            isFirst = false;
        }

        collectionManager.update();
        return new Response("Коллекция очищена :) ");
    }
}
