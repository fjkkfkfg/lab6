package server.commands;

import global.facility.Response;
import global.facility.Route;
import server.rulers.CollectionManager;

/**
 * команда сохраняющая коллекцию в файл
 */
public class Save  extends Command{

    private final CollectionManager collectionRuler;

    public Save(CollectionManager collectionRuler){
        super("save", "сохранить коллекцию");

        this.collectionRuler = collectionRuler;
    }
    /**
     * метод выполняет команду
     *
     * @return возвращает сообщение о  успешности выполнения команды
     */
    @Override
    public Response apply(String[] arguments , Route route){
        if(!arguments[1].isEmpty()){
            console.println("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return new Response("");
        }

        collectionRuler.saveCollection();
        console.println("Выполнение сохранения прошло успешно");
        return new Response("");
    }
}