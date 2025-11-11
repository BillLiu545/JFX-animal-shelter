
public class Pet implements Comparable<Pet>
{
    private String name, type;
    private int nameTag;
    public Pet(String name, int nameTag, String type)
    {
        this.name = name;
        this.type = type;
        this.nameTag = nameTag;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getNameTag()
    {
        return nameTag;
    }
    
    public int compareTo(Pet other)
    {
        if (nameTag == other.nameTag)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
    public String toString()
    {
        return "Name: " + name + "/ Name Tag #: " + nameTag + "/Type: " + type;
    }
}
