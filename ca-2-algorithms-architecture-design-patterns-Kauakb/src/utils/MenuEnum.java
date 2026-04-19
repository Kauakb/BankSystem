package utils;

public enum MenuEnum {
    SORT(1, "SORT"),
    SEARCH(2, "SEARCH"),
    ADD_RECORDS(3, "ADD RECORDS"),
    BINARY_TREE(4, "Create a binary tree"),
    EXIT(5, "EXIT");

    private final int choice;
    private final String description;

    MenuEnum(int choice, String description) {
        this.choice = choice;
        this.description = description;
    }

    public int getChoice(){
        return choice;
    }

    public String getDescription(){
        return description;
    }

    //get menuenum from user choice
    public static MenuEnum fromChoice(int choice){
        for (MenuEnum menu : MenuEnum.values()){
            if (menu.getChoice() == choice){
                return menu;
            }
        }
        return null;
    }

    public static void displayMenu(){
        System.out.println("\nDo you wish to SORT or SEARCH:");
        for (MenuEnum menu : MenuEnum.values()){
            System.out.println(menu.choice + " " + menu.description);
        }
    }
}