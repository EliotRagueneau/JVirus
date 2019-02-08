import Utils.IO;

public class Game {
    private static Map map;

    public static void main(String[] args) {
        map = new Map(20);
//        map.show();
        menu();
    }

//    public static void menu(){
//        boolean go = true;
//        while(go){
//            Content selectedCell = map.selectContent();
//            selectedCell.menu(); //Appel du menu de la cellule sélectionnée
//        }
//    }

    public static void menu(){
        for(int i = 0;i<4;i++){
            IO.print("Cellules, tour : " + (i + 1) + "\n");
            Case selectedCase = map.selectCase(Cell.class); //Selection uniquement des cellules
            selectedCase.content.menu(selectedCase); //Appel du menu de la cellule sélectionnée
        }
        for(int i = 0;i<4;i++) {
            IO.print("Virus, tour : " + (i + 1) + "\n");
            Case selectedCase = map.selectCase(Virus.class); //Selection uniquement des cellules
            selectedCase.content.menu(selectedCase); //Appel du menu de la cellule sélectionnée
        }
        map.turn();
    }

    public static Map getMap() {
        return map;
    }
}
