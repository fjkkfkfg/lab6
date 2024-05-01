package server.commands;
import global.facility.Response;
import global.facility.Route;
import server.rulers.CollectionManager;
import global.tools.Console;

/**
 * Команда,которая выводит дистанции в порядке возрастания
 * @author Artem
 */
public class PrintFieldAsсendingDistance extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldAsсendingDistance(Console console, CollectionManager collectionManager) {
        super("print_field_ascending_distance", "вывести значения поля distance всех элементов в порядке возрастания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public Response apply(String[] args, Route route) {
        if (!args[1].isEmpty()) {
            return new Response( "Неправильное количество аргументов!" +
                    "\nИспользование: '" + getName() + "'");
        }
        var collection = collectionManager.getCollection();
        for (var e : collection) {
            console.println(e.getDistance());
        }
        return new Response("suck dick");
    }
}
