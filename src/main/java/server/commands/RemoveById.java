package server.commands;

import global.facility.Response;
import global.facility.Route;
import global.tools.Console;
import server.rulers.CollectionManager;
import global.exeptions.NotFoundException;

/**
 * команда удаляющая элемент из коллекции по его id
 */
public class RemoveById extends Command {
    private final CollectionManager collectionRuler;
    private final Console console;


    public RemoveById(Console console , CollectionManager collectionRuler) {
        super("remove_by_id", "удалить элемент из коллекции по его id");

        this.collectionRuler = collectionRuler;
        this.console = console;
    }
    /**
     * метод выполняет команду
     *
     * @return возвращает сообщение о  успешности выполнения команды
     */
    @Override
    public Response apply(String[] arguments , Route ticket){
        if(arguments[1].isEmpty()){
            //console.println("Неправильное количество аргументов!");
            //console.println("Использование: '" + getName() + "'");
            return new Response("Неправильное количество аргументов!\n" + "Использование: '" + getName() + "'" );
        }
        try{
            long deletableId= Long.parseLong(arguments[1]);
            var deletable= collectionRuler.byId((int) deletableId);
            if (deletable == null) throw new NotFoundException();
            collectionRuler.remove(deletable.getId());
            return new Response("Route удалён");
        }catch(NotFoundException e){
            //console.printError("Продукта с таким ID в коллекции нет!");
            return new Response("Продукта с таким ID в коллекции нет!");
        }
    }
}