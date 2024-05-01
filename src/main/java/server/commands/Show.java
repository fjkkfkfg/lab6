package server.commands;

import global.facility.Response;
import global.facility.Route;
import global.tools.Console;
import server.rulers.CollectionManager;

/**
 * команда выводящая в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command {

    private final CollectionManager collectionManager;
    private final Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;

    }

    /**
     * метод выполняет команду
     *
     * @return возвращает сообщение о успешности выполнения команды
     */
    @Override
    public Response apply(String[] arguments, Route ticket) {
        if (!arguments[1].isEmpty()) {
            return new Response("Неправильное количество " +
                    "аргументов!\nИспользование: '\" + getName() + \"'");
        }
        return new Response(collectionManager.toString());
    }
}