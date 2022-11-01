package oceanCleanup.src;

import java.util.List;

public class Game {

    private Room currentRoom;
    private CommandWords commands;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        Room dock, recyclingCenter, ship, wheelhouse, ocean;

        dock = new Room("on the dock at the shipyard");
        recyclingCenter = new Room("in the recycling center");
        ship = new Room("onboard the ship 002");
        wheelhouse = new Room("in the ship's wheelhouse");
        ocean = new Room("on the Pacific Ocean");


        dock.setExit("east", recyclingCenter);
        dock.setExit("north", ship);


        recyclingCenter.setExit("west", dock);

        ship.setExit("south", dock);
        ship.setExit("west", wheelhouse);

        wheelhouse.setExit("east", ship);
        wheelhouse.setExit("north", ocean);

        ocean.setExit("south", wheelhouse);


        currentRoom = dock;
    }

    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        String direction = command.getCommandValue();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            return true;
        }
    }

    public boolean quit(Command command) {
        if (command.hasCommandValue()) {
            return false;
        } else {
            return true;
        }
    }

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
    }

    public CommandWords getCommands() {
        return commands;
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }

}
