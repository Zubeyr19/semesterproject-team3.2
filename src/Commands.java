package oceanCleanup.src;

public enum Commands
{
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), TALK("talk");
    
    private String commandName;
    
    Commands(String commandString)
    {
        this.commandName = commandString;
    }
    
    public String toString()
    {
        return commandName;
    }
}
