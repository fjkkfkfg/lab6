package server.commands;

import global.facility.Response;
import global.facility.Route;
import server.rulers.CollectionManager;
import global.tools.Console;

/**
 * Команда, которая выводит элементы коллекции в порядке возрастания.
 * @author Artem
 */
public class PrintAscending extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintAscending(Console console, CollectionManager collectionManager) {
        super("print_ascending","вывести элементы коллекции в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public Response apply(String[] args, Route route) {
        if (!args[1].isEmpty()) {
            return new Response("Неправильное количество аргументов!" +
                    "\nИспользование: '" + getName() + "'");
        }
        var collection = collectionManager.getCollection();
        for (var e : collection) {
            console.println(e);
        }
        return new Response("NICE");
    }
}
